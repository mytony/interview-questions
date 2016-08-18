import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

  class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  };

class Solution {
  public static void main(String[] args) {
    UndirectedGraphNode a = new UndirectedGraphNode(1);
    UndirectedGraphNode b = new UndirectedGraphNode(2);
    UndirectedGraphNode c = new UndirectedGraphNode(3);
    UndirectedGraphNode d = new UndirectedGraphNode(4);
    UndirectedGraphNode e = new UndirectedGraphNode(5);
    a.neighbors.add(b);
    a.neighbors.add(c);
    b.neighbors.add(c);
    b.neighbors.add(a);
    c.neighbors.add(a);
    c.neighbors.add(b);
    d.neighbors.add(e);
    e.neighbors.add(d);
    ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
    nodes.add(a);
    nodes.add(b);
    nodes.add(c);
    nodes.add(d);
    nodes.add(e);
    List<List<Integer>> list = connectedSet(nodes);
    for(List<Integer> res : list)
    System.out.println(res);
    }
  
  

  
  public static List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if(nodes==null) return result;

        Set<UndirectedGraphNode> visited = new HashSet<>();
   
        for (UndirectedGraphNode node : nodes){
            if(!visited.contains(node))
                bfs(node, visited, result);
        }
        return result;
    }
    
    
    public static void bfs(UndirectedGraphNode node, Set<UndirectedGraphNode> visited, List<List<Integer>> result){
        
        
        visited.add(node);
        
        List<Integer> row = new ArrayList<>();
        
        
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        
        
        queue.offer(node);
        while (!queue.isEmpty()){
            UndirectedGraphNode u = queue.poll();
            row.add(u.label);
            for (UndirectedGraphNode v : u.neighbors){
                if (!visited.contains(v)){
                    visited.add(v);
                    queue.offer(v);
                }
            }
        }
        
        Collections.sort(row);
        result.add(row);
        
    }
}
