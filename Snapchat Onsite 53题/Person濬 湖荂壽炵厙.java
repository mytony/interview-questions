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
      Person Mike = new Person(1,"Mike");
      Person Mitch = new Person(2,"Mitch");
      Person Ryan = new Person(3,"Ryan");
      Person Lila = new Person(4,"Lila");
      Mike.friends.add(Mitch);
      Mike.friends.add(Ryan);
    
      Mitch.friends.add(Mike);
      Mike.friends.add(Lila);
    
      Mike.printFriendsGraph();
    }
  
}

class Person{
  int id;
  String name;
  ArrayList<Person> friends;
  public Person(int id, String name){
    this.id = id;
    this.name = name;
    this.friends = new ArrayList<Person>();
  }
  public void printFriendsGraph(){
    Set<Integer> visited = new HashSet<Integer>();
    Queue<Person> friendGraph = new LinkedList<Person>();
    visited.add(this.id);
    friendGraph.offer(this);
    List<String> res = new ArrayList<String>();
    while(!friendGraph.isEmpty()){
      Person person = friendGraph.poll();       
      for(Person friend : person.friends){
        if(!visited.contains(friend.id)){
          res.add(friend.name);
          visited.add(friend.id);
          friendGraph.offer(friend);
          }
      }
    }
    System.out.println(res);
}
}