// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.

import java.util.*;
class Solution{
	 public static void main(String[] args) {
     	ArrayList<Data> records = new ArrayList<>();
     	records.add(new Data("Alice","",5));
	    records.add(new Data("Carol","Bob",2));
     	records.add(new Data("David","Bob",3));
     	records.add(new Data("Bob","Alice",3));
		  records.add(new Data("Richard","Carol",5));
	   	records.add(new Data("Tom","Carol",5));
		  records.add(new Data("Ferris","Eve",1));

     	records.add(new Data("Kim","Richard",5));

     	records.add(new Data("Eve","Alice",2));
		  
      companyPrint(records);


    Employer employer = map.get("Alice");
    Employer boss = map.get("");
    Employer a = map.get("Tom");
    Employer b = map.get("Bob");
  	Employer LCA = findLCA(employer,boss,a,b);
  	System.out.println(LCA.name);

	 }
  
  static void companyPrint(ArrayList<Data> records){
  	Employer ceo = buildTree(records);
  	totlaSale(ceo); 
  	print2(ceo,"");  
    print(ceo);  
      
  }

  static Employer findLCA(Employer employer, Employer boss, Employer a, Employer b){
  	if (employer.equals(a) || employer.equals(b)) return boss;
  	Employer first = null;
  	Employer second = null;
  	for(Employer subordinate : employer.subordinates){
  		Employer LCA = findLCA(subordinate, employer, a, b);
  		if(LCA!=null)
  			if(first==null) first = LCA;
  			else if(second==null) second = LCA;
  	}
	  if (first != null && second != null) return employer;
	  if (first == null) return second;
    if (second == null) return first;
  	return null;
}

  static Map<String,Employer> map = new HashMap<>();
  static Employer buildTree(ArrayList<Data> records){
  	
  	for(Data record : records){
  		if(!map.containsKey(record.person)){
            map.put(record.person,new Employer(record.person));
  		}
  		if(!map.containsKey(record.boss)){
            map.put(record.boss,new Employer(record.boss));
  		}
  		map.get(record.person).sold = record.sold;
  		map.get(record.person).totlaSale = record.sold;
  		map.get(record.boss).subordinates.add(map.get(record.person));
  	}
    return map.get("").subordinates.get(0);

  }

   static int totlaSale(Employer employer){
  	if(employer.subordinates.size()==0)
  		return employer.totlaSale;
	  for(Employer subordinate : employer.subordinates){
		  employer.totlaSale += totlaSale(subordinate);
	   }
	 return employer.totlaSale;
  }

  static void print(Employer employer){
    String prefix = employer.prefix;
		System.out.println(prefix + employer.name + " " + employer.totlaSale);
  	    int n = employer.subordinates.size()-1;
  	    for(int i = 0; i <= n; i++){
  	    	String s;    	
  	    	if(prefix.length()==0)
  	    		s = (i==n) ? "\\_" : "|-";
  	    	else{
  	    		String p = prefix.substring(0,prefix.length()-2);
  	    		if(i==n)
  	    			s = prefix.charAt(prefix.length()-2)=='\\'? p + " \\_" : p  + "|\\_";
  	    		else
  	    			s = prefix.charAt(prefix.length()-2)=='\\'? p + " |-" : p + "||-";
  	    	}
          employer.subordinates.get(i).prefix = s;
  	    	print(employer.subordinates.get(i));
  	    }		
  }
    static void print2(Employer employer, String prefix){
		  System.out.println(prefix + employer.name + " " + employer.totlaSale);
  	    int n = employer.subordinates.size()-1;
  	    for(int i = 0; i <= n; i++){
  	    	print2(employer.subordinates.get(i), prefix+"  ");
  	    }		
  }
}

  class Employer{
  	String name;
  	int sold;
  	int totlaSale;
  	String prefix;
    List<Employer> subordinates;
    public Employer(String name){
    	this.name = name;
    	this.totlaSale = 0;
    	prefix = "";
    	subordinates = new ArrayList<>();
    }
  } 

  class Data{
  	String person;
  	String boss;
  	int sold;
  	public Data(String person, String boss, int sold){
  		this.person = person;
  		this.boss = boss;
  		this.sold = sold;
  	}
  }