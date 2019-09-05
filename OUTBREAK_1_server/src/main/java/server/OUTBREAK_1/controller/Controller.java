package server.OUTBREAK_1.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.OUTBREAK_1.entity.EmailPoster;
import server.OUTBREAK_1.util.DBConnect;

@RestController
@SpringBootApplication
public class Controller {
	// 登录功能
	@RequestMapping("Login")
	public Map<String, Object> Login(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		DBConnect db = new DBConnect();
		System.out.println(email + "+" + password);
		boolean message = false;
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			if (db.searchUser(true, email, password) == 0) {
				message = true;
			} else {
				message = false;
			}
		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		map.put("judge", message);
		return map;
	}

	// 注册功能
	@RequestMapping("Register")
	public Map<String, Object> Register(String email, String password, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		DBConnect db = new DBConnect();
		boolean message = false;
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			if (db.searchUser(false, email, password) == 0) {
				db.insertUser(email, password, null, name, null);
				message = true;
			} else {
				message = false;
			}
		} catch (SQLException e) {
			System.out.println("注册搜索失败");
			e.printStackTrace();
		}
		map.put("judge", message);
		return map;
	}

	// 简单会议信息功能
	@RequestMapping("SimpleMeeting")
	public Map<String, Object> SimpleMeeting(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		ArrayList<Map> list = new ArrayList<Map>();
		DBConnect db = new DBConnect();
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			ResultSet rs = db.searchPeople(email);
			int[] mids = new int[100];
			int i = 0;
			while (rs.next()) {
				mids[i] = rs.getInt("Mid");
				i++;
			}
			rs.close();
			map.put("Number", i);
			for (int j = 0; j < i; j++) {
				rs = db.searchMeeting(mids[j]);
				rs.next();
				Map<String, Object> meetings = new HashMap<String, Object>();
				meetings.put("name", rs.getString("name"));
				meetings.put("time", rs.getString("begintime"));
				meetings.put("place", rs.getString("place"));
				meetings.put("state", rs.getInt("state"));
				meetings.put("mid", mids[j]);
				System.out.println(mids[j]);
				list.add(meetings);
			}

		} catch (SQLException e) {
			System.out.println("会议搜索失败");
			e.printStackTrace();
		}
		map.put("list", list);
		return map;
	}

	// 详细会议信息功能
	@RequestMapping("ComplexMeeting")
	public Map<String, Object> ComplexMeeting(int mid) {
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		ArrayList<Map> list = new ArrayList<Map>();
		DBConnect db = new DBConnect();
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			ResultSet rs = db.searchMeeting(mid);
			rs.next();
			Map<String, Object> meetings = new HashMap<String, Object>();
			meetings.put("name", rs.getString("name"));
			meetings.put("topic", rs.getString("topic"));
			meetings.put("time", rs.getString("Begintime"));
			meetings.put("place", rs.getString("place"));
			meetings.put("state", rs.getInt("state"));
			meetings.put("fileUrl", rs.getString("fileUrl"));
			meetings.put("content", rs.getString("content"));
			map.put("meeting", meetings);
			rs.close();
			rs = db.searchPeople(mid);
			int i = 0;
			while (rs.next()) {
				Map<String, Object> people = new HashMap<String, Object>();
				people.put("name", rs.getString("Uid"));
				people.put("TOF", rs.getInt("TOF"));
				people.put("email", rs.getString("email"));
				list.add(people);
				i++;
			}
			map.put("number", i);
			map.put("list", list);
		} catch (SQLException e) {
			System.out.println("会议搜索失败");
			e.printStackTrace();
		}
		return map;
	}

	//确定参加会议
	@RequestMapping("setTOF")
	public Map<String, Object> setTOF(String email,int mid) {
		System.out.println("进入setTOF");
		Map<String, Object> map = new HashMap<String, Object>();
		DBConnect db = new DBConnect();
		boolean judge=false;
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			db.updatePeople(email, mid);
			judge=true;
		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		map.put("judge", judge);
		return map;
	}
	
	// 全局消息查询功能
	@RequestMapping("getMessage")
	public Map<String, Object> getMessage() {
		System.out.println("进入getMessage");
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		ArrayList<Map> list = new ArrayList<Map>();
		DBConnect db = new DBConnect();
		int i = 0;
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			ResultSet rs = db.searchMessage();

			while (rs.next()) {
				Map<String, Object> tempmap = new HashMap<String, Object>();
				tempmap.put("message", rs.getString("message"));
				tempmap.put("time", rs.getString("time"));
				list.add(tempmap);
				i++;
			}

			map.put("number", i);
			map.put("list", list);
		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		return map;
	}

	// 用户数据查询功能
	@RequestMapping("UserData")
	public Map<String, Object> UserData(String email) {
		System.out.println("进入userdata");
		Map<String, Object> map = new HashMap<String, Object>();
		DBConnect db = new DBConnect();
		System.out.println(email);
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			ResultSet rs = db.searchUserData(email);
			rs.next();
			map.put("name", rs.getString("name"));
			map.put("phoneNum", rs.getString("phoneNumber"));
			map.put("duties", rs.getString("duties"));
			map.put("address", rs.getString("address"));
			map.put("email", rs.getString("email"));
		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		return map;
	}

	// 用户验证邮件
	@RequestMapping("sendEmail")
	public Map<String, Object> sendEmail(String email) {
		System.out.println("进入sendEmail");
		Map<String, Object> map = new HashMap<String, Object>();
		int code = 0;
		while (true) {
			code = (int) Math.floor(Math.random() * 1000000);
			if (code > 99999)
				break;
		}
		EmailPoster.sendCodeCheck(email, code);
		map.put("code", code);
		return map;
	}

	// 用户修改资料
	@RequestMapping("changeData")
	public Map<String, Object> changeData(String email, String name, String value) {
		System.out.println("进入changedata");
		Map<String, Object> map = new HashMap<String, Object>();
		DBConnect db = new DBConnect();
		boolean judge = false;
		System.out.println(email);
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			db.updateUser(email, name, value);
			judge = true;

		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		map.put("judge", judge);
		return map;
	}
}