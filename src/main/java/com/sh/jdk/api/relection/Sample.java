package com.sh.jdk.api.relection;

public class Sample {
	
	private int num;
	private String str;
	
	//생성자
	public Sample() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public Sample(int num, String str) {
		super();
		this.num = num;
		this.str = str;
	}

	//getet 
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	@Override
	public String toString() {
		return "Sample [num=" + num + ", str=" + str + "]";
	}
	
	

}
