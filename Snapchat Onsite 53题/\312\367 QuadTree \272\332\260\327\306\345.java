import java.util.*;
public class Solution {
  
    public static void main(String[] args) {
      int[][]board1 = {{0,0,1,0},{0,0,1,1},{1,1,1,1},{0,0,1,1}};  //1 W 0 B
      int[][]board2 = {{0,0,1,1},{0,0,1,1},{0,1,1,1},{0,0,1,1}};  //1 W 0 B
      
      QdTree img1 = Build(board1);
      QdTree img2 = Build(board2);
      QdTree merge = merge(img1, img2);
      print(merge);
      

    }
    // Implement regular expression matching with support for '.' and '*'.
    public static QdTree Build(int[][]board) {
        QdTree node = dfs(board, 0, board.length-1, 0, board[0].length-1, board.length);
        print(node);
        return node;
    }
    public static QdTree dfs(int[][]board, int m, int n, int x, int y, int size) {
              if(m==n&&x==y){
                return new QdTree(board[m][x]==1?"White" : "Black", 1);
              }
             QdTree node = new QdTree("Gray",size);     
             int midV = (m+n)/2;
             int midH = (x+y)/2;
             int subsize = midV+1;
             QdTree NW = dfs( board, m, midV, x, midH, subsize);
             QdTree NE = dfs( board, m, midV, midH+1, y, subsize);
             QdTree SW = dfs( board, midV+1, n, x, midH, subsize);
             QdTree SE = dfs( board, midV+1, n, midH+1, y, subsize);
                
             String color = NW.color;
             if(NE.color.equals(color) && SW.color.equals(color) && SE.color.equals(color)){
              node.color = color; 
             }else{
              node.child.add(NW);
              node.child.add(NE);
              node.child.add(SW);
              node.child.add(SE);
            }
            return node;
       
    }
    
// 如果一个区域在 第一个quadtree 里面是
// 白的，这个相同的区域在 第二个 quadtree里面是黑的，那么intersection
// 就是白的  白黑-白 黑白-白 白白-白 黑黑-黑
    public static QdTree merge(QdTree img1, QdTree img2){
         String color1 = img1.color;
         String color2 = img2.color;
         String color = "White";
         if(color1.equals("Black")&&color2.equals("Black")) color = "Black";
         
         if(!color1.equals("Gray")&&!color2.equals("Gray"))         
            return new QdTree(color, img1.size);

         if(!color1.equals("Gray"))
            return mergeHelper(img1, img2);
         if(!color2.equals("Gray"))
            return mergeHelper(img2, img1);

          QdTree root = new QdTree("Gray", img1.size);
          for(int i=0; i<4; i++)
            root.child.add(merge(img1.child.get(i), img2.child.get(i)));
          // 这里有问题要重写，需要判断四个合并后的 是否又变成同色了，是的话不需要一一添加child, 更改root颜色即可
          return root;      
    }
     public static QdTree mergeHelper(QdTree img1, QdTree img2){
        if(img1.color.equals("White"))
            return img1;
        return img2;
     }

    public static void print(QdTree root){

      Queue<QdTree> q = new LinkedList<QdTree>();
      q.offer(root);
      while(!q.isEmpty()){
        int size = q.size();
        System.out.println("  ");
        for(int i=0; i<size; i++ ){
          QdTree node = q.poll();
         System.out.println(node.color+" "+node.size);
         for(QdTree n : node.child){
          q.offer(n);
         }
      }
    }
  }
    static class QdTree{
          String color;
          ArrayList<QdTree> child;
          int size;
          QdTree(String color, int size){
            this.color = color;
            this.size = size;
            child = new ArrayList<QdTree>();
          }
    }   
}