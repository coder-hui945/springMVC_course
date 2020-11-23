package com.wuminghui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/test")//方便模块的命名和修改（可用可不用）
public class MyController {
    /*
    * @RequestMapping：请求映射
    *   属性：method，表示发起请求的方式。它的值是RequestMethod类的枚举
    *   例如： get请求方式：RequestMethod.GET
    *           post请求方式：RequestMethod.POST
    * 注意：不指定方式，表示两种方式都可以请求，指定了就得按固定的方式请求
    *
    *
    * */
   // @RequestMapping(value = {"/test/some.do","/test/first.do"})//把多个请求交给同一个方法处理
   @RequestMapping(value = {"/some.do","/first.do"},method= RequestMethod.GET)
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc++++");
        mv.addObject("fun",  "执行的是doSome方法");
        mv.setViewName("show");
        //返回mv
        return mv;
    }

   // @RequestMapping(value = {"/test/other.do","/test/second.do"})
   @RequestMapping(value = {"/other.do","/second.do"},method = RequestMethod.POST)
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","===欢迎使用springmvc===");
        mv.addObject("fun",  "执行的是doOther方法");
        mv.setViewName("other");
        return mv;
    }
    // @RequestMapping(value = {"/test/other.do","/test/second.do"})
    @RequestMapping(value = "/thired.do")
    public ModelAndView doThired(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","===欢迎使用springmvc===" + request.getParameter("name"));
        mv.addObject("fun",  "执行的是doThired方法");
        mv.setViewName("other");
        return mv;
    }
}
