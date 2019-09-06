package com.outbreak.entity;

public class Decoder {
	public static String decode(String string) {
		String s="";
		for(int i=0;i<string.length();i++){
			switch(i%3){
			case 0:
				s+=(char)((int)string.charAt(i)-1);
				break;
			case 1:
				s+=(char)((int)string.charAt(i)-2);
				break;
			case 2:
				s+=(char)((int)string.charAt(i)-3);
				break;
			}
		}
		return s;
	}
	public static void testDecode() {
		System.out.println(Decoder.decode("n{q66999"));
	}
	public static void main(String[] args) {
		Decoder.testDecode();
	}
}
