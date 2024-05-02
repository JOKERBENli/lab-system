package com.ksedu.labsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Xzj
 * @Description
 * @create 2024/2/27 18:10
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtConfig jwtConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.contains("/login")){
            return true;
        }

        //处理token
        String token = request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtConfig.getHeader());
        }

        if(StringUtils.isEmpty(token)){
            //返回401，token不能为空
            sendError(response,"token不能为空");
            return false;
        }

        try {
            jwtConfig.getTokenClaim(token);
        } catch (Exception e) {

            sendError(response,"token无效");
            return false;
        }
        return true;

    }
    private  void sendError(HttpServletResponse response,String msg) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(msg);
    }

}
