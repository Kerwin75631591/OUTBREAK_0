package com.outbreak.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import com.outbreak.entity.InvitedPeople;

public class DBConnect {
	public Connection connection = null;
	public Statement statement = null;
	public ResultSet rs = null;

	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("�޷��ҵ�������");
		}
	}

	// ��ʼ�����ݿ�
	public void initiazation() throws SQLException {
		try {
			// �����������ݿ�
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "MyDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			// �½����ݿ�
			statement.executeUpdate("create database UserDB");
			// ���½������ݿ�
			statement.close();
			connection.close();
			Class.forName("com.mysql.jdbc.Driver");
			dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();

			// �����û�����¼ id�����䣬���룬��ϵ��ʽ�����֣���ַ��
			statement.executeUpdate(
					"create table UserTable(id integer(5),email varchar(20),password varchar(20),phoneNumber varchar(11), name varchar(20), address varchar(20))");

			// �����������¼ id�����䣬���룬��ϵ��ʽ�����֣���ַ��
			statement.executeUpdate(
					"create table MeetingTable(id integer(5),time date,place varchar(20),name varchar(20), "
							+ "content varchar(20), host varchar(20), state integer(5), PeopleNum integer(5),ArrivalNum integer(5),"
							+ "FileUrl varchar(20))");

			// ������Ա����¼ ����id����Աid���Ƿ�μ�
			statement.executeUpdate("create table PeopleTable(Mid integer(5),Uid integer(5),TOF tinyint)");

		} catch (ClassNotFoundException e) {
			System.out.println("�޷��ҵ�������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �������ݿ�
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false";
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			System.out.println("���ݿ�������");
		} catch (ClassNotFoundException e) {
			System.out.println("�޷��ҵ�������");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ��޷�����");
		}
	}

	// ��UserTable�м����µ�����
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

	// ��UserTable��ɾ������
	public void deleteUser(String email) throws SQLException {
		String sql = "DELETE * FROM UserTable WHERE email = " + email;
		statement.execute(sql);
	}

	// UserTable��¼(true)&ע��(false)��� ����ֵ0Ϊͨ������¼��1Ϊ�������2Ϊ�˺Ų����ڣ�ע����1Ϊ��������ע��
	public int searchUser(boolean judge, String email, String password) throws SQLException {
		String sql = "SELECT*FROM UserTable";
		rs = statement.executeQuery(sql);
		System.out.println("rs���Ѵ���");
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

	// ��MeetingTable�м����µ�����
	public int insertMeeting(int state, Date time, String place, String name, String content, String host,
			int PeopleNum, int ArrivalNum,String FileUrl) throws SQLException {
		String sql = "SELECT id FROM UserTable ";
		rs = statement.executeQuery(sql);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		id = id + 1;
		sql = "INSERT INTO MeetingTable(id,time,place,name,content,host,state,PeopleNum,ArrivalNum,FileUrl)values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setDate(2, new java.sql.Date(time.getTime()));
		pstmt.setString(3, place);
		pstmt.setString(4, name);
		pstmt.setString(5, content);
		pstmt.setString(6, host);
		pstmt.setInt(7, state);
		pstmt.setInt(8, PeopleNum);
		pstmt.setInt(9, ArrivalNum);
		pstmt.setString(10, FileUrl);
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();

		return id;
	}

	// ��MeetingTable��ɾ������
	public void deleteMeeting(Date time, String name) throws SQLException {
		String sql = "DELETE FROM MeetingTable WHERE time = " + time + " And name = " + name;
		statement.execute(sql);
	}

	// MeetingTable����ĳ�˾ٰ�Ļ��飬����resultset
	public ResultSet searchMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE host = '" + host + "'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable��������δ�ύ�Ļ��飬����resultset
	public ResultSet searchMeeting() throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 0";
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable������ͬ��ͬʱ����
	public boolean searchMeeting(Date time, String name) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE time = '" + time + "' name = '" + name + "'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);
		boolean judge = true;
		while (rs.next())
			judge = false;
		return judge;

	}

	// MeetingTable�޸�ĳ�������״̬
	public void updateMeeting(int id, int state) throws SQLException {
		String sql = "UPDATE MeetingTable SET state = " + state + " WHERE   id = '" + id + "'";
		System.out.println(sql);
		rs = statement.executeQuery(sql);

	}

	// ��PeopleTable�м����µ�����
	public void insertPeople(int id, InvitedPeople people) throws SQLException {
		while (people.getNext() != null) {

			String sql = "SELECT id FROM UserTable WHERE email= '" + people.getEmail() + "'";
			rs = statement.executeQuery(sql);
			rs.next();
			int pid = rs.getInt("id");

			sql = "UPDATE UserTable SET NAME =  '" + people.getName() + "'  WHERE email= '" + people.getEmail() + "'";
			statement.executeUpdate(sql);

			sql = "INSERT INTO PeopleTable(Mid,Pid,TOF)values(?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, pid);
			pstmt.setBoolean(3, false);
			pstmt.addBatch();
			pstmt.clearParameters();
			pstmt.executeBatch();
			pstmt.clearBatch();

			people = people.getNext();
		}
	}

	// PeopleTable�������и�mid�Ļ��飬����resultset
		public ResultSet searchPeople(int mid) throws SQLException {
			String sql = "SELECT * FROM MeetingTable WHERE mid = '"+mid+"'";
			rs = statement.executeQuery(sql);
			return rs;
		}
	//pidת��������
		public String pid2name(int pid)throws SQLException {
			String sql = "SELECT name FROM UserTable WHERE id = '"+pid+"'";
			rs = statement.executeQuery(sql);
			rs.next();
			return rs.getString("name");
		}
		
		
	// �ر����ݿ�����
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���ݿ��ʼ��
	public static void main(String[] args) throws SQLException {
		DBConnect db = new DBConnect();
		db.initiazation();
	}

}
