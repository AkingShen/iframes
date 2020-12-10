package com.iframe.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.JwtUtils;
import com.iframe.common.utils.RedisUtils;
import com.iframe.common.utils.RetResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RedisUtils redisUtils;

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
        String token = request.getHeader("X-token");
        if(StringUtils.isBlank(token)){
            // 没有从request中拿
            token = request.getParameter("X-token");
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
        //当token存在时，userId一定会存在，无需判断是否村存在
        //jwt验证token;
        String userId = request.getHeader("X-userId");
        DecodedJWT jwt = null;
        try{
             jwt = JwtUtils.verifyToken(token,userId);
        }catch (Exception e){
            return false;
        }
        if(userId.equals(jwt.getClaim("userId").toString())){
            return false;
        }
        //先去缓存中寻找是否存在改token;
        Object object = redisUtils.get(userId,0);
        if(null == object){
            return false;
        }
        String redisToken = (String)object;
        if(!redisToken.equals(token)){
            return false;
        }
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
