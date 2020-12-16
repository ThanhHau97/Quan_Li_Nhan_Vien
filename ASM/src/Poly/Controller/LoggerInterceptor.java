package Poly.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handle) throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null){
			response.sendRedirect(request.getContextPath() + "/Web/login.htm");
			return false;
		}
		return true;
	}
}
