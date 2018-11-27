package com.itxc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itxc.dao.UsersDao;
import com.itxc.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    //接收表单数据
		String name = request.getParameter("userName");
		String pwd = request.getParameter("passWord");
		//控制器 调用业务或持久层模型类
		UsersDao dao = new UsersDao();
		User user=dao.findUserByNameAndPwd(name, pwd);
		if(user==null) {
			//没登录成功  提示用户
			request.getSession().setAttribute("message", "【用户名或密码错误,请重新输入！】");
			response.sendRedirect("login.jsp");
		}else {
			// 发送自动登录的cookie
			String autoLogin = request.getParameter("autologin");
			if (autoLogin != null) {
				// 注意 cookie 中的密码要加密
				Cookie cookie = new Cookie("autologin", name +"-"+ pwd);
				cookie.setMaxAge(Integer.parseInt(autoLogin));//储存时间
				cookie.setPath(request.getContextPath());//储存路径
				response.addCookie(cookie);//cookie将被写入到浏览器本地磁盘中
			}
			request.getSession().setAttribute("user",user);
			response.sendRedirect("index.jsp");
		}
	}

}
