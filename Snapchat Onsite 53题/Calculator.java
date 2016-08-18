import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


public class Calculator {
  public static void main(String[] args) {
    String[] in ={"3", "*", "6", "-", "(",  "23", "+", "7", ")", "/", "(", "1", "+", "2", ")"} ;
    System.out.println("res");  
     int res =  evaluateExpression(in);
    System.out.println("res"+res);
    }
    
    public int calculate(String s) {
        int len;
    if(s==null || (len = s.length())==0) return 0;
    Stack<Integer> stack = new Stack<Integer>();
    int num = 0;
    
    char op = '+';
    
    for(int i=0;i<len;i++){
        if(Character.isDigit(s.charAt(i))){
            num = num*10+s.charAt(i)-'0';
        }
        if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
            if(op=='-'){
                stack.push(-num);
            }
            if(op=='+'){
                stack.push(num);
            }
            if(op=='*'){
                stack.push(stack.pop()*num);
            }
            if(op=='/'){
                stack.push(stack.pop()/num);
            }
            op = s.charAt(i);
            num = 0;
        }
    }

    int re = 0;
    for(int i:stack){
        re += i;
    }
    return re;
    }

  
static class  TreeNode {
  public int val;
  public String s;
  public TreeNode left, right;

  public TreeNode(int val, String ss) {
    this.val = val;
    this.s = ss;
    this.left = this.right = null;
  }

}

 static  int get(String a, Integer base) {
    if (a.equals("+") || a.equals("-"))
      return 1 + base;
    if (a.equals("*") || a.equals("/"))
      return 2 + base;

    return Integer.MAX_VALUE;
  }

  public static int evaluateExpression(String[] expression) {
    // write your code here
    Stack<TreeNode> stack = new Stack<TreeNode>();

    int val = 0;
    Integer base = 0;
    for (int i = 0; i < expression.length; i++) {
        if (expression[i].equals("(")) {
          base += 10;
          continue;
        }
        if (expression[i].equals(")")) {
          base -= 10;
          continue;
        }
      val = get(expression[i], base);

      TreeNode cur = new TreeNode(val,expression[i]);
      
      // construct min stack: (参考 MAX TREE， 这里是min tree )
     while (!stack.isEmpty() && cur.val <= stack.peek().val) {
                cur.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }
            stack.push(cur);
    }

        TreeNode result = new TreeNode(0,"");
        if (stack.isEmpty()) result = null;
        while (!stack.isEmpty()) {
            result = stack.pop();
        }

    ArrayList<String> reversepolish = new ArrayList<String>();
    dfs(result, reversepolish);
    String[] str = new String[reversepolish.size()];
    reversepolish.toArray(str);
    //System.out.println(as);
    
    return evalreversepolish(str);
  }
    
    // postorder reversepolish
  static void dfs(TreeNode root, ArrayList<String> as) {
    if(root==null)
      return;
//     if (root.left != null)
      dfs(root.left, as);
    
//     if (root.right != null)
      dfs(root.right, as);
    as.add(root.s);
  }
  
  static int evalreversepolish(String[] tokens) {
   Stack<Integer> stack = new Stack<Integer>();
        for(String s : tokens){
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
                int op2 = stack.pop();
                int op1 = stack.pop();
                if (s.equals("+")) stack.push(op1+op2);
                if (s.equals("-")) stack.push(op1-op2);
                if (s.equals("*")) stack.push(op1*op2);
                if (s.equals("/")) stack.push(op1/op2);
            }else
                stack.push(Integer.valueOf(s));
        }
        if(stack.isEmpty()) return 0;
        return stack.peek();
  }
   
  
  
}
