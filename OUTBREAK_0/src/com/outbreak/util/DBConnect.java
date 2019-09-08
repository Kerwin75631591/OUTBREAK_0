package com.outbreak.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
			System.out.println("�޷��ҵ�������");
		}
	}

	// �������ݿ�
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/"
					+ "UserDB?user=root&password=749847569&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
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
		sql = "INSERT INTO UserTable(id ,email ,password ,phoneNumber , name , address,Public)values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, name);
		pstmt.setString(6, address);
		pstmt.setString(7, "0000");
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

	// UserTable�޸�����
	public void updateUserpassword(String email, String newPassword) throws SQLException {
		String sql = "UPDATE UserTable SET password = " + newPassword + " WHERE   email = '" + email + "'";
		System.out.println(sql);
		statement.executeUpdate(sql);
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
	public int insertMeeting(int state, String begintime, String endtime, String place, String name, String topic,
			String content, String host, int PeopleNum, int ArrivalNum, String FileUrl) throws SQLException {
		String sql = "SELECT id FROM MeetingTable ";
		rs = statement.executeQuery(sql);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		id = id + 1;
		sql = "INSERT INTO MeetingTable(id,begintime,endtime,place,name,topic,content,host,state,PeopleNum,ArrivalNum,FileUrl,Assessment)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2,begintime);
		pstmt.setString(3,endtime);
		pstmt.setString(4, place);
		pstmt.setString(5, name);
		pstmt.setString(6, topic);
		pstmt.setString(7, content);
		pstmt.setString(8, host);
		pstmt.setInt(9, state);
		pstmt.setInt(10, PeopleNum);
		pstmt.setInt(11, ArrivalNum);
		pstmt.setString(12, FileUrl);
		pstmt.setInt(13, 0);
		pstmt.addBatch();
		pstmt.clearParameters();
		pstmt.executeBatch();
		pstmt.clearBatch();

		return id;
	}

	// ��MeetingTable��ɾ������
	public void deleteMeeting(Date time, String name) throws SQLException {
		String sql = "DELETE FROM MeetingTable WHERE begintime = " + time + " And name = " + name;
		statement.execute(sql);
	}

	// MeetingTable����ĳ�˾ٰ�Ļ��飬����resultset
	public ResultSet searchMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE host = '" + host + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable������id���飬����resultset
	public ResultSet searchMeeting(int id) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE id = '" + id + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable��������δ�ύ�Ļ��飬����resultset
	public ResultSet searchChangableMeeting(String host) throws SQLException {
		String sql = "SELECT * FROM MeetingTable WHERE state = 0  And host = '" + host + "'";
		rs = statement.executeQuery(sql);
		return rs;
	}

	// MeetingTable������ͬ��ͬʱ����
	public boolean searchMeeting(Date time, String name) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "SELECT * FROM MeetingTable WHERE begintime = '" + sdf.format(time) + "' AND name = '" + name
				+ "'";
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
		statement.executeUpdate(sql);

	}
	// MeetingTable�޸�ĳ�����������״̬
	public void updateAssessment(int id) throws SQLException {
		String sql = "UPDATE MeetingTable SET Assessment = 1 WHERE   id = '" + id + "'";
		System.out.println(sql);
		statement.executeUpdate(sql);

	}

	// ��PeopleTable�м����µ�����
	public void insertPeople(int id, ArrayList<InvitedPeople> people) throws SQLException {
		System.out.println("insertPeople�ѽ���");
		Iterator<InvitedPeople> it = people.iterator();
		while (it.hasNext()) {
			InvitedPeople p = it.next();
			System.out.println(p.getName() + "+" + p.getEmail());

			String sql = "INSERT INTO PeopleTable(Mid,uid,TOF,Email,PhoneNum)values(?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, p.getName());
			pstmt.setBoolean(3, false);
			pstmt.setString(4, p.getEmail());
			pstmt.setString(5, p.getPhoneNumber());
			pstmt.addBatch();
			pstmt.clearParameters();
			pstmt.executeBatch();
			pstmt.clearBatch();

		}
	}

	// PeopleTable�������и�mid�Ļ��飬����resultset
	public ResultSet searchPeople(int mid) throws SQLException {
		String sql = "SELECT * FROM PeopleTable WHERE mid = '" + mid + "'";
		rs = statement.executeQuery(sql);
		return rs;
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

}
