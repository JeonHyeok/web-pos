package com.web.pos.control;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String driver = context.getInitParameter("jdbc_driver");
		String url = context.getInitParameter("db_url");
		String user = context.getInitParameter("db_user");
		String passwd = context.getInitParameter("db_passwd");
		
		//QuestionProvider qp = new QuestionProviderByJDBC(driver, url, user, passwd);
		
		//context.setAttribute("question_provider", qp);
		
		System.out.println("execute ServletContextListener.contextIntialized()...");
	}
}