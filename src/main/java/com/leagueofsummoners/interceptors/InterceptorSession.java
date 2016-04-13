package com.leagueofsummoners.interceptors;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class InterceptorSession  implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}

}
