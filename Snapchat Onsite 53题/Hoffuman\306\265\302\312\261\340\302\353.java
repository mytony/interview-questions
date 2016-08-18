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
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("abbabc");
    strings.add("");
    strings.add("1234");
    strings.add("1243");
    for (String string : strings) {
      System.out.println(string + " is encoded as " + encode(string));
    }
  }


  static class Node{
    char cha;
    int freq;
    public Node(char cha, int freq) {
      this.cha = cha;
      this.freq = freq;
    }
  }

  public static String encode(String input) {
    if (input == null) {
      return "";
    }
    HashMap<Character, Integer> statistics = new HashMap<>();
    for (int pos = 0; pos < input.length(); pos++) {
      char cur = input.charAt(pos);
      if (!statistics.containsKey(cur)) {
        statistics.put(cur, 1);
      }
      else {
        statistics.put(cur, statistics.get(cur)+1);
      }
    }

    List<Node> list = new ArrayList<>();
    for (Character key : statistics.keySet()) {
      list.add(new Node(key, statistics.get(key)));
    }

    Collections.sort(list, new Comparator<Node>(){
      public int compare(Node a, Node b) {
        return b.freq - a.freq;
      }
    });
    HashMap<Character, String> encodeDict = new HashMap<>();
    StringBuilder code = new StringBuilder("1");
    for (Node node: list) {
      encodeDict.put(node.cha, code.toString());
      code.insert(0, "0");
    }

    //O(n^2)

    StringBuilder res = new StringBuilder();
    for (int pos = 0; pos < input.length(); pos++) {
      res.append(encodeDict.get(input.charAt(pos)));
    }

    return res.toString();

  }
}
  
  

