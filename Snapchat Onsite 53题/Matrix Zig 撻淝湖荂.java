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

    int[][]matrix = {{1,2},{3,4},{5,6}};
      
    List<List<Integer>> res = printZMatrix(matrix);
    System.out.println(res);
    
    
  }
 
   // http://www.lintcode.com/en/problem/matrix-zigzag-traversal/
  public static List<List<Integer>> printZMatrix(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;
        int count = matrix.length * matrix[0].length;
        List<List<Integer>> array = new ArrayList<List<Integer>>();
        int r = 0, c = 0;
        int lastr = 0, lastc=0;
        for (int i = 0; i < count; ) {
            
            r = lastr;
            c = lastc;
            List<Integer> list = new ArrayList<>();
            while(i < count && r  < matrix.length && c  >= 0) {
                list.add( matrix[r++][c--]);
                i++;
            }

            array.add(list);
            if (lastc + 1 < matrix[0].length) {
                ++lastc;
            } else if (lastr + 1 < matrix.length) {
                ++lastr;
            }
          
            
        }
        return array;
    }

     public int[] printZMatrix(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;
        int count = matrix.length * matrix[0].length;
        int[] array = new int[count];
        int r = 0, c = 0;
        array[0] = matrix[0][0];
        int lastr = 0, lastc=0;
        for (int i = 1; i < count; ) {
            //斜上走到顶
            while(i < count && r - 1 >= 0 && c + 1 < matrix[0].length) {
                array[i++] = matrix[--r][++c];
                
            }
            //横右走一步，不可横右走时竖下走一步
            if (i < count && c + 1 < matrix[0].length) {
                array[i++] = matrix[r][++c];
            } else if (i < count && r + 1 < matrix.length) {
                array[i++] = matrix[++r][c];
            }
            //斜下走到底
            while(i < count && r + 1 < matrix.length && c - 1 >= 0) {
                array[i++] = matrix[++r][--c];
            }
            //竖下走一步，不可竖下走时横右走一步
            if (i < count && r + 1 < matrix.length) {
                array[i++] = matrix[++r][c];
            } else if (i < count && c + 1 < matrix[0].length) {
                array[i++] = matrix[r][++c];
            }
        }
        return array;
    }

   
  
  
}
