import java.util.*;
class Solution {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
    // 需要改 只有长度一样的 放在一个trie root
    String[] dictionary = {"like","god","internal","me","internet","interval","intension","face","intrusion"};
    List<String> res = new ArrayList<>();
    // Map<String,Trie> compress word map到 tri
    // if（!map.containsKey(compressWord)) 
    //    map.put(Compressword, new Trie); 
    // map.get(compressWord).add(word);
    // 
    // get Map的keySet 建不同根的trie, 
    Trie trie = buildTrie(dictionary);    
    for(String word : dictionary){        
      TrieNode root = trie.root.child.get(word.charAt(word.length()-1));
      res.add(compress(word,root,"")); 
    }
    
     System.out.println(res);
  }
    
    //group trie by last char of words, append last char to the begining of each word
    static Trie buildTrie(String[]dictionary){
        Trie trie = new Trie();
        for(String word : dictionary){
           word = word.charAt(word.length()-1)+word; 
           trie.add(word);
        }
        return trie;
    }
    
    //dfs traverse trie, get prefix until trie node count equals 1
    static String compress(String word,TrieNode root,String prefix){   
        char ch = word.charAt(0);
        prefix += ch;
        if(root.child.get(ch).count==1){
            String orig = prefix + word.substring(1);
            String abbr = prefix + orig.length() + word.charAt(word.length()-1);
            return orig.length()<=abbr.length()? orig : abbr;
        }
        return compress(word.substring(1),root.child.get(ch),prefix);
        
    } 
    
   static class TrieNode{
        int count;
        boolean isWord;
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
              root.count++;  
              root = root.child.get(ch);
            }
            root.isWord = true;
        }
    }
   
}