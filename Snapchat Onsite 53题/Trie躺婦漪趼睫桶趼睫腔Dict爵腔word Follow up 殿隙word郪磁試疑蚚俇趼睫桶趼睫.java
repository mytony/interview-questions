import java.util.*;
class Solution {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
    String[] dictionary = {"a","aa","cat","cats","robot","aaa","cc"};
    // char[] ch = {'a','r','c','a','t','s'};
    char[] ch = {'a','a','c','a','t','s'};
    ArrayList<String> result = validWord(dictionary,ch);
    // System.out.println(result);
   }
    // 由string字符组成的的字典dict里的有效单词
    static ArrayList<String> validWord(String[]dictionary, char[] ch ){
        Trie trie = new Trie();
        for(String word : dictionary){
            trie.add(word);
        } 
        
        HashMap<Character,Integer> count = new HashMap<>();
        
        for(char c: ch){
            if(!count.containsKey(c)){
                count.put(c,1);
            }else{
                count.put(c,count.get(c)+1);
            }
        }
        
        ArrayList<String> result = new ArrayList<>();
        dfsTrie(trie.root, count, result);

        ArrayList<ArrayList<String>> resultII = new ArrayList<>();
        getcomb(0,result, count,resultII,new ArrayList<String>());
        System.out.println(resultII);

        return result;
    }
    
    static void dfsTrie(TrieNode root , HashMap<Character,Integer>count, ArrayList<String>result ){
        
        if(root.isWord) result.add(root.s);
        for(char ch: root.child.keySet()){
            if(count.containsKey(ch)&&count.get(ch)>0){
                count.put(ch,count.get(ch)-1);
                dfsTrie(root.child.get(ch), count, result);
                count.put(ch,count.get(ch)+1);
            }
        }
    } 

// follow up 若干string,若干dict,  返回由这些单词字符重新排序组成的word list
// bad cat是单词，字典是dad， tac， bat， cad， 返回［［dad，tac］，［bat，cad］］。单词里的字符要用完。我一开始有点懵，说先枚举，面试官提示说要用到第一题，因此就是用第一题返回的word list进行组合，找可以吧所给单词字符用光的单词组合。

    static void getcomb(int pos,ArrayList<String>dict,HashMap<Character,Integer>count, ArrayList<ArrayList<String>> resultII,ArrayList<String> list){
        if(countEqualsZero(count)){
            resultII.add(new ArrayList(list));
            return;
        }
        for(int i=pos; i<dict.size();i++){
            String word = dict.get(i);
            if(!validcount(word,count)) continue;
            
            for(char c : word.toCharArray()) { count.put(c,count.get(c)-1);}
            list.add(word);
            
            getcomb(i+1,dict, count, resultII,list);

            for(char c : word.toCharArray()) count.put(c,count.get(c)+1);
            list.remove(list.size()-1);
            }
        }
    
    static boolean countEqualsZero(HashMap<Character,Integer>count){
        for(int i : count.values()){
            if(i!=0) return false;
        }
        return true;
    }
    static boolean validcount(String word,HashMap<Character,Integer>count){
        for(char c : word.toCharArray()) { 
            if(count.get(c)<0) return false;
        }
        return true;
    }
        
        
     
    
    static class TrieNode{
        String s;
        boolean isWord;
        Map<Character, TrieNode> child;
        public TrieNode(){
            s = "";
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
            }
            root.isWord = true;
            root.s = word;
        }
    }

    
    
    
}