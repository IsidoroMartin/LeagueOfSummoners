package com.leagueofsummoners.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leagueofsummoners.model.dto.UserDTO;

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
			httpServletResponse.sendRedirect("/notlogged");
			return false;
		}
		

		return super.preHandle(httpServletRequest, httpServletResponse, handler);
	}
}