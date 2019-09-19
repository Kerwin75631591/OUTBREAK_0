package com.outbreak.dao;

import com.outbreak.entity.EmailPoster;

public class EmailPosterCL {
	public static void sendRegCheck(String receiver, int check) {
		EmailPoster.sendRegCheck(receiver, check);
	}
	public static void sendPwResetCheck(String receiver, int check) {
		EmailPoster.sendPwResetCheck(receiver, check);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//EmailPosterCL.sendRegCheck("toEmail@qq.com", 159836);
		EmailPosterCL.sendPwResetCheck("toEmail@qq.com", 584264);
	}

}
