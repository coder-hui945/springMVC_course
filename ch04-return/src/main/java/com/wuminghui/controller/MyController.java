package com.wuminghui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuminghui.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
* @RequestMapping:
* value:所有请求地址的公共部分，叫做模块名称
* 位置：放在所有类的上面
*
* */
@Controller
public class MyController {
/*处理器方法返回一个String---表示逻辑视图名称，需要配置逻辑视图解析器
* string只能控制页面跳转，但是可以手动添加数据的转发，通过request作用域实现。
* */
   @RequestMapping(value = "/returnString-view.do")
    public String doReturnView(HttpServletRequest request,String name,Integer age){
        //可以自己手工添加数据到request作用域
       request.setAttribute("name",name);
       request.setAttribute("age",age);
        //show:逻辑视图名称，项目中springmvc配置了视图解析器
       //框架对视图执行的是forward转发操作
        return "show";
    }
//处理器方法返回String，表示完整视图路径，此时不能配置视图解析器
    @RequestMapping(value = "/returnString-view2.do")
    public String doReturnView2(HttpServletRequest request,String name,Integer age){
        System.out.println("======doReturnView2======    name = " + name + "   age = " + age );
        //可以自己手工添加数据到request作用域
        request.setAttribute("name",name);
        request.setAttribute("age",age);
        //完整视图路径，项目不能配置视图解析器
        //框架对视图执行的是forward转发操作
        return "/WEB-INF/view/show.jsp";
    }
        //处理器方法返回void，响应ajax请求
    //手工实现ajax，json数据：代码有重复，重复体现在
        // 1.java对象转为json ；2.通过HttpServletRespongse输出json数据
    //所以该过程可交给框架实现，通过返回值Object
        // （注意：Object对象返回主要用于响应ajax请求的）
    @RequestMapping(value = "/return-ajax.do")
    public void doReturnVoidAjax(HttpServletResponse response,String name,Integer age) throws IOException {
        System.out.println("======doReturnVoidAjax======    name = " + name + "   age = " + age );
       //处理ajax使用json做数据的格式
        //service调用完成了，使用Student表示处理结果
        Student student = new Student(name,age);
        //把结果转化为json格式数据
        String json = "{}";
        if (student != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转换的json：" + json);

        }
        //输出数据，响应ajax的请求
        response.setContentType("application/json;charset=utf-8");/*除此之外，过滤器不能解决该乱码问题，
                                                                    因为通过@ResponseBody直接输出响应，不走过滤器*/
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();


    }
    /*
    * 处理器方法返回一个Student，通过框架转为json，响应ajax请求
    *@ResponseBody:作用是把处理器方法返回的对象转为json后，通过HttpServletResponse输出给浏览器。
    * 位置：方法定义的上面。和其它注解没有顺序的关系。
    *
   返回对象框架的处理流程：
     *  1. 框架会把返回Student类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     *     检查哪个HttpMessageConverter接口的实现类能处理Student类型的数据--MappingJackson2HttpMessageConverter
     *
     *  2.框架会调用实现类的write（）， MappingJackson2HttpMessageConverter的write()方法
     *    把李四同学的student对象转为json， 调用Jackson的ObjectMapper实现转为json
     *    contentType: application/json;charset=utf-8
     *
     *  3.框架会调用@ResponseBody把2的结果数据输出到浏览器， ajax请求处理完成
    * */
    @RequestMapping(value = "/returnStudentJson.do")
    @ResponseBody
    public Student doStudentJsonObject(String name,Integer age) {
        //调用service，获取请求结果数据，Student对象表示结果数据
        Student student = new Student("吴明辉", 25);
        return student;//会被框架转为json格式

    }
/*处理器方法返回List<student>*/
    @RequestMapping(value = "/doStudentJsonObjectArray.do")
    @ResponseBody
    public List<Student> doStudentJsonObjectArray(String name, Integer age) {
        ArrayList<Student> list = new ArrayList<>();
        //调用service，获取请求结果数据，Student对象表示结果数据
        Student student1 = new Student("吴明辉", 25);
        Student student2= new Student("陈悦", 23);
        Student student3= new Student("哈哈哈", 24);
        list.add(student1);
        list.add(student2);
        list.add(student3);
        return list;//会被框架转为json格式

    }

    /**
     * 处理器方法返回的是String ， String表示数据的，不是视图。
     * 区分返回值String是数据，还是视图，看有没有@ResponseBody注解
     * 如果有@ResponseBody注解，返回String就是数据，反之就是视图
     *
     * 默认使用“text/plain;charset=ISO-8859-1”作为contentType,导致中文有乱码，
     * 解决方案：给RequestMapping增加一个属性 produces, 使用这个属性指定新的contentType.
     * 返回对象框架的处理流程：
     *  1. 框架会把返回String类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     *     检查那个HttpMessageConverter接口的实现类能处理String类型的数据--StringHttpMessageConverter
     *
     *  2.框架会调用实现类的write（）， StringHttpMessageConverter的write()方法
     *    把字符按照指定的编码处理 text/plain;charset=ISO-8859-1
     *
     *  3.框架会调用@ResponseBody把2的结果数据输出到浏览器， ajax请求处理完成
     */
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doStringData(String name,Integer age){
        return "Hello Springmvc 返回String对象表示数据";
    }
    }
