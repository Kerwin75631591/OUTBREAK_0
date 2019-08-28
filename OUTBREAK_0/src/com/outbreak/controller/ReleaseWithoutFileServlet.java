package com.outbreak.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.outbreak.entity.MeetingBean;
import com.outbreak.util.DBConnect;

/**
 * Servlet implementation class ReleaseWithoutFileServlet
 */
@WebServlet("/ReleaseWithoutFileServlet")
public class ReleaseWithoutFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseWithoutFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//新建会议实体
		MeetingBean mb = new MeetingBean();
		
		//设置会议信息
		mb.setName(request.getParameter("meetingName"));
		mb.setTopic(request.getParameter("meetingTopic"));
		//合成会议时间
		try {
			Date begintime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("meetingData") + " " + request.getParameter("meetingBegintime"));
			Date endtime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("meetingData") + " " + request.getParameter("meetingBegintime"));
			mb.setBegintime(begintime);
			mb.setEndtime(endtime);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		mb.setContent(request.getParameter("meetingContent"));
		mb.setPlace(request.getParameter("meetingPlace"));
		//制作会议邀请名单
		String[] guests = request.getParameter("Users").split("-");
		for(int i = 0; i<guests.length/3; i+=3)
		{
			mb.addpeople(guests[i], guests[i+2],guests[i+1]);;
		}
		mb.setPeopleNum(guests.length / 3);
		mb.setHost((String) request.getSession().getAttribute("sessionemail"));
		
		DBConnect db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id=0;
		try {
			id = db.insertMeeting(1, mb.getBegintime(), mb.getEndtime(), mb.getPlace(), mb.getName(), mb.getTopic(), mb.getContent(), mb.getHost(), mb.getPeopleNum(), mb.getArrivalNum(), mb.getFileUrl());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.insertPeople(id, mb.getPeople());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.close();
		
        // 跳转到 会议管理页面
		response.sendRedirect("/OUTBREAK_0/JSP/MeetingManage.jsp");
	}

}
