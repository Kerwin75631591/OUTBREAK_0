package server.OUTBREAK_1.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.OUTBREAK_1.util.DBConnect;

@RestController
@SpringBootApplication
public class Controller {
	//登录功能
	@RequestMapping("Login")
	public Map<String, Object> Login(String email,String password){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		DBConnect db=new DBConnect();
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			if(db.searchUser(true, email, password)==0)
			{
				ResultSet rs=db.searchPeople(email);
				List<Integer> listtemp=new ArrayList<Integer>();
				while(rs.next()) {
					listtemp.add(rs.getInt("mid"));
				}
				for (int i = 0; i < listtemp.size(); i++) {
					 rs=db.searchMeeting(listtemp.get(i));
					 rs.next();
					 list.add(rs.getString("name"));
					 list.add(""+rs.getDate("begintime"));
					 list.add(rs.getString("place"));
					 list.add(""+rs.getInt("state"));
					 map.put("list"+i,list);
					 list.clear();
					 }
			}else {
				String message="登录失败";
				map.put("message", message);
			}
		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		return map;
	}
	
	//注册功能
	@RequestMapping("Register")
	public Map<String, Object> Register(String email,String password,String name){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		DBConnect db=new DBConnect();
		try {
			db.connect();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		try {
			if(db.searchUser(false, email, password)==0)
			{
				db.insertUser(email, password, null, name, null);
				ResultSet rs=db.searchPeople(email);
				List<Integer> listtemp=new ArrayList<Integer>();
				while(rs.next()) {
					listtemp.add(rs.getInt("mid"));
				}
				for (int i = 0; i < listtemp.size(); i++) {
					 rs=db.searchMeeting(listtemp.get(i));
					 rs.next();
					 list.add(rs.getString("name"));
					 list.add(""+rs.getDate("begintime"));
					 list.add(rs.getString("place"));
					 list.add(""+rs.getInt("state"));
					 map.put("list"+i,list);
					 list.clear();
					 }
			}else {
				String message="注册失败";
				map.put("message", message);
			}
		} catch (SQLException e) {
			System.out.println("登录搜索失败");
			e.printStackTrace();
		}
		return map;
	}

}
