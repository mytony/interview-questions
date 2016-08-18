// Given List<word>, String para, 返回包涵所有word最短的一段话,返回String 
import java.util.*;
class Solution {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
    String[] words = {"dod","dict"};
    String s = "dodictdodictabcd";
    minSubstring(words , s);

   }

   // 如果Para是隔开的， 用LRU实现，遍历一遍即可。
   // for(i-->dict.size()){
   // word = para[i];
   // if(dict.contains(word));
   // LRU.put(word,i);//插入到tail前，如果LRU已含有word,修改index,移到tail前
   // if(LRU.size()==dict.size()){
   //      min = Math.min(min, LRU.tail.prev.idx-LRU.head.next.idx);
   //      LRU.remove(head.next);//删除第一个dict含有的单词
   //   }
   // }


    
    static void minSubstring(String[] words, String s ){
        Trie trie = new Trie();
        for(String word : words){
            trie.add(word);
        } 
        for(int i=0; i<s.length();i++){
            int[] idx = new int[]{i,i};
            search(trie, trie.root,s,idx,words.length);
        }
    }

   
    static void search(Trie trie, TrieNode node, String s, int[] idx, int wordCount){

        if(node.isWord){
            wordCount--;
            if(wordCount!=0){
                node = trie.root; 
                search(trie, node, s, idx,wordCount); //回头从新找下一个
            }
            else{
                System.out.println("minSubstring:   "+ s.substring(idx[0],idx[1]));
                return;
            }
        }


        if(idx[1]<s.length()){
            char ch = s.charAt(idx[1]++);
            if(!node.child.containsKey(ch)) 
                search(trie, node, s, idx, wordCount);
            
            if(node.child.containsKey(ch)&&node.child.get(ch).count>0){
                node.child.get(ch).count--;
                search(trie, node.child.get(ch), s, idx,wordCount);
                node.child.get(ch).count++;
            }
        }            

    } 
    
    static class TrieNode{
        boolean isWord;
        int count;
        Map<Character, TrieNode> child;
        public TrieNode(){
            count = 0;
            child = new HashMap<Character,TrieNode>();
        }
    }
    static class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        void add(String word){
            TrieNode root = this.root;
            for(char ch : word.toCharArray()){
                if(!root.child.containsKey(ch))
                    root.child.put(ch,new TrieNode());
                root = root.child.get(ch);
                root.count++;
            }        
            root.isWord = true;
        }
    }

    
    
    
}