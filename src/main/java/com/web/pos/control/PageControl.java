package com.web.pos.control;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.web.pos.dao.UserInfoInterfacce;
import com.web.pos.dao.UserInfoJdbcDao;
import com.web.pos.entity.UserInfo;

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
			System.out.println("main 실행");
			
			viewName = "WEB-INF/view/main.jsp";
		} else if (uri.equals("/login")) {
			System.out.println("로그인 처리 실행");
			
			// id, pw 가지고 와서 변수에 저장
			String input_id = request.getParameter("id");
			String input_pw = request.getParameter("pw");
			UserInfo userinfo = null;
			
			System.out.println("넘어온 아이디 : " + input_id);
			
			// DB 접근 객체 생성
			UserInfoInterfacce ui = null;
			
			// DB 접근
			ui = (UserInfoInterfacce)getServletContext().getAttribute("UserInfo");
			userinfo = ui.getUserInfo(input_id, input_pw);
			
			if (userinfo == null) {
				System.out.println("로그인 실패!");
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('회원 정보를 찾을 수 없습니다!!!'); location.href='main';</script>");
				writer.close();
			} else {
				System.out.println("로그인 성공!");
				
				// 로그인에 성공하면 id와 이름을 세션으로 저장시켜 management.jsp로 넘겨줌
				request.setAttribute("UserInfo_ID", userinfo.getUser_id());
				request.setAttribute("UserInfo_NAME", userinfo.getUser_name());
				
				viewName = "WEB-INF/view/management.jsp";
			}
		} else if (uri.equals("/membership")) { // 회원 가입 화면 출력
			System.out.println("회원가입 화면 실행");
			
			viewName = "WEB-INF/view/membership.jsp";
		} else if (uri.equals("/memberprocess")) { // 회원 가입 실행(DB에 저장하고 로그인 화면으로 돌아감)
			System.out.println("회원가입 실행");
			
			// id, pw, name 가지고 와서 변수에 저장
			String input_id = request.getParameter("member_id");
			String input_pw = request.getParameter("member_pw");
			String input_name = request.getParameter("member_name");
			
			System.out.println("넘어온 아이디 : " + input_id);
			System.out.println("넘어온 이름 : " + input_name);
			
			// DB 접근 객체 생성
			UserInfoInterfacce ui = null;
						
			// DB 접근
			ui = (UserInfoInterfacce)getServletContext().getAttribute("UserInfo");
			int result = ui.insertUserInfo(input_id, input_pw, input_name);
			
			if (result == 1) {
				System.out.println("회원가입 성공!!!");
				
				// 회원 가입 성공하면 메시지 창 띄우고 로그인 화면으로...
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('회원가입 성공!!!'); location.href='main';</script>");
				writer.close();
			} else {
				System.out.println("회원가입 실패!!!");
				
				// 회원 가입 실패하면 메시지 창 띄우고 회원가입 화면으로...
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('회원가입 실패!!! 아이디가 중복 됩니다!!!'); location.href='membership';</script>");
				writer.close();
			}
		}
		
		
		// step #3. output results
		if (viewName != null) { // viewName이 null이 아닐때만 forward
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}