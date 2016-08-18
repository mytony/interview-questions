// 给两个string的数组和一个pattern数组，判断将两个string数组分别和
// pattern转化后的结果是否相同。
// 例如s1={"abc", "a", "ccc"}, s2={"bc", "aa", "d"}, 
// pattern={1, 0}，则s1和p的转化结果是"aabc"，
// s2和p的转化结果也是是"aabc"，则返回true。
// 如果pattern是{0, 1}，则转化结果分别是"abca", "bcaa"，则返回false。
// followup是，如果给定s1和s2，判断是否存在一个pattern使得转化结果一致
import java.util.*;
class solution{
public static void main(String[] args){
	String[] A = new String[]{"abc","a","ccc"};
	String[] B = {"bc","aa","d"};
	int[] p = new int[]{1,0};
	System.out.println(isMatch(A,B,p));

	// String[] C = {"a","ab","cdef"};
	// String[] D = {"aabc","de","f"};
	// String[] C = {"de","a", "ghf",  "abc"};
	// String[] D = {"h","aab","f","cdeg"};
	String[] C = {"a","f","bc","ccc","ef","abce"};
	String[] D = {"aa","ef","bce","d","f","bc"};
	System.out.println(existPattern(C,D));
}
public static boolean isMatch(String[]A, String[]B, int[]pattern){
	StringBuilder a = new StringBuilder();
	StringBuilder b = new StringBuilder();
	for(int idx : pattern){
		a.append(A[idx]);
		b.append(B[idx]);
	}
	return a.toString().equals(b.toString());
}

public static boolean existPattern(String[]A, String[]B){
  List<Integer> list = new ArrayList<>();
  for(int i=0; i<A.length;i++){
    Prefix prefix = getPrefix(A[i],B[i]);
    if(prefix.isPrefix){
      Deque<String> qA = new LinkedList<>();
      Deque<String> qB = new LinkedList<>();
      if(prefix.AisPrefixOfB) qB.add(prefix.suffix); 
      else qA.add(prefix.suffix);
      list.add(i);
	  if(dfs(A, B, list, prefix.AisPrefixOfB,qA,qB)) return true;
	  list.remove(i);
    }
  }
  return false;
}

public static boolean dfs(String[]A, String[]B, List<Integer> list, boolean chooseA,Deque<String> qA,Deque<String> qB){
 	
 	if(qA.isEmpty()&&qB.isEmpty()) {
 	  System.out.println(list);
 	  return true;
 	}

 	Deque<String> first = chooseA ? qA : qB;
	Deque<String> second = chooseA ? qB : qA;
 	String suffix = second.poll();
	String target = first.poll();
	
 	if(target!=null){
 		Prefix prefix = getPrefix(suffix,target);
		if(!prefix.isPrefix) return false; //pruning 	
	  	updateDeque(first,second,prefix);
		if(dfs(A, B, list, chooseA^prefix.AisPrefixOfB, qA, qB)) return true;
 	}

 	for(int i=0; i<A.length;i++){
 	  if(list.contains(i)) continue;
	  Prefix prefix = getPrefix(suffix, chooseA?A[i]:B[i]);
	  if(!prefix.isPrefix) continue;
  	  updateDeque(first,second,prefix);
	  second.add(chooseA?B[i]:A[i]);
	  list.add(i);
	  if(dfs(A, B, list, chooseA^prefix.AisPrefixOfB, qA, qB)) return true;
	  list.remove(i);
	}
 	return false;
}
public static void updateDeque(Deque<String> first,Deque<String> second,Prefix prefix){
	if(prefix.suffix.length()!=0){
		if(prefix.AisPrefixOfB) first.add(prefix.suffix);
		else second.addFirst(prefix.suffix);
	}
}


static class Prefix{
	boolean isPrefix;
	String suffix;
	boolean AisPrefixOfB;
	Prefix(boolean isPrefix, String suffix, boolean AisPrefixOfB){
		this.isPrefix = isPrefix;
		this.suffix = suffix;
		this.AisPrefixOfB = AisPrefixOfB;
	}
}

public static Prefix getPrefix(String a, String b){
	boolean AisPrefixOfB = true;
	if(a.length()>b.length()){
		String tmp = b;
		b = a;
		a = tmp;
		AisPrefixOfB = false;
	}
	int i;
	for(i=0 ; i<a.length();i++){
	  if(a.charAt(i)!=b.charAt(i)) return new Prefix(false,"",false);
	}
	return new Prefix(true,b.substring(i),AisPrefixOfB);
}

}