package server.OUTBREAK_1.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.OUTBREAK_1.util.DBConnect;

@RestController
@SpringBootApplication
public class Controller {
	// 登录功能
	@RequestMapping("Login")
	public Map<String, Object> Login(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		DBConnect db = new DBConnect();
		System.out.println(email+"+"+password);
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
			map.put("Number", i + 1);
			for (int j = 0; j < i; j++) {
				rs = db.searchMeeting(mids[j]);
				Map<String, Object> meetings = new HashMap<String, Object>();
				meetings.put("name", rs.getString("name"));
				meetings.put("time", rs.getString("time"));
				meetings.put("place", rs.getString("place"));
				meetings.put("state", rs.getInt("state"));
				map.put(mids[j] + "", meetings);
			}

		} catch (SQLException e) {
			System.out.println("会议搜索失败");
			e.printStackTrace();
		}
		return map;
	}

	// 详细会议信息功能
	@RequestMapping("ComplexMeeting")
	public Map<String, Object> ComplexMeeting(int mid) {
		Map<String, Object> map = new HashMap<String, Object>();

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
			Map<String, Object> meetings = new HashMap<String, Object>();
			meetings.put("name", rs.getString("name"));
			meetings.put("topic", rs.getString("topic"));
			meetings.put("time", rs.getString("time"));
			meetings.put("place", rs.getString("place"));
			meetings.put("state", rs.getInt("state"));
			meetings.put("fileUrl", rs.getString("fileUrl"));
			meetings.put("content", rs.getString("content"));
			map.put("meeting", meetings);
			rs.close();
			rs=db.searchPeople(mid);
			while(rs.next()) {
				map.put(rs.getString("name"), rs.getInt("TOF"));
			}
			
		} catch (SQLException e) {
			System.out.println("会议搜索失败");
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
}