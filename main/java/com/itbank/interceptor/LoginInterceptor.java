package com.itbank.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 만약 로그인이 되어 있지 않다면 예정을 중단하고, 로그인 페이지로 리다이렉트한 후 false를 반환
		
		// 1) request에서 session을 불러온다
		HttpSession session = request.getSession();
		
		// 2) session에서 login attribute를 꺼낸다
		Object login = session.getAttribute("login");
		
		// 3) 만약 login이  null이면, 요청받은 주소를 파라미터로 지정하면서 login 메뉴로 리다이렉트한다
		if(login == null) {
			String url = request.getRequestURL().toString();
			String cpath = request.getContextPath();
			String path = cpath + "/member/login?url=" + url;
			response.sendRedirect(path);
			return false;
			// 리다이렉트 이후 포워드를 진행하면 문제가 발생할 수 있다
			// [응답이 커밋된 후에는 포워드를 수행할 수 없습니다]
			// [응답이 커밋된 후에는 리다이렉트를 수행할 수 없습니다]
		}
		
		// 로그인이 되어있다면 예정대로 진행하기 위해서 true를 반환
		return true;
	}

}
