package com.wuminghui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
* @RequestMapping:
* value:所有请求地址的公共部分，叫做模块名称
* 位置：放在所有类的上面
*
* */
@Controller

public class MyController {

    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age) {
        System.out.println("======执行了MyController中的doSome方法======");
        ModelAndView mv  = new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);
       mv.setViewName("show");
        return mv;
    }



}
