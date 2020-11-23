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

    @RequestMapping(value = "/some.do")/*除此之外，过滤器不能解决该乱码问题，
                                                                                    因为通过@ResponseBody直接输出响应，不走过滤器*/
    public ModelAndView doSome( String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("show");

        return mv;
    }
}
