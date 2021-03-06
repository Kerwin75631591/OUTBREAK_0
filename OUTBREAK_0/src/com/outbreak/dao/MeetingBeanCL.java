package com.outbreak.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.outbreak.entity.MeetingBean;
import com.outbreak.util.DBConnect;
public class MeetingBeanCL {
	public DBConnect db;
	
	public MeetingBeanCL() {
		db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//会议创建，j=true时为提交，j=false时为保存草稿
	public boolean create(MeetingBean mb,boolean j) {
		boolean judge = true;
		int state=99;
		/*try {
			judge = db.searchMeeting(mb.getBegintime(),mb.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		if (!judge) {
			return false;
		}
		if(j)
			state=1;
		else
			state=0;
		try {
			int id=db.insertMeeting(state,mb.getBegintime(),mb.getEndtime(),mb.getPlace(),mb.getName(),mb.getTopic(),mb.getContent()
					,mb.getHost(),mb.getPeopleNum(),mb.getArrivalNum(),mb.getFileUrl());
			db.insertPeople(id,mb.getPeople());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//会议查询
	public ResultSet search(String host) {
		try {
			System.out.println("search over");
			return db.searchMeeting(host);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
