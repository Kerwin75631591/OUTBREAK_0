package com.outbreak.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.outbreak.entity.InvitedPeople;

public class DBConnect {
	public Connection connection = null;
	public Statement statement = null;
	public ResultSet rs = null;

	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		}
	}

	// 初始化数据库
	public void initiazation() throws SQLException {
		try {
			// 连接已有数据库
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "MyDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			// 新建数据库
			statement.executeUpdate("create database UserDB");
			// 打开新建的数据库
			statement.close();
			connection.close();
			Class.forName("com.mysql.jdbc.Driver");
			dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();

			// 创建用户表并记录 id，邮箱，密码，联系方式，名字，地址，
			statement.executeUpdate(
					"create table UserTable(id integer(5),email varchar(20),password varchar(20),phoneNumber varchar(11), name varchar(20), address varchar(20))");

			// 创建会议表并记录 id，邮箱，密码，联系方式，名字，地址，
			statement.executeUpdate(
					"create table MeetingTable(id integer(5),time datetime,endtime datetime,place varchar(20),name varchar(20), "
							+ "content varchar(20), host varchar(20), state integer(5), PeopleNum integer(5),ArrivalNum integer(5),"
							+ "FileUrl varchar(20))");

			// 创建人员表并记录 会议id，人员id，是否参加
			statement.executeUpdate("create table PeopleTable(Mid integer(5),Uid integer(5),TOF tinyint)");

		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 连接数据库
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			System.out.println("数据库已连接");
		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库无法连接");
		}
	}

	// 在UserTable中加入新的数据
	public void insertUser(String email, String password, String phoneNumber, String name, String address)
			throws SQLException {
		String sql = "SELECT id FROM UserTable ";
		rs = statement.executeQuery(sql);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		id = id + 1;
		sql = "INSERT INTO UserTable(id ,email ,password ,phoneNumber , name , address)values(?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, name);
		pstmt.setString(6, address);
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();
	}

	// 在UserTable中删除数据
	public void deleteUser(String email) throws SQLException {
		String sql = "DELETE * FROM UserTable WHERE email = " + email;
		statement.execute(sql);
	}

	// UserTable登录(true)&注册(false)检测 返回值0为通过，登录中1为密码错误，2为账号不存在，注册中1为该邮箱已注册
	public int searchUser(boolean judge, String email, String password) throws SQLException {
		String sql = "SELECT*FROM UserTable";
		rs = statement.executeQuery(sql);
		System.out.println("rs表已创建");
		if (judge) {
			while (rs.next()) {
				if (email.equals(rs.getString("email"))) {
					if (password.equals(rs.getString("password")))
						return 0;
					else
						return 1;
				}
			}
			return 2;
		} else {
			while (rs.next()) {
				if (email.equals(rs.getString("email")))
					return 1;
			}
			return 0;
		}
	}

	// 在MeetingTable中加入新的数据
	public int insertMeeting(int state, Date time,Date endtime, String place, String name,String topic, String content, String host,
			int PeopleNum, int ArrivalNum,String FileUrl) throws SQLException {
		String sql = "SELECT id FROM MeetingTable ";
		rs = statement.executeQuery(sql);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		id = id + 1;
		sql = "INSERT INTO MeetingTable(id,time,endtime,place,name,topic,content,host,state,PeopleNum,ArrivalNum,FileUrl)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setDate(2, new java.sql.Date(time.getTime()));
		pstmt.setDate(3, new java.sql.Date(endtime.getTime()));
		pstmt.setString(4, place);
		pstmt.setString(5, name);
		pstmt.setString(6, topic);
		pstmt.setString(7, content);
		pstmt.setString(8, host);
		pstmt.setInt(9, state);
		pstmt.setInt(10, PeopleNum);
		pstmt.setInt(11, ArrivalNum);
		pstmt.setString(12, FileUrl);
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();

		return id;
	}

	// 在MeetingTable中删除数据
	public void deleteMeeting(Date time, String name) throws SQLException {
		String sql = "DELETE FROM MeetingTable WHERE time = " + time + " And name = " + name;
		statement.execute(sql);
	}

	// MeetingTable搜索某人举办的会议，返回resultset
	public ResultSet searchMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE host = '" + host + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable搜索所有未提交的会议，返回resultset
	public ResultSet searchChangableMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 0  And host = '"+ host + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable搜索的同名同时会议
	public boolean searchMeeting(Date time, String name,String place) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE time = '" + time + "' name = '" + name + "' place = '"+place+"'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);
		boolean judge = true;
		while (rs.next())
			judge = false;
		return judge;

	}

	// MeetingTable修改某个会议的状态
	public void updateMeeting(int id, int state) throws SQLException {
		String sql = "UPDATE MeetingTable SET state = " + state + " WHERE   id = '" + id + "'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);

	}

	// 在PeopleTable中加入新的数据
	public void insertPeople(int id, ArrayList<InvitedPeople> people) throws SQLException {
		System.out.println("insertPeople已进入");
		Iterator<InvitedPeople> it = people.iterator();
		while (it.hasNext()) {
			InvitedPeople p=it.next();
			System.out.println(p.getName()+"+"+p.getEmail());
			String sql = "UPDATE UserTable SET NAME =  '" + p.getName() + "'  WHERE email= '" + p.getEmail() + "'";
			statement.executeUpdate(sql);

			sql = "INSERT INTO PeopleTable(Mid,uid,TOF)values(?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, p.getName());
			pstmt.setBoolean(3, false);
			pstmt.addBatch();
			pstmt.clearParameters();
			pstmt.executeBatch();
			pstmt.clearBatch();

		}
	}

	// PeopleTable搜索所有该mid的会议，返回resultset
		public ResultSet searchPeople(int mid) throws SQLException {
			String sql = "SELECT * FROM PeopleTable WHERE mid = '"+mid+"'";
			rs = statement.executeQuery(sql);
			return rs;
		}
		
		
	// 关闭数据库连接
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 数据库初始化
	public static void main(String[] args) throws SQLException {
		DBConnect db = new DBConnect();
		db.initiazation();
	}

}
