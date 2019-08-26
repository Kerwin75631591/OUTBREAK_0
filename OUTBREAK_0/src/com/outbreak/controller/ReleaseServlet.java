package com.outbreak.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReleaseServlet
 */
@WebServlet("/ReleaseServlet")
public class ReleaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author HuYu
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String meetingName = request.getParameter("meetingName");
		response.getWriter().append(meetingName);
		response.getWriter().append("doGet is over!");
		
		//鎻愪氦瀹屾垚锛岃繑鍥炰細璁鐞嗛〉闈�
		response.sendRedirect("MeetingManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String meetingName = request.getParameter("meetingName");
		response.getWriter().append(meetingName);
		response.getWriter().append("doGet is over!");
		System.out.println("doPost is over!");
		
		//鎻愪氦瀹屾垚锛岃繑鍥炰細璁鐞嗛〉闈�
		response.sendRedirect("/OUTBREAK_0/JSP/MeetingManage.jsp");
	}

}
