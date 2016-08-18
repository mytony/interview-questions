   
import java.util.*;
class Solution{
    public static void main(String[] args) {
      Employer e1 = new Employer(13);
      Employer e2 = new Employer(9);
      Employer e3 = new Employer(2);
      Employer e4 = new Employer(3);
      Employer e5 = new Employer(4);
      Employer e6 = new Employer(8);
      Employer e7 = new Employer(6);
      Employer e8 = new Employer(7);

      e1.subordinates.add(e2);
      e1.subordinates.add(e3);
      e3.subordinates.add(e6);
      e2.subordinates.add(e4);
      e2.subordinates.add(e5);
      e5.subordinates.add(e7);
      e5.subordinates.add(e8);

      updateWeight(e1);
      ArrayList<Integer> goList = new ArrayList<>();
      updateGo(e1, false, goList);
      System.out.println(goList);
      System.out.println(Math.max(e1.select,e1.unselect));

    }

   static void updateGo(Employer employer, boolean bossGo, ArrayList<Integer> go){
   		if(!bossGo && employer.select>employer.unselect){ 
            employer.go=true;
            go.add(employer.weight);
         }
         for(Employer subordinate : employer.subordinates){
            updateGo(subordinate, employer.go, go);
         }         
   }

   static void updateWeight(Employer employer){
         for(Employer subordinate : employer.subordinates){
            updateWeight(subordinate);
            employer.select += subordinate.unselect;
            employer.unselect += Math.max(subordinate.unselect,subordinate.select);
         }          
   }

  static class Employer{
    int weight;
    int select;
    int unselect;
    boolean go;  
    List<Employer> subordinates;
    public Employer(int weight){
      this.weight = weight;
      this.select = weight;
      this.unselect = 0;
      subordinates = new ArrayList<>();
    }
  } 

    }

