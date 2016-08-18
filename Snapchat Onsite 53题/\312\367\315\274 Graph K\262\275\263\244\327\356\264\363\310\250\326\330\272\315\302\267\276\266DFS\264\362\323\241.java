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

     DirectedGraphNode e = new DirectedGraphNode(5);
     DirectedGraphNode f = new DirectedGraphNode(6);
     DirectedGraphNode g = new DirectedGraphNode(7);
     DirectedGraphNode h = new DirectedGraphNode(8);
     e.neighbors.add(f);
     e.neighbors.add(g);
     
     f.neighbors.add(h);
     g.neighbors.add(h);


     Path path = new Path(0);

     Set<DirectedGraphNode> visited = new HashSet<>();
     int depth = 2;
     ArrayList<Integer> result = maxWeightPath(e, visited, path, depth);
     System.out.println(result);

}
  static class Path{
    int weightSum;
    ArrayList<Integer> path;
    Path(int weightSum){
      this.weightSum = weightSum;
      path = new ArrayList<Integer>();
    }
  }
  static int max = 0;
  public static ArrayList<Integer> maxWeightPath(DirectedGraphNode node, 
                      Set<DirectedGraphNode> visited, Path path, int depth) {
            // implement here
        ArrayList<Integer> result = new ArrayList<>();
        if(depth==0){
         
          if(path.weightSum+node.weight>max){
            path.path.add(node.weight);    
            result = new ArrayList<Integer>(path.path);
            path.path.remove(path.path.size()-1);
            max = path.weightSum+node.weight;
            return result;
          }
        }
        visited.add(node);    
        for(DirectedGraphNode neighbor : node.neighbors){
          
            if(!visited.contains(neighbor)){
              path.path.add(node.weight);
              path.weightSum+=node.weight;

              result = maxWeightPath(neighbor, visited, path, depth-1);

              path.path.remove(path.path.size()-1);
              path.weightSum-=node.weight; 
            }
        }

        visited.remove(node); 
        return result;
        
        }


}

 class DirectedGraphNode {
      int weight;
      ArrayList<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) { weight = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 };  
  

