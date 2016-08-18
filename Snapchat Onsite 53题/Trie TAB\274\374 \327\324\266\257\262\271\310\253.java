import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class Solution {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
    String[] dictionary = {"abcefegddf","abc","ab","catb","cats","robot","catsa","abcd","cc"};
    // String[] dictionary = {};
    Trie tree = new Trie();
    for(String s : dictionary){
      tree.add(s);
    }
    List<String> result = new ArrayList<String>();
    tab(result, tree.root,"c","");
    System.out.println(result);
    
    System.out.println(shortest(tree.root.child.get('c'),"c",1));


  }
   public static void tab(List<String> result, TrieNode root, String s, String prefix ){
     if(s.length()==0){
       if(root.child.size()==1){
         findCommon(result,root,new StringBuilder(prefix));
       }
       else{
        findMatch(result,root,new StringBuilder(prefix));
       }
       return;
     }

     char ch = s.charAt(0);
     prefix += ch;
     tab(result, root.child.get(ch),s.substring(1),prefix);
}


// 会自动补全 需要输入的最短字符数
   public static int shortest(TrieNode root, String s, int length){
//  
     if((root.count==1&&!root.isWord)||s.length()==0) return length;
     char ch = s.charAt(0);
     return shortest(root.child.get(ch),s.substring(1),length+1);    
   }
   
   public static void findCommon(List<String> result, TrieNode root, StringBuilder prefix ){
     if(root.isWord || root.child.size()>1){
       result.add(prefix.toString());
       return;
     }
     for(char ch : root.child.keySet()){
       prefix.append(ch);
       findCommon(result,root.child.get(ch),new StringBuilder(prefix));
     }  
   }
   
   public static void findMatch(List<String> result, TrieNode root, StringBuilder prefix ){
     if(root.isWord)  result.add(prefix.toString());
     for(char ch : root.child.keySet()){
       prefix.append(ch);
       findMatch(result,root.child.get(ch),new StringBuilder(prefix));
       prefix.setLength(prefix.length()-1);
     }
   }
}  
class TrieNode{
  boolean isWord;
  String s;
  int count;
  Map<Character,TrieNode> child;
  TrieNode(){
    count= 0;
    child = new HashMap<>();
  }
}
class Trie{
  TrieNode root;
  Trie(){
    root = new TrieNode();
  }
  public void add(String word){
    TrieNode node = root;
    for(int i=0; i<word.length();i++){
      char ch = word.charAt(i);
      if(!node.child.containsKey(ch))
        node.child.put(ch, new TrieNode());
      node.count++;
      node = node.child.get(ch);
    }
    node.isWord = true;
    node.s = word;
  }
}

