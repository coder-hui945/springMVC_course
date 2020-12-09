# springMVC_course
Spring_MVC学习笔记


SpringMVC：是基于spring的一个框架， 实际上就是spring的一个模块， 专门是做web开发的。
           理解是servlet的一个升级

	   web开发底层是servlet ， 框架是在servlet基础上面加入一些功能，让你做web开发方便。

SpringMVC就是一个Spring。 Spring是容器，ioc能够管理对象，使用<bean>, @Component, @Repository, @Service, @Controller
  SpringMVC能够创建对象， 放入到容器中（SpringMVC容器）， springmvc容器中放的是控制器对象，

  我们要做的是 使用@Contorller创建控制器对象， 把对象放入到springmvc容器中， 把创建的对象作为控制器使用
  这个控制器对象能接收用户的请求， 显示处理结果，就当做是一个servlet使用。

  使用@Controller注解创建的是一个普通类的对象， 不是Servlet。 springmvc赋予了控制器对象一些额外的功能。


  web开发底层是servlet， springmvc中有一个对象是Servlet ： DispatherServlet(中央调度器)
  DispatherServlet: 负责接收用户的所有请求， 用户把请求给了DispatherServlet， 之后DispatherServlet把请求转发给
                    我们的Controller对象， 最后是Controller对象处理请求。

		   

   

  index.jsp-----DispatherServlet(Servlet)----转发，分配给---Controller对象（@Controller注解创建的对象）
  main.jsp                                                   MainController
  addUser.jsp                                                UserController



---------------------------------------------------------------------------------------------------------

springmvc请求的处理流程

 1）发起some.do
 2）tomcat(web.xml--url-pattern知道 *.do的请求给DispatcherServlet)
 3）DispatcherServlet（根据springmvc.xml配置知道 some.do---doSome()）
 4）DispatcherServlet把some.do转发个MyController.doSome()方法
 5）框架执行doSome（）把得到ModelAndView进行处理， 转发到show.jsp


上面的过程简化的方式
  some.do---DispatcherServlet---MyController
--------------------------------------------------------------------------------------------------------
springmvc执行过程源代码分析
1. tomcat启动，创建容器的过程
   通过load-on-start标签指定的1，创建DisaptcherServlet对象， 
   DisaptcherServlet它的父类是继承HttpServlet的， 它是一个serlvet， 在被创建时，会执行init（）方法。
   在init（）方法中
   //创建容器，读取配置文件
    WebApplicationContext ctx = new ClassPathXmlApplicationContext("springmvc.xml");
    //把容器对象放入到ServletContext中
    getServletContext().setAttribute(key, ctx);

  上面创建容器作用： 创建@controller注解所在的类的对象， 创建MyController对象，
   这个对象放入到 springmvc的容器中， 容器是map ， 类似 map.put("myController",MyController对象)


2.请求的处理过程
  1）执行servlet的service()
       protected void service(HttpServletRequest request, HttpServletResponse response)

       protected void doService(HttpServletRequest request, HttpServletResponse response)


      DispatcherServlet.doDispatch(request, response){

          调用MyController的.doSome()方法
      }

   doDispatch：springmvc中DispatcherServlet的核心方法， 所有的请求都在这个方法中完成的。



没有加入注解驱动标签时的状态
org.springframework.http.converter.ByteArrayHttpMessageConverter 
org.springframework.http.converter.StringHttpMessageConverter
org.springframework.http.converter.xml.SourceHttpMessageConverter
org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter


加入注解驱动标签时的状态
org.springframework.http.converter.ByteArrayHttpMessageConverter
org.springframework.http.converter.StringHttpMessageConverter
org.springframework.http.converter.ResourceHttpMessageConverter
org.springframework.http.converter.ResourceRegionHttpMessageConverter
org.springframework.http.converter.xml.SourceHttpMessageConverter 
org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter 
org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter
org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

==========================================================================
发起的请求是由哪些服务器程序处理的。

http://localhost:8080/ch05_url_pattern/index.jsp ：tomcat（jsp会转为servlet）
http://localhost:8080/ch05_url_pattern/js/jquery-3.4.1.js ： tomcat
http://localhost:8080/ch05_url_pattern/images/p1.jpg ： tomcat
http://localhost:8080/ch05_url_pattern/html/test.html： tomcat
http://localhost:8080/ch05_url_pattern/some.do ：  DispatcherServlet（springmvc框架处理的）


tomcat本身能处理静态资源的访问， 像html， 图片， js文件都是静态资源


tomcat的web.xml文件有一个servlet 名称是 default ， 在服务器启动时创建的。
 <servlet>
        <servlet-name>default</servlet-name>
        <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
        <init-param>
            <param-name>debug</param-name>
            <param-value>0</param-value>
        </init-param>
        <init-param>
            <param-name>listings</param-name>
            <param-value>false</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

	  <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>/</url-pattern>  表示静态资源和未映射的请求都这个default处理
    </servlet-mapping>


default这个servlet作用： 
The default servlet for all web applications, that serves static  
resources.  It processes all requests that are not mapped to other  
servlets with servlet mappings (defined either here or in your own   
web.xml file).

1.处理静态资源
2.处理未映射到其它servlet的请求。


========================================================================
在jsp ， html中使用的地址， 都是在前端页面中的地址，都是相对地址

地址分类：
 1.绝对地址 ， 带有协议名称的是绝对地址，  http://www.baidu.com , ftp://202.122.23.1
 2.相对地址， 没有协议开头的， 例如 user/some.do  , /user/some.do
              相对地址不能独立使用，必须有一个参考地址。 通过参考地址+相对地址本身才能指定资源。

				  张三同学， 1班有张三， 2班也有张三

 3.参考地址
    1） 在你的页面中的，访问地址不加 "/"

	 访问的是： http://localhost:8080/ch06_path/index.jsp
      路径： http://localhost:8080/ch06_path/
		资源： index.jsp

    在index.jsp发起 user/some.do请求，访问地址变为 http://localhost:8080/ch06_path/user/some.do
	   当你的地址 没有斜杠开头,例如 user/some.do , 当你点击链接时， 访问地址是当前页面的地址
		加上链接的地址。
      http://localhost:8080/ch06_path/ + user/some.do


     -------------------------------------------------------------
	  index.jsp  访问 user/some.do  ， 返回后现在的地址： http://localhost:8080/ch06_path/user/some.do

	  http://localhost:8080/ch06_path/user/some.do
	  路径：	  http://localhost:8080/ch06_path/user/
	  资源：   some.do

	  在index.jsp在 user/some.do ，就变为 http://localhost:8080/ch06_path/user/user/some.do

	  解决方案：
	   1.加入${pageContext.request.contextPath}
		2.加入一个base标签， 是html语言中的标签。 表示当前页面中访问地址的基地址。
		  你的页面中所有 没有“/”开头的地址，都是以base标签中的地址为参考地址
        使用base中的地址 + user/some.do 组成访问地址




   2）在你的页面中的，访问地址加 "/"
      访问的是： http://localhost:8080/ch06_path/index.jsp
      路径： http://localhost:8080/ch06_path/
		资源： index.jsp

		点击 /user/some.do, 访问地址变为 http://localhost:8080/user/some.do
		参考地址是 你的服务器地址， 也就是 http://localhost:8080


		如果你的资源不能访问： 加入${pageContext.request.contextPath}
		<a href="${pageContext.request.contextPath}/user/some.do">发起user/some.do的get请求</a>


	

index.jsp--addStudent.jsp---student/addStudent.do( service的方法，调用dao的方法)--result.jsp

=======================================================================================================

ch08-forard-redirect:转发和重定向

forward：表示转发
redirect：表示重定向
forward和redirect都是关键字， 有一个共同的特点不和视图解析器一同工作


扩展：
forward和redirect他们都可以访问 视图文件，比如某个jsp ，html
 forward:/hello.jsp  forward:/main.html

forward和redirect他们都可以访问其它的controller
 forward:/some.do , redirect:/other.do

处理器方法可以返回ModelAndView, String , void 都可以使用forward，redirect
    
============================================================================================
异常处理：
springmvc框架采用的是统一，全局的异常处理。
把controller中的所有异常处理都集中到一个地方。 采用的是aop的思想。把业务逻辑和异常处理代码分开。解耦合。

使用两个注解
1.@ExceptionHandler
2.@ControllerAdvice



拦截器：
1）拦截器是springmvc中的一种，需要实现HandlerInterceptor接口。
2）拦截器和过滤器类似，功能方向侧重点不同。 过滤器是用来过滤器请求参数，设置编码字符集等工作。
    拦截器是拦截用户的请求，做请求做判断处理的。
3）拦截器是全局的，可以对多个Controller做拦截。 
   一个项目中可以有0个或多个拦截器， 他们在一起拦截用户的请求。
	拦截器常用在：用户登录处理，权限检查， 记录日志。

拦截器的使用步骤：
 1.定义类实现HandlerInterceptor接口
 2.在springmvc配置文件中，声明拦截器， 让框架知道拦截器的存在。

拦截器的执行时间：
  1）在请求处理之前， 也就是controller类中的方法执行之前先被拦截。
  2）在控制器方法执行之后也会执行拦截器。
  3）在请求处理完成后也会执行拦截器。


拦截器：看做是多个Controller中公用的功能，集中到拦截器统一处理。使用的aop的思想

=================================================================================
多个拦截器：
第一个拦截器preHandle=true , 第二个拦截器preHandle=true 

111111-拦截器的MyInterceptor的preHandle()
22222-拦截器的MyInterceptor的preHandle()
=====执行MyController中的doSome方法=====
22222-拦截器的MyInterceptor的postHandle()
111111-拦截器的MyInterceptor的postHandle()
22222-拦截器的MyInterceptor的afterCompletion()
111111-拦截器的MyInterceptor的afterCompletion()

---------------------------------------------------
第一个拦截器preHandle=true , 第二个拦截器preHandle=false

111111-拦截器的MyInterceptor的preHandle()
22222-拦截器的MyInterceptor的preHandle()
111111-拦截器的MyInterceptor的afterCompletion()

----------------------------------------------------------
第一个拦截器preHandle=false , 第二个拦截器preHandle=true|false

111111-拦截器的MyInterceptor的preHandle()


====================================================================
拦截器和过滤器的区别

1.过滤器是servlet中的对象，  拦截器是框架中的对象
2.过滤器实现Filter接口的对象， 拦截器是实现HandlerInterceptor
3.过滤器是用来设置request，response的参数，属性的，侧重对数据过滤的。
  拦截器是用来验证请求的，能截断请求。
4.过滤器是在拦截器之前先执行的。
5.过滤器是tomcat服务器创建的对象
  拦截器是springmvc容器中创建的对象
6.过滤器是一个执行时间点。
  拦截器有三个执行时间点
7.过滤器可以处理jsp，js，html等等
  拦截器是侧重拦截对Controller的对象。 如果你的请求不能被DispatcherServlet接收， 这个请求不会执行拦截器内容
8.拦截器拦截普通类方法执行，过滤器过滤servlet请求响应

===========================================================================
