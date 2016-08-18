import java.util.*;

class LogEntry{
    int thID;
    int mutID;
    boolean act;//true acquire  false release
    LogEntry(int thID, int mutID, boolean act){
        this.thID = thID;
        this.mutID = mutID;
        this.act = act;
    }
}
class graphNode{
    int node;
    Set<graphNode> neighbors;
    graphNode(int node){
      this.node = node;
      this.neighbors = new HashSet<graphNode>();
    }
}

class Solution{
public static void main(String [] args){
    
// 1 0 1
// 2 0 2
// 3 0 1
// 2 0 1
// 1 0 2
// 1 1 1
// 3 0 2  

    List<LogEntry> logs = new ArrayList<>();
    logs.add(new LogEntry(1,1,true));
    logs.add(new LogEntry(2,2,true));
    logs.add(new LogEntry(3,1,true));
    logs.add(new LogEntry(2,1,true));
    logs.add(new LogEntry(1,2,true));
    logs.add(new LogEntry(1,1,false));
    logs.add(new LogEntry(3,2,true));
  

    print(logs);

}

static void print(List<LogEntry> logs){
     Map<Integer,Queue<graphNode>> mutexMap = new HashMap<>();//MutexID: waiting queue of thread
     Map<Integer,graphNode> threadMap = new HashMap<>();
     ArrayList<graphNode>graph = new ArrayList<>();
     
     for(LogEntry log: logs){
       //creat or get thread graphNode
       graphNode node;
       if(!threadMap.containsKey(log.thID))
         threadMap.put(log.thID,new graphNode(log.thID));  
          
       node = threadMap.get(log.thID);
       graph.add(node); 
       
       // acquire mutex
       if(log.act){
          // mutex not exist 
          if(!mutexMap.containsKey(log.mutID)){
            mutexMap.put(log.mutID,new LinkedList<graphNode>());        
            // exist mutex, add one edge to the graph
          }else{
            node.neighbors.add(mutexMap.get(log.mutID).peek()); 
          }
          mutexMap.get(log.mutID).add(node);
      
       // release mutex
         
       }else{
         
         Queue<graphNode> q = mutexMap.get(log.mutID);
         graphNode releaseID = q.poll();
         if(q.size()==0) mutexMap.remove(log.mutID);
         
         graphNode candidate = null;
        // give every other node in the waiting list to the first candidate if exist
         for(int i=0; i<q.size();i++){
           if(i==0) {
              candidate = q.poll();           
              candidate.neighbors.remove(releaseID);
              q.add(candidate);
           }
           else{
              graphNode waitingID = q.poll();
              waitingID.neighbors.remove(releaseID);
              waitingID.neighbors.add(candidate);
              q.add(waitingID);
           }
         }
       }
         System.out.println(hasCycle(graph));
     }
    
}
  
  static boolean hasCycle(ArrayList<graphNode> graph){
     HashMap<graphNode,Integer> visited = new HashMap<>();
     for(graphNode node : graph)
        visited.put(node,0); 
     for(graphNode node : graph)
       if(visited.get(node)==0)
          if(dfs(node,visited)){ 
            return true;
          }
    return false;
     
   }
  static boolean dfs(graphNode node, HashMap<graphNode,Integer> visited){
    if(visited.get(node)==1) return true;
    visited.put(node,1);
    for(graphNode neighbor : node.neighbors){
      if(visited.get(neighbor)!=2)
        if(dfs(neighbor,visited)) return true;
    }
     visited.put(node,2);
     return false;
  }






}