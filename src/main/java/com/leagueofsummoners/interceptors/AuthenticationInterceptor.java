package com.leagueofsummoners.interceptors;

import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.security.annotations.LoginRequired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
		UserDTO user = (UserDTO) httpServletRequest.getSession().getAttribute("userlogged");
		
		if (loginRequired == null) {
			return true;
		}
		
		if(null == user){
			httpServletResponse.sendRedirect("/?champName=Amumu&locale=en&error=notlogged");
			return false;
		}
		

		return super.preHandle(httpServletRequest, httpServletResponse, handler);
	}
}