import java.util.*;
class Solution {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
    String[] rules = {"7.7.7.7/8","123.2.67.3/8"};
    // char[] ch = {'a','r','c','a','t','s'};
    String[] ip = {"23.2.4.1","7.3.4.1","5.1.2.3"};
    ArrayList<String> result = validWord(rules,ip);
     System.out.println(result);
   }
    
    static ArrayList<String> validWord(String[]rules, String[] IPList ){
         ArrayList<String> result = new ArrayList<>();
        Trie trie = new Trie();
        for(String r : rules){
            Rule rule = prepare(r,true);
            trie.add(rule.binaryIP,rule.blockBits);
        } 
        
        for(String s : IPList){
            Rule ip = prepare(s,false);
            if(!needBlock(trie.root,ip.binaryIP))
                result.add(s);
        }
        System.out.println(result);
        return result;
    }
    static boolean needBlock(TrieNode root, String ip){
        if(root.needBlock) return true;
        char digit = ip.charAt(0);
        if(root.child.containsKey(digit))
            if(needBlock(root.child.get(digit),ip.substring(1))) return true; 
        return false; 
    }

    static class Rule{
        String binaryIP;
        int blockBits;
        Rule(String binaryIP, int blockBits){
            this.binaryIP = binaryIP;
            this.blockBits = blockBits;
        }
    }

    static Rule prepare(String rule, boolean ruleOrIP){
            String[] part = ruleOrIP?rule.split("/"):new String[]{rule,"0"};
            String[] blockip = part[0].split("\\.");
            int blockBits = Integer.parseInt(part[1]);
            StringBuilder sb = new StringBuilder();
            for(String block : blockip){
                String binaryblock = Integer.toBinaryString(Integer.parseInt(block));
                //添加零，补齐8bit
                int leadingZero = 8 - binaryblock.length();
                while(leadingZero-->0) sb.append("0");
                sb.append(binaryblock);
            }
            return new Rule(sb.toString(),blockBits);
        }
    
    
     
    
    static class TrieNode{
        // int val;
        boolean needBlock;
        Map<Character, TrieNode> child;
        public TrieNode(){
            // val = 0;
            child = new HashMap<Character,TrieNode>();
        }
    }
    static class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        void add(String ip, int k){
            TrieNode root = this.root;
            for(int i = 0; i<k; i++){
                char digit = ip.charAt(i);
                if(!root.child.containsKey(digit))
                    root.child.put(digit,new TrieNode());
                root = root.child.get(digit);
            }
            root.needBlock = true;
        }
    }

    
    
    
}