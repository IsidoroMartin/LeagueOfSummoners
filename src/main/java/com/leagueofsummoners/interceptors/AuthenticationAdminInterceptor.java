package com.leagueofsummoners.interceptors;

import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.security.annotations.LoginAdminRequired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationAdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginAdminRequired adminLoginRequired = handlerMethod.getMethod().getAnnotation(LoginAdminRequired.class);
		UserDTO user = (UserDTO) httpServletRequest.getSession().getAttribute("userlogged");

		if (adminLoginRequired == null) {
			return true;
		}
		if (null != user && user.isAdmin()) {
			return true;
		} else {
			httpServletResponse.sendRedirect("/forbbiden");
			return false;
		}
	}
}