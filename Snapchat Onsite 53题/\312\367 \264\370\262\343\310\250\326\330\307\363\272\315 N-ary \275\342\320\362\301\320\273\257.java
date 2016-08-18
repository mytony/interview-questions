// 权重求和

import java.util.*;
class Solution {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
   
    String s = "[1,[3,[4,5],6],7]";
    String s1= "[1,[2,3],[[4]]],"; 
    String s2= "[1,[3,[4]]],"; //23
    String s3= "[[2,[4]]],"; //20
    String s4= "[1,[[4]]],"; //13


    
    
	// StringBuilder sb = new StringBuilder("01)03)04)5))6))7))");
	StringBuilder sb = new StringBuilder(s2);
	Wrapper de = deserialize(sb);

    System.out.println(weightSum2(de,1,1));

    StringBuilder sb1 = new StringBuilder();
    serialize(de, sb1);
    System.out.println(sb1.toString());
  }

    static Wrapper deserialize(StringBuilder sb){
  		char val = sb.charAt(0);
  		sb.deleteCharAt(0);
        if (val==']'||val==',') return null;
        Wrapper root = new Wrapper(val=='[' ? 0 : val-'0');
        while(sb.length()>0){
        	Wrapper node = deserialize(sb);
            if(node!=null) 
            	root.nodes.add(node);
            else break;
		}
        return root;    
  }

  static void serialize(Wrapper root, StringBuilder sb){ 	
  	sb.append(root.val);
  	for(Wrapper node : root.nodes){
   		serialize(node, sb);
   	}
   	sb.append(")");
  }




      static int weightSum2(Wrapper root, int depth, int weight){
      int n = root.nodes.size();
      System.out.println(n);
      // 叶节点返回值
      if(n==0) return weight*root.val;    
     // 如果有"[[[4]]],"， 需要return weight*root.val
     // String s2= "[1,[3,[4]]],"; 这种最里层的数退出也需要乘外层weight
      int weight1 = n==1 ? depth : 1;
      int weight2 = n==1 ? 1 : depth;
      int sum = 0;      
      for(int i = 0; i<n ; i++){
        Wrapper node = root.nodes.get(i);
        // System.out.println(node.val);
        //[1,[3,[4]]] 如果要求4系数是2而不是3的话，dfs进入4时weight要重新从1开始累积  
        sum += (weight2)*(weightSum2(node, i!=0&&node.val==0?2:depth+1, weight1));
        // sum += (weight2)*(weightSum2(node, depth+1, weight1)); //[1,[3,[4]]] 4系数3 
         
      }
      return sum;
   }
    
   static class Wrapper{
        int val;
        ArrayList<Wrapper> nodes;
        public Wrapper(int val){
            this.val = val;
            this.nodes = new  ArrayList<Wrapper>();
        }
    }
   
}
