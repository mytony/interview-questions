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
    String A = "1.4056767885433";
    String B = ".9";
    
    A = "1";
    B = "222.9";
    
    String res = twoNumAdd(A,B);
    System.out.println(res);
   
   
    
    String C="0";
    String D="0";   
    String res3 = multiply(C,D);
    System.out.println(res3);
    
    String E="1234";
    String F="1000";   
    String res4 = sub(E,F);
    System.out.println(res4);
    

      
  }
  
   static String twoNumAdd(String A,String B){
    int idxA = A.indexOf(".");
    int idxB = B.indexOf(".");
    String intA=idxA==-1?A:A.substring(0,idxA);
    String intB=idxB==-1?B:B.substring(0,idxB);
    String deciA=idxA==-1?"":A.substring(idxA+1);
    String deciB=idxB==-1?"":B.substring(idxB+1);
    
    String decimal =  deciPart(deciA,deciB);
    String integer =  intPart(intA,intB);
    return integer+ "."+decimal;
   }
  
  static int sum = 0;
   public static String intPart(String A, String B) {
        int m = A.length()-1;
        int n = B.length()-1;
        String res ="";
        int i=m,j=n;
        
        while(i>=0||j>=0){
            sum/=10;
            if(i>=0){
                sum+=A.charAt(i)-'0';
                i--;
            }
            if(j>=0){
                sum+=B.charAt(j)-'0';
                j--;
            }
            res = sum%10 + res;
        }
        if(sum/10==1)
            res = "1" + res;
            return res;
    }
  
  public static String deciPart(String A, String B) {
        int m = A.length()-1;
        int n = B.length()-1;
        String res ="";
        int i = Math.max(m,n);
        while(i>=0){
            sum/=10;
            if(m>=i){
                sum+=A.charAt(i)-'0';      
            }
            if(n>=i){
                sum+=B.charAt(i)-'0';
                
            }
           i--;
           res = sum%10 + res;
        }
            return res;
    }

  public static int compare(String A, String B){
            if(A.length()<B.length()) return -1;
            else if(A.length()>B.length()) return 1;
            else{
                for(int i=0;i<A.length();i++){
                    if(A.charAt(i)<(B.charAt(i))) return -1;
                    else if(A.charAt(i)>(B.charAt(i))) return 1;    
                }
                return 0;
            }
               
        }  

  public static String subNeg(String A, String B){
        if (isNeg(A) && isNeg(B)) return sub(B.substring(1),A.substring(1));
        if (!isNeg(A) && !isNeg(B)) return sub(A,B);
        if (!isNeg(A) && isNeg(B)) return intPart(A,B.substring(1));
        if (isNeg(A) && !isNeg(B)) return "-"+intPart(A.substring(1),B);
        
  }      
  public static String sub(String A, String B) {    
        boolean neg = false;
        if(compare(A,B)<0){
            String tmp = B;
            B = A;
            A = tmp;
            neg = true;
        }
        String res ="";
        int i=A.length()-1;
        int j=B.length()-1;
        int c = 0;

        while(i>=0){
              int a = A.charAt(i--)-'0';
              int b = j>=0?B.charAt(j--)-'0':0;
             if(a-c>=b){
               res = (a-c-b) + res;
               c = 0;
             }else{
               res = (a-c-b+10) + res;
               c = 1;
            }
        }
        res = res.charAt(0)=='0'? res.substring(1):res;
        return neg? "-"+res : res;
    }

  public static String multiply(String num1, String num2) {
        int m = num1.length()-1;
        int n = num2.length()-1;
        int[] d = new int[m+n+2];     // 构建数组存放乘积  
        for(int i=m; i>=0; i--){  
            for(int j=n; j>=0; j--){ 
                int position = i+j+1;
                d[position] += (num1.charAt(i)-'0') * (num2.charAt(j)-'0');      
            }  
        }  
          
        String sb = "";
        int sum = 0;
        for(int i=d.length-1; i>=0; i--){  
            sum/=10;
            sum+=d[i];
            sb = sum%10 + sb;
        }  
          
        // 移除前导零  
        int i = 0;
        while(sb.charAt(i)=='0' && i<sb.length()-1)
        {
            i++;
        }
        return sb.substring(i);
    
    }
  
  
}
