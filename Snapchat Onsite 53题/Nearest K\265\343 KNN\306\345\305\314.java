import java.util.*;
public class Solution {
  
    public static void main(String[] args) {
      int[][]board = {{0,1,1,1},{0,0,0,0},{1,1,1,0},{0,0,0,0}};
      nearestK(board, 5, new Query(0,0));
    }
    // Implement regular expression matching with support for '.' and '*'.
    public static void nearestK(int[][]board, int k, Query q) {
    	ArrayList<Node> nodes = new ArrayList<>();
    	for(int i=0; i<board.length;i++)
    		for(int j=0; j<board[0].length;j++){
    			if(board[i][j]==1){
    				int dis = Math.abs(q.x-i)+Math.abs(q.y-j);
    				nodes.add(new Node(i,j,dis));
    			}
    		}
    	PriorityQueue<Node> pq = new PriorityQueue<Node>(k, comp);
    	for(Node node : nodes){
    		if(pq.size()<k)
    			pq.offer(node);
    		else if(pq.peek().dis>node.dis){
    			pq.poll();
    			pq.offer(node);
    		}
    	}
    	
    	Node[] result = new Node[k];
        int index = 0; 
        while (!pq.isEmpty()) {
            result[index++] = pq.poll().key;
        }
        // reverse
        for (int i = 0; i < index / 2; i++) {
            Node temp = result[i];
            result[i] = result[index - i - 1];
            result[index - i - 1] = temp;
        }     
    }
    
    static quickSelectK(int k ,int start, int end, Node[]nodes){
    	int l = start, r = end, pivot = end;
    	while(l<r){
    		while(l<r && nodes[l].dis<nodes[pivot].dis){
    			l++;
    		}
    		while(l<r && nodes[r].dis>=nodes[pivot].dis){
    			r--;
    		}
    		swap(nodes, l, r);
    	}
    	swap(nodes,l, pivot);
    	if(k==l+1) return l;
    	else if(k>l+1) quickSelectK(k, l+1, end, nodes);
		else quickSelectK(k, start, l, nodes);
    }

    static Comparator<Node> comp = new Comparator<Node>(){
    	public int compare(Node a, Node b){
    		return b.dis - a.dis;
    	}
    };

    public static class Node{
		int x;
		int y;
		int dis;
		Node(int x, int y, int dis){
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}

	public static class Query{
		int x;
		int y;
		Query(int x, int y){
			this.x = x;
			this.y = y;
		}
	}   
}

