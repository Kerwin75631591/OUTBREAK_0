package com.outbreak.entity;

import java.util.Date;

public class MeetingBean {
	private Date time;//����ʱ��
	private String place;//����ص�
	private String name;//��������
	private String topic;//��������
	private String content;//��������
	private String host;//����ٰ췽
	private int state;//0.δ�ύ 1.����� 2.���ͨ�� 3.���δͨ�� 4.�����
	private int PeopleNum;//������������
	private int ArrivalNum;//���鵽������
	InvitedPeople People;//���������ֵ��
	private String FileUrl;//�����ļ�·��

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
