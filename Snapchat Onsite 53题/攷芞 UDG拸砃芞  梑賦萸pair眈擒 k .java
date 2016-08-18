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
    UndirectedGraphNode n1 = new UndirectedGraphNode(1);
    UndirectedGraphNode n2 = new UndirectedGraphNode(2);
    UndirectedGraphNode n3 = new UndirectedGraphNode(3);
    UndirectedGraphNode n4 = new UndirectedGraphNode(4);
    UndirectedGraphNode n5 = new UndirectedGraphNode(5);
    UndirectedGraphNode n6 = new UndirectedGraphNode(6);
    UndirectedGraphNode n7 = new UndirectedGraphNode(7);
    UndirectedGraphNode n8 = new UndirectedGraphNode(8);
    UndirectedGraphNode n9 = new UndirectedGraphNode(9);
    UndirectedGraphNode n10 = new UndirectedGraphNode(10);
    UndirectedGraphNode n11 = new UndirectedGraphNode(11);
    UndirectedGraphNode n12 = new UndirectedGraphNode(12);
    UndirectedGraphNode n13 = new UndirectedGraphNode(13);
    UndirectedGraphNode n14 = new UndirectedGraphNode(14);
    UndirectedGraphNode n15 = new UndirectedGraphNode(15);
    UndirectedGraphNode n16 = new UndirectedGraphNode(16);

    n1.neighbors.add(n2);
    n1.neighbors.add(n5);
    n1.neighbors.add(n8);


    n2.neighbors.add(n1);
    n2.neighbors.add(n3);
    n2.neighbors.add(n4);


    n8.neighbors.add(n9);
    n8.neighbors.add(n10);
    n8.neighbors.add(n1);


    n9.neighbors.add(n8);
    n10.neighbors.add(n8);

    n3.neighbors.add(n2);
    n4.neighbors.add(n2);

    n5.neighbors.add(n1);
    n5.neighbors.add(n6);
    n5.neighbors.add(n7);


    n6.neighbors.add(n5);
    n6.neighbors.add(n11);
    n6.neighbors.add(n15);


    n15.neighbors.add(n16);
    n15.neighbors.add(n14);
    n15.neighbors.add(n6);

    n11.neighbors.add(n6);
    n11.neighbors.add(n12);
    n11.neighbors.add(n13);


    n7.neighbors.add(n5);
    n12.neighbors.add(n11);

    n13.neighbors.add(n11);
    n14.neighbors.add(n15);

    n16.neighbors.add(n15);


   
    boolean res = Connected(n1, n5, 3);

    System.out.println(res);
    }
  
  

  
  public static boolean Connected(UndirectedGraphNode a, UndirectedGraphNode b, int distance) {
        // Write your code here

        Set<UndirectedGraphNode> friendOfA = new HashSet<>();
        Set<UndirectedGraphNode> friendOfB = new HashSet<>();
             
        friendOfA.add(a);
        friendOfB.add(b);
        distance++;
        // 复杂度 O（d^h/2）
        if(dfs(a, friendOfA, friendOfB, distance/2)) return true;
        if(dfs(b, friendOfB, friendOfA, distance - distance/2)) return true;

        return false;
    }
    
    //DFS 深度 h, 
    public static boolean dfs(UndirectedGraphNode node, Set<UndirectedGraphNode> visited1, 
                            Set<UndirectedGraphNode> visited2, int depth){
        if(visited2.contains(node)) return true;   
        for(UndirectedGraphNode neighbor: node.neighbors){
            if(!visited1.contains(neighbor)){
                if(depth>0){
                     visited1.add(neighbor);
                    if(dfs(neighbor, visited1, visited2, depth -1)) return true;
                }
            }
        }
        return false;
        
    }
}
