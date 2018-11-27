package com.itxc.filter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itxc.dao.UsersDao;
import com.itxc.model.User;
public class AutoLoginFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("--------AutoLoginFilter----------");
		HttpServletRequest request = (HttpServletRequest) req;
		// 获得一个名为 autologin 的cookie
		Cookie[] cookies = request.getCookies();
		String autologin = null;
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			if ("autologin".equals(cookies[i].getName())) {
				// 找到了指定的cookie
				autologin = cookies[i].getValue();
				break;
			}
		}
		if (autologin != null) {
			// 做自动登录
			String[] parts = autologin.split("-");
			String username = parts[0];
			String password = parts[1];
			//控制器 调用业务或持久层模型类
			UsersDao dao = new UsersDao();
			User user=dao.findUserByNameAndPwd(username, password);
			if(user!=null) {
				//将用户储存对象信息储存
				request.getSession().setAttribute("user", user);
			}
		}
		// 放行
		chain.doFilter(request, response);
	}
	public void destroy() {
	}
}