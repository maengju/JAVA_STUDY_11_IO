package io;

import java.io.Serializable;

public class PersonDTO implements Serializable {//추상메소드가 아예 없는 인터페이스임

	private String name;
	private int age;
	private double height;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	
	
	
	
	
	
	
}



