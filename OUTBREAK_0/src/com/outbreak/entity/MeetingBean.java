package com.outbreak.entity;

import java.util.Date;

public class MeetingBean {
	private Date time;//会议时间
	private String place;//会议地点
	private String name;//会议名称
	private String topic;//会议主题
	private String content;//会议内容
	private String host;//会议举办方
	private int state;//0.未提交 1.待审核 2.审核通过 3.审核未通过 4.已完成
	private int PeopleNum;//会议邀请人数
	private int ArrivalNum;//会议到达人数
	InvitedPeople People;//到达情况键值对
	private String FileUrl;//会议文件路径

	public MeetingBean() {
		PeopleNum=0;
		ArrivalNum=0;
		People =new InvitedPeople();
	}
	
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getFileUrl() {
		return FileUrl;
	}

	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}
	
	public int getArrivalNum() {
		return ArrivalNum;
	}

	public void setArrivalNum(int arrivalNum) {
		ArrivalNum = arrivalNum;
	}

	public int getPeopleNum() {
		return PeopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		PeopleNum = peopleNum;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public InvitedPeople getPeople() {
		return People;
	}

	public void setPeople(InvitedPeople people) {
		while(People.getNext()!=null) {
			People=People.getNext();
		}
		People.setNext(people)  ;
	}
	
	
}
