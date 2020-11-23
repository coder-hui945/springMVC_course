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
@RequestMapping("/user")//方便模块的命名和修改（可用可不用）
public class MyController {

   @RequestMapping(value = "/first.do")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc++++");
        mv.addObject("fun",  "执行的是doSome方法");
        mv.setViewName("/index.jsp");//导致http://localhost:8080/ch06_path_war_exploded/user/user/first.do
        //返回mv
        return mv;
    }


}
