package com.wuminghui.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类：拦截用户的请求。
public class MyInterceptor implements HandlerInterceptor {

//验证用户的登录信息，正确return true，其他情况return false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器111111的MyInterceptor的preHandle()");
        String loginName = "";
        //从session中获取name
        Object attr = request.getSession().getAttribute("name");
        if (attr != null){
            loginName = (String)attr;
        }
        //判断登录的账号是否符合要求
        if (!"zs".equals(loginName)){
            //不能访问系统
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;

        }
        //zs登录
        return true;
    }
}
