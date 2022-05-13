package com.web.pos.control;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.web.pos.dao.UserInfoInterfacce;
import com.web.pos.dao.UserInfoJdbcDao;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String driver = context.getInitParameter("jdbc_driver");
		String url = context.getInitParameter("db_url");
		String user = context.getInitParameter("db_user");
		String passwd = context.getInitParameter("db_passwd");
		
		// DB접근을 위한 정보 저장
		UserInfoInterfacce ui = new UserInfoJdbcDao(driver, url, user, passwd);
		
		// 세션에 저장
		context.setAttribute("UserInfo", ui);
		
		System.out.println("execute ServletContextListener.contextIntialized()...");
	}
}