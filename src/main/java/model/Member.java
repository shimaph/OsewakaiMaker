package model;

import java.io.Serializable;

public class Member implements Serializable{
	private String name;
	private String party;
	private int grade;
	private int sex;
	private int exp;
	
	public Member(){}
	
	public Member(String name, String party, int grade, int sex, int exp) {
		this.name = name;
		this.party = party;
		this.grade = grade;
		this.sex = sex;
		this.exp = exp;
		
	}

	public String getName() {
		return name;
	}

	public String getParty() {
		return party;
	}

	public int getGrade() {
		return grade;
	}

	public int getSex() {
		return sex;
	}

	public int getExp() {
		return exp;
	}
}
