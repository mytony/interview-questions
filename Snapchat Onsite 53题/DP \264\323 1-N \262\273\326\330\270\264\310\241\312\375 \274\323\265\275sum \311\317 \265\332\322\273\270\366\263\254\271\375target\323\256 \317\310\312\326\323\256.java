// DP 从 1-N 不重复取数 加到sum 上 第一个超过target赢 先手赢

	// DP
import java.util.*;
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */

    public static void main(String[] args) {

       LinkedList<Integer> n = new LinkedList<>();
       n.add(1);
       n.add(2);
       n.add(3);
	   n.add(4);
       n.add(5);
      boolean res = dfs(n, 0, 11);
      System.out.println(res);
    }


    public static boolean dfs(LinkedList<Integer> n, int sum, int target) { // 0 is empty, 1 is false, 2 is true

        boolean result = true;
        if(n.size()==0)
        	return false;
        else if(n.size()==1)
        	return true;
        else{
        	for(int i = 0; i<n.size();i++){
        		if(n.get(i)+sum>target) return true;
        		int num = n.remove(i);
        		if(dfs(n, sum+num,target)) result = false;
        		n.add(num);
        	}

        return result;
        }
    }
}