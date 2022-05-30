package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class OsewakaiArg {
	public ArrayList<ArrayList<Member>> execute(ArrayList<Member> memberList, int num) {
		
		ArrayList<ArrayList<ArrayList<Member>>> groupslist = new ArrayList<ArrayList<ArrayList<Member>>>();
		
		ArrayList<ArrayList<Member>> memberListByGrade = memberListByGrade(memberList);
		
		
		
		ArrayList<ArrayList<Member>> groups = defaultArg(memberListByGrade.get(0), num);
		groups = checkBalance(groups, memberListByGrade.get(1));
		groups = lowerGrades(groups, memberListByGrade.get(2));
		groups = lowerGrades(groups, memberListByGrade.get(3));
		Collections.shuffle(groups);
		
		return groups;
	}
	
	//2期以降
	public ArrayList<ArrayList<Member>> lowerGrades(ArrayList<ArrayList<Member>> input_groups, ArrayList<Member> list){
		
		ArrayList<ArrayList<Member>> groups = new ArrayList<ArrayList<Member>>();
		ArrayList<Member> shuffledList = new ArrayList<Member>();

		
		for(int i = 0; i < 5; i++) {
			groups = new ArrayList<ArrayList<Member>>();
			groups = input_groups;
			shuffledList = new ArrayList<Member>();
			Collections.shuffle(list);
			shuffledList = list;
			
			int max = groups.get(0).size();
			//1個目のお世話会の人数に合わせる
			for(ArrayList<Member> group : groups) {
				int added = 0;
				
				if(shuffledList.size() == 0) {
					break;
				}
				
				if(group.size() < max) {
					if(checkLeanSex(group) == 0) {
						for(Member mem : shuffledList) {
							if(mem.getSex() == 1) {
								group.add(mem);
								added++;
								break;
							}
						}
					}else if(checkLeanSex(group) == 1) {
						for(Member mem : shuffledList) {
							if(mem.getSex() == 0) {
								group.add(mem);
								added++;
								break;
							}
						}
					}else {
						group.add(shuffledList.get(0));
						added++;
					}
					
					if(added == 0) {
						group.add(shuffledList.get(0));
					}
					shuffledList.remove(0);
				}
			}
			
			
			
			if(shuffledList.size() != 0) {
				for(ArrayList<Member> group : groups) {
					int added = 0;
					
					if(shuffledList.size() == 0) {
						break;
					}
					
					if(checkLeanSex(group) == 0) {
						for(Member mem : shuffledList) {
							if(mem.getSex() == 1) {
								group.add(mem);
								added++;
								break;
							}
						}
					}else if(checkLeanSex(group) == 1) {
						for(Member mem : shuffledList) {
							if(mem.getSex() == 0) {
								group.add(mem);
								added++;
								break;
							}
						}
					}else {
						group.add(shuffledList.get(0));
						added++;
					}
					
					if(added == 0) {
						group.add(shuffledList.get(0));
					}
					shuffledList.remove(0);
					
				}
				
			}
		}
		
		return groups;
	}
	
	//4期の結果を伺いながら3期を振り分ける
	public ArrayList<ArrayList<Member>> checkBalance(ArrayList<ArrayList<Member>> groupS, ArrayList<Member> lisT){
		
		ArrayList<ArrayList<Member>> groups = new ArrayList<ArrayList<Member>>();
		
		for(int i = 0; i < 5; i++) {
			groups = new ArrayList<ArrayList<Member>>();
			groups = groupS;
			ArrayList<Member> list = new ArrayList<Member>();
			list = lisT;
			ArrayList<ArrayList<Member>> adding_listlist = divideByExp(list);
			Collections.shuffle(adding_listlist.get(0));
			Collections.shuffle(adding_listlist.get(1));
			ArrayList<Member> adding_list = adding_listlist.get(1);
			adding_list.addAll(adding_listlist.get(0));
			
			int max = groups.get(0).size();
			
			//1個目のお世話会の人数に合わせる
			for(ArrayList<Member> group : groups) {
				if(adding_list.size() == 0) {
					break;
				}
				
				if(group.size() < max) {
					group.add(adding_list.get(0));
					adding_list.remove(0);
				}
			}
			
			//残りを後ろから入れる
			for(ArrayList<Member> group : groups) {
				if(adding_list.size() == 0) {
					break;
				}
				Collections.reverse(adding_list);
				group.add(adding_list.get(0));
				adding_list.remove(0);
			}
			
			//3期の人数がおバカだった時用
			if(adding_list.size() != 0) {
				while(adding_list.size() > 0) {
					for(ArrayList<Member> group : groups) {
						if(adding_list.size() == 0) {
							break;
						}
						group.add(adding_list.get(0));
						adding_list.remove(0);
					}
				}
			}
			
			if(!checkPartyOverlap(groups)) {
				return groups;
			}
		}
		
		return groups;
	}
	
	//4期用
	public ArrayList<ArrayList<Member>> defaultArg(ArrayList<Member> list, int num){
		
		ArrayList<ArrayList<Member>> dividedList = divideByExp(list);
		
		ArrayList<ArrayList<Member>> fixedList = new ArrayList<ArrayList<Member>>();
		
		//5回シャッフルし、パーティ被りを避ける
		for(int i = 0; i < 5; i++) {
			Collections.shuffle(dividedList.get(0));
			Collections.shuffle(dividedList.get(1));
			fixedList = toEachGroup(dividedList, num);
			if(!checkPartyOverlap(fixedList)) {
				break;
			}
		}
		return fixedList;
	}
	
	//性別の分布チェック
	public int checkLeanSex(ArrayList<Member> list) {
		int lean = -1;
		
		int count = 0;
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i-1).getSex() == list.get(i).getSex()) {
				count+=1;
			}
		}
		if(list.size()-1 == count) {
			lean = list.get(0).getSex();
		}	
		return lean;
	}
	
	//各お世話会に同じパーティの人がいるかチェック
	public boolean checkPartyOverlap(ArrayList<ArrayList<Member>> groupList) {
		
		for(ArrayList<Member> group : groupList) {
			Set<String> linkedHashSet = new LinkedHashSet<String>();
			ArrayList<String> parties = new ArrayList<String>();
			
			for(Member member : group) {
				linkedHashSet.add(member.getParty());
				parties.add(member.getParty());
			}
			
			if(linkedHashSet.size() != parties.size()) {
				return true;
			}	
		}
		return false;
	}
	
	//各お世話会に代入
	public ArrayList<ArrayList<Member>> toEachGroup(ArrayList<ArrayList<Member>> list, int num){
		ArrayList<Member> listConcat = list.get(0);
		listConcat.addAll(list.get(1));
		
		ArrayList<ArrayList<Member>> groupList = new ArrayList<ArrayList<Member>>();
		
		for(int i = 0; i < listConcat.size()/num+1; i++) {
			for(int j = 0; j < num; j++) {
				
				try {
					listConcat.get(i*num+j);
				}catch(IndexOutOfBoundsException e) {
					break;
				}
				
				if(i>0){
					groupList.get(j).add(listConcat.get(i*num+j));
				}else{
					ArrayList<Member> addingList = new ArrayList<Member>();
					addingList.add(listConcat.get(i*num+j));
					groupList.add(addingList);
				}
		
				
			}
	
		}
		return groupList;
	}
	
	//経験者未経験者を分ける
	public ArrayList<ArrayList<Member>> divideByExp(ArrayList<Member> memberList){
		ArrayList<Member> exps = new ArrayList<Member>();
		ArrayList<Member> nonExps = new ArrayList<Member>();
		
		for(int i = 0;i < memberList.size();i++) {
			if(memberList.get(i).getExp() == 0) {
				nonExps.add(memberList.get(i));
			} else {
				exps.add(memberList.get(i));
			}
		}
		
		ArrayList<ArrayList<Member>> dividedList = new ArrayList<ArrayList<Member>>();
		
		dividedList.add(nonExps);
		dividedList.add(exps);
	
		return dividedList;
	}
	
	//memberListを受け、期ごとにリストになっている多次元配列を返す
	public ArrayList<ArrayList<Member>> memberListByGrade(ArrayList<Member> memberList){
		
		ArrayList<ArrayList<Member>> memberListByGrade = new ArrayList<ArrayList<Member>>();
		
		for(int i = 4; i > 0; i--) {
			memberListByGrade.add(searchByGrade(memberList,i));
		}
		return memberListByGrade;
	}
	
	//memberListと任意の期を受け、期だけのリストを返す
	public ArrayList<Member> searchByGrade(ArrayList<Member> memberList, int grade){
		
		ArrayList<Member> byGrade = new ArrayList<Member>();
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).getGrade() == grade) {
				byGrade.add(memberList.get(i));
			}
		}
		return byGrade;
	}

}
