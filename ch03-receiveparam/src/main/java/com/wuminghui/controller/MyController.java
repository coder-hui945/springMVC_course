package com.wuminghui.controller;

import com.wuminghui.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
* @RequestMapping:
* value:所有请求地址的公共部分，叫做模块名称
* 位置：放在所有类的上面
*
* */
@Controller

public class MyController {
    /*逐个接收请求参数
    *   要求：处理器方法的形参名和请求中的参数名必须一致，按名字接收，与顺序无关。
    * 同名的请求参数赋值给同名的形参
    * * 框架接收请求参数
     *   1. 使用request对象接收请求参数
     *      String strName = request.getParameter("name");
     *      String strAge = request.getParameter("age");
     *   2. springmvc框架通过 DispatcherServlet 调用 MyController的doSome()方法
     *      调用方法时，按名称对应，把接收的参数赋值给形参
     *      doSome（strName，Integer.valueOf(strAge)）
     *      框架会提供类型转换的功能，能把String转为 int ，long ， float， double等类型。
     *
     *  400状态码是客户端错误， 表示提交请求参数过程中，发生了问题。例如下列参数有个int，但是如果提交的参数为空或为字符串，框架提供的自动转换
     * 会将空字符串或字符串准换为int，出错，报400.  此时可以考虑将int改成integer,解决空字符串问题。
    */

   @RequestMapping(value = "/receiveproperty.do")
    public ModelAndView doSome(String name,Integer age){
       //可以在方法中直接使用name age
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age", age);
        //视图文件的逻辑名称（文件名）
        mv.setViewName("show");
        //返回mv
        return mv;
    }
/*
* 请求的参数名和处理器的形参名不一样
* @RequestParam：逐个接收请求参数中，解决请求中的参数名和形参名不一样的问题。
* 属性：1.value 请求中的参数名（）value可以省略
*       2.required 是一个Boolean，默认为true，true表示请求中必须包含此参数（缺少该参数报400），false表示该参数可以有也可以没有。程序不会报错
* 位置：处理器方法的形参定义的前面
*
* */

    @RequestMapping(value = "/receiveparam.do")
    public ModelAndView receivepara(@RequestParam("myname") String name,@RequestParam("myage") Integer age){
        //可以在方法中直接使用name age
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age", age);
        //视图文件的逻辑名称（文件名）
        mv.setViewName("show");
        //返回mv
        return mv;
    }
/*
*处理器方法形参是java对象，这个对象的属性名和请求中参数名一致的
* 框架会自动创建形参的Java对象，给属性赋值。请求中的参数是name，框架会自动调用setName（）方法。
*位置：参数位置定义形参对象
* */
    @RequestMapping(value = "/receiveobj.do")
    public ModelAndView receiveobj(Student myStudent){//可以创建多个对象接收参数，仍然是同名参数给同名的对象属性
        //可以在方法中直接使用name age
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",myStudent.getName());
        mv.addObject("age",myStudent.getAge());
        mv.addObject("myStudent",myStudent);
        //视图文件的逻辑名称（文件名）
        mv.setViewName("show");
        //返回mv
        return mv;
    }

}
