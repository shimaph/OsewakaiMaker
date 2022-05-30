package model;

import java.util.ArrayList;

public class ShowMembersLogic {
	public ArrayList<Member> execute(ArrayList<Member> memberList) {
		
		memberList.sort((Member a , Member b) -> Integer.toString(a.getGrade()).compareTo(Integer.toString(a.getGrade())));
	
		
		return memberList;
	}

}
