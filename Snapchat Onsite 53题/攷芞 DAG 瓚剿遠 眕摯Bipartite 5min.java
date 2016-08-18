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
     DirectedGraphNode a = new DirectedGraphNode(1);
     DirectedGraphNode b = new DirectedGraphNode(2);
     DirectedGraphNode c = new DirectedGraphNode(3);
     DirectedGraphNode d = new DirectedGraphNode(4);
     a.neighbors.add(b);
     a.neighbors.add(d);
     
     c.neighbors.add(a);
     d.neighbors.add(c);
     
     c.neighbors.add(b);
     b.neighbors.add(d);
     
     ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
     list.add(a);
     list.add(d);
     list.add(b);
     list.add(c);
     boolean res = cycle(list);
     System.out.println(res);
  


     DirectedGraphNode e = new DirectedGraphNode(5);
     DirectedGraphNode f = new DirectedGraphNode(6);
     DirectedGraphNode g = new DirectedGraphNode(7);
     DirectedGraphNode h = new DirectedGraphNode(8);
     e.neighbors.add(f);
     e.neighbors.add(g);
     
     f.neighbors.add(h);
     g.neighbors.add(h);

     // f.neighbors.add(g);
     
     
     // c.neighbors.add(b);
     // b.neighbors.add(d);
    ArrayList<DirectedGraphNode> list1 = new ArrayList<DirectedGraphNode>();
     list1.add(e);
     list1.add(f);
     list1.add(g);
     list1.add(h);
      boolean res2 = isBipartite(list1);
     System.out.println(res2);

     
     
 
}
   static boolean cycle(ArrayList<DirectedGraphNode> graph){
     HashMap<DirectedGraphNode,Integer> visited = new HashMap<>();
     for(DirectedGraphNode node : graph)
        visited.put(node,0);
     
     for(DirectedGraphNode node : graph)
       if(visited.get(node)==0)
          if(dfs(node,visited)){ 
            return true;
          }
    return false;
     
   }
  static boolean dfs(DirectedGraphNode node, HashMap<DirectedGraphNode,Integer> visited){
    if(visited.get(node)==1) return true;
    visited.put(node,1);
    for(DirectedGraphNode neighbor : node.neighbors){
      if(visited.get(neighbor)!=2)
        if(dfs(neighbor,visited)) return true;
    }
     visited.put(node,2);
     return false;
  }
  
  public static boolean isBipartite(ArrayList<DirectedGraphNode> graph) {
            // implement here
            int len = graph.size();

            if (len < 3) return true;
            Set<DirectedGraphNode> color1 = new HashSet();    // always point to 
            Set<DirectedGraphNode> color2 = new HashSet();
            for (DirectedGraphNode node : graph){
                if (!color1.contains(node) && !color2.contains(node)){
                    if (!paint(node, color1, color2)) return false;
                  }
            }
            return true;
        }
        
        public static boolean paint(DirectedGraphNode node, Set<DirectedGraphNode> color1, Set<DirectedGraphNode> color2){

            if (color2.contains(node)) return false;//染色错误 返回false
            color1.add(node);//染色
            for(DirectedGraphNode child : node.neighbors){
                if(!color2.contains(child)) // 判断是否已染色
                    if (!paint(child, color2, color1)) return false; //dfs邻居节点 交换颜色
            }
            return true;
        }
    


}

  class DirectedGraphNode {
      int label;
      ArrayList<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 };  
  

