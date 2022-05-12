package com.web.pos.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestControl
 */
@WebServlet(urlPatterns = {"/main", "/login", "/membership", "/memberprocess"})
public class PageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PageControl 서블릿 실행!!!");
		
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		System.out.println("들어온 주소는 " + request.getRequestURI());
		
		// step #2. data processing
		String viewName = null;
		String uri = request.getRequestURI();
		
		if (uri.equals("/main")) {
			System.out.println("main 처리 실행");
			viewName = "WEB-INF/view/main.jsp";
		} else if (uri.equals("/login")) {
			System.out.println("로그인 처리 실행");
			viewName = "WEB-INF/view/main.jsp";
		} else if (uri.equals("/membership")) {
			System.out.println("회원가입 화면 실행");
			viewName = "WEB-INF/view/membership.jsp";
		} else if (uri.equals("/memberprocess")) {
			System.out.println("회원가입 실행");
			viewName = "WEB-INF/view/main.jsp";
		}
		
		
		// step #3. output results
		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}