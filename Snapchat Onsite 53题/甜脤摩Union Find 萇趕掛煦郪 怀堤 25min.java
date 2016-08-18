// 题目是手机上的通讯录，每条记录只有(name, number)这种pair,有些记录名字重复，有些记录号码重复，让我返回一个list<list<Record>>，
// 将所有记录按人分组。比较tricky的点在于(ABC,123), (ABC, 456), (BCD, 456)
// 三条记录，第一条和第三条也属于同一个人。要求时间复杂度尽量小

// 25min

import java.util.*;
public class Solution{
public static void main(String[] args){
	ArrayList<LinkedList<Record>> res = new ArrayList<LinkedList<Record>>();
	ArrayList<Record> records = new ArrayList<>();
	records.add(new Record("ABC",123));
	records.add(new Record("ABC",456));
	records.add(new Record("BCD",456));

	records.add(new Record("AD",342));
	records.add(new Record("AddD",11));
	records.add(new Record("DFX",34));
	records.add(new Record("AD",34));
	records.add(new Record("AD",11));

	
	res = GroupRecord(records);

}
public static ArrayList<LinkedList<Record>> GroupRecord(ArrayList<Record> records){
	UnionFind uf = new UnionFind(records);
	Map<String,Record> nameMap = new HashMap<>();
	Map<Integer,Record> numberMap = new HashMap<>();
	
	for(Record record : records){
		Record frecord = uf.find(record);
		
		if(nameMap.containsKey(record.name)){
			Record fname = uf.find(nameMap.get(record.name));	
			if(!frecord.equals(fname)){ 	
				uf.union(frecord,fname);	
			}
		}
		else{
			nameMap.put(record.name,record);	
		}
		
		if(numberMap.containsKey(record.number)){
			Record fnumber = uf.find(numberMap.get(record.number));
			if(!frecord.equals(fnumber)) {	
				uf.union(frecord,fnumber);
			}
		}
		else{
			numberMap.put(record.number,record);
		}
	}

	ArrayList<LinkedList<Record>> result = getGroup(uf, records);
	return result;

} 
public static ArrayList<LinkedList<Record>> getGroup (UnionFind uf, ArrayList<Record>records){
	Map<Record, LinkedList<Record>> groupMap = new HashMap<Record, LinkedList<Record>>();
	ArrayList<LinkedList<Record>> result = new ArrayList<LinkedList<Record>>();
	for(Record record : records){
		Record people = uf.find(record);	

		if(!groupMap.containsKey(people)){
			groupMap.put(people,new LinkedList<Record>());
		}
		groupMap.get(people).add(record);
	}
	
	for(Record people : groupMap.keySet()){
		result.add(groupMap.get(people));
		System.out.print(r.name+" "+r.number+",");
		System.out.println(groupMap.get(people).size());	
	}
	return result;
	
}

public static class Record{
	String name;
	int number;
	Record(String name, int number){
		this.name = name;
		this.number = number;
	} 
}
public static class UnionFind{
	Map<Record,Record> map;
	UnionFind(ArrayList<Record> records){
		map = new HashMap<>();
		for(Record record : records ){
			map.put(record,record);
		}
	}
	public void union(Record a, Record b){
		Record fa = find(a);
		Record fb = find(b);
		if(!fa.equals(fb))
			map.put(fa,fb);	
	}
	public Record find(Record a){
		Record fa = map.get(a);	
		while(!fa.equals(map.get(fa))){
			fa = map.get(fa);
		}
		Record x = a;
		while(!map.get(x).equals(fa)){
			Record tmp = map.get(x);
			map.put(x,fa);
			x = tmp;
		}
		return fa;
	}
}

}
