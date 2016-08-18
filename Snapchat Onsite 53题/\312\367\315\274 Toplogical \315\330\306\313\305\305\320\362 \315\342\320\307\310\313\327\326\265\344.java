public class Solution {
    public String alienOrder(String[] words) {
    
    Map<Character, Set<Character>> graph =new HashMap<Character, Set<Character>>();
    Map<Character, Integer> degree=new HashMap<Character, Integer>();
    
    StringBuilder result= new StringBuilder();
    if(words==null || words.length==0) return "";
    
    //initial
    for(String s: words){
        for(char c: s.toCharArray()){
            degree.put(c,0);
            graph.put(c,new HashSet<>());
        }
    }
    
    int i = 1;
    while ( i < words.length) {
        
        String s1=words[i-1];
        String s2=words[i];
        int minLen=Math.min(s1.length(), s2.length());
        
        for(int j=0; j<minLen; j++){
            
            char c1=s1.charAt(j);
            char c2=s2.charAt(j);
            
            if(c1!=c2){
                Set<Character> neibors = graph.get(c1);
                
                if(!neibors.contains(c2)){
                    neibors.add(c2);
                    graph.put(c1, neibors);
                    degree.put(c2, degree.get(c2)+1);
                }
                break;
            }
        }
        i++;
    }
    
    Queue<Character> q=new LinkedList<Character>();
    for(char node: degree.keySet()){
        if(degree.get(node)==0) 
            q.offer(node);
    }
    
    while(!q.isEmpty()){
        char node=q.poll();
        result.append(node);
        for(char n: graph.get(node)){
            degree.put(n,degree.get(n)-1);
            if(degree.get(n)==0) {
                q.offer(n);
                // 只要每一轮多个degree==0的结点被加入，即结果不唯一
            }
        }
    }
    
    if(result.length()!=degree.size()) return "";
    
    return result.toString();

 }
}