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

    Set<String> dict = new HashSet<String>();
    dict.add("hot");
    dict.add("dot");
    dict.add("dog");
    dict.add("lot");
    dict.add("log");
    List<List<String>> ladders = findLadders("hit","cog",dict);
    System.out.println(ladders);
    
    
  }
      public static int ladder1(String start, String end, Set<String> dict) {
        // write your code here
        if (dict == null || dict.size() == 0) {
            return 0;
        }
        dict.add(end);
        HashSet<String> hash = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        hash.add(start);
        
        int length = 1;
        while(!queue.isEmpty()) {
            length++;// add one when distance +1
            int size = queue.size();// loop in same distance level
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord: getNextWords(word, dict)) {
                     if (nextWord.equals(end)) {
                        return length;
                    }  
                     if (!hash.contains(nextWord)) {
                        hash.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }
        }
        return 0;
    }

   public static List<List<String>> findLadders(String start, String end,
        Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> graph = new HashMap<String, List<String>>(); // 临接表
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
        
        for (String s : dict) {
            graph.put(s, new ArrayList<String>());
        }
        
        bfs(graph, distance, start, dict);
        dfs(ladders, new ArrayList<String>(), end, start, distance, graph);

        return ladders;
    }

   static void dfs(List<List<String>> ladders, List<String> path, String crt,
            String start, Map<String, Integer> distance,
            Map<String, List<String>> graph) {
        
       
        if (crt.equals(start)) {
            path.add(crt);
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
            path.remove(path.size() - 1);
            return;
        } 
        
        for (String next : graph.get(crt)) {
            if (distance.get(crt)-1 == distance.get(next)) { 
                path.add(crt);
                dfs(ladders, path, next, start, distance, graph);
                path.remove(path.size() - 1);
            }
        }           
        
    }

   static void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, Set<String> dict) {
        
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);

        while (!q.isEmpty()) {
            String crt = q.poll();
            for (String next : getNextWords(crt, dict)) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) { //相当于visited的记录
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    private static ArrayList<String> getNextWords(String word, Set<String> dict) {
        
      ArrayList<String> nextWords = new ArrayList<String>();
        
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                char[] chars = word.toCharArray();
                chars[i] = c;
                String nextWord = new String(chars);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
  
  
}
