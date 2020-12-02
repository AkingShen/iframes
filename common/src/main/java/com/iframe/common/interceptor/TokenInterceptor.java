package com.iframe.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.RetResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class TokenInterceptor  extends HandlerInterceptorAdapter {
    public TokenInterceptor() {
        super();
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 免token注解
        CheckToken checkToken;
        //1： 判断是否方法级别的
        if(handler instanceof HandlerMethod) {
            checkToken = ((HandlerMethod) handler).getMethodAnnotation(CheckToken.class);
        } else {
            // 如果不是方法级别的
            return true;
        }
        if(checkToken == null || checkToken.value()== false){
            // 没有注解,或者注解为不需要则 无需验证验证token
            return true;
        }
        //2:header中拿token
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            // 没有从request中拿
            token = request.getParameter("token");
        }

        //3:token为空
        if(StringUtils.isBlank(token)){
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            try {
                writer = response.getWriter();
                writer.print(JSONObject.toJSONString(RetResponse.makeErrUnauisp("无效令牌")));

            } catch (IOException e) {

            } finally {
                if (writer != null)
                    writer.close();
            }

            return  false;
        }
        //验证token 正确性

        //下面两步省略，自己可以创建一个简单用户表，然后里面设置token 信息
        //4:查询token信息 没查到抛出token无效信息
        //5：设置userId到request里，后续根据userId，获取用户信息
        //return  true表示通过了拦截器 可以执行下面的操作
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
