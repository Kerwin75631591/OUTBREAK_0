package server.OUTBREAK_1.entity;

import java.util.ArrayList;
import java.util.Date;

public class MeetingBean {
	private Date begintime;//���鿪ʼʱ��
	private Date endtime;//�������ʱ��
	private String place;//����ص�
	private String name;//��������
	private String topic;//��������
	private String content;//��������
	private String host;//����ٰ췽
	private int state;//0.δ�ύ 1.����� 2.���ͨ�� 3.���δͨ�� 4.�����
	private int PeopleNum;//������������
	private int ArrivalNum;//���鵽������
	ArrayList<InvitedPeople> People;//���������ֵ��
	private String FileUrl;//�����ļ�·��

	public MeetingBean() {
		PeopleNum=0;
		ArrivalNum=0;
		People =new ArrayList<InvitedPeople>();
	}
	
	public void addpeople(String name,String email,String phoneNumber) {
		InvitedPeople temp=new InvitedPeople();
		temp.setEmail(email);
		temp.setName(name);
		temp.setPhoneNumber(phoneNumber);
		People.add(temp);
	}
	
	public ArrayList<InvitedPeople> getPeople() {
		return People;
	}


	public void setPeople(ArrayList<InvitedPeople> people) {
		People = people;
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

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	
}
