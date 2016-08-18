// 树(图) 序列化

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
     
     StringBuilder sb = new StringBuilder();
     Set<DirectedGraphNode> visited = new HashSet<>();
     graphSerialize(a,visited,sb);
     System.out.println(sb.toString());
     
     LinkedList<String> nodes = new LinkedList<>();
     nodes.addAll(Arrays.asList(sb.toString().split(",")));
    
     // 解序列 建图
     DirectedGraphNode root = graphDeserialize(nodes,new HashMap<Integer,DirectedGraphNode>());
     
     StringBuilder sb1 =  new StringBuilder();
     Set<DirectedGraphNode> visited1 = new HashSet<>();
     graphSerialize(root,visited1,sb1);
     System.out.println(sb1.toString());




     
 
}
  
  static void graphSerialize(DirectedGraphNode node, Set<DirectedGraphNode> visited, StringBuilder sb){
  // 图的序列化比N-ary Tree 的序列化多的地方 就是这个visited 也要添加并退回
    if(visited.contains(node)) {
      sb.append(""+node.label+",").append("#,");
      return;
    }
    sb.append(""+node.label+",");
    visited.add(node);
    for(DirectedGraphNode neighbor : node.neighbors){
      graphSerialize(neighbor,visited, sb) ;
    }
    sb.append("#,");
  }

  static DirectedGraphNode graphDeserialize(LinkedList<String> list,Map<Integer,DirectedGraphNode> visited){
      String val = list.poll();

        if (val.equals("#")) return null;
        if(!visited.containsKey(Integer.parseInt(val))){
          visited.put(Integer.parseInt(val), new DirectedGraphNode(Integer.parseInt(val)));
        }
        DirectedGraphNode root = visited.get(Integer.parseInt(val));
        while(list.size()>0){
          DirectedGraphNode node = graphDeserialize(list,visited);
            if(node!=null) 
              root.neighbors.add(node);
            else break;
    }
        return root;    
  }
  

}

  class DirectedGraphNode {
      int label;
      ArrayList<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 };  
  

