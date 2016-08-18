

  
 
public class Solution {
  public static void main(String[] args){
      
      int[] preorder = {1,2,3};
      int[] inorder = {2,1,3};
      
      TreeNode root = buildTree(preorder,inorder);
      System.out.println(root.val);
      System.out.println(root.left.val);
      System.out.println(root.right.val);
      
      
  }
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder.length!=inorder.length) return null;
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public static TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart > preorder.length - 1 || inStart > inEnd) {
        return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);
    int inIndex = -1; // Index of current root in inorder
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
        }
    }
    if (inIndex == -1) return null;
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
    return root;
}
}