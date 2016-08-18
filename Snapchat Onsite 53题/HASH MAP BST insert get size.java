import java.util.*;
public class solution{
public static void main(String[] args) {
        
        Map map = new Map();
        
        // null test
        // map.put(null, null);
        // System.out.println("Null value is: " + map.get(null));
        
        System.out.println(map.size());
        map.put("Country", "US");
        System.out.println(map.size());
        map.put("Fruit", "Apple");

        System.out.println(map.size());
        map.put("Fruit", "Mango");

        System.out.println(map.size());
        map.put("Planet", "Earth");

        System.out.println(map.size());
        map.put("App", "PayPal");

        System.out.println(map.size());
        
        String fruit = map.get("Fruit");
        System.out.println("Fruit value is: " + fruit);
        
        String country = map.get("Country");
        System.out.println("Country value is: " + country);
        
        String planet = map.get("Planet");
        System.out.println("Planet value is: " + planet);
        
        String app = map.get("App");
        System.out.println("App value is: " + app);
        
    }

    public static class Map{
        Node root;
        Map(){
        root = new Node("","");
        }
        public void put(String key, String value){
            insertNode(root, key, value);   
        }
        public String get(String key){
            return getValueFromBST(root,key);
        }
        public int size(){
            return root.count-1;
        }

    private String getValueFromBST(Node root, String key) {
        
        if(key == null) {
            return null;
        }     
        while(root != null) {
            if(key.equals(root.key)) {
                return root.value;
            } else if(key.compareTo(root.key) < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        return null;    
    }

    private Node insertNode(Node root, String key, String value) {       
        if(root == null) {
            root = new Node(key,value);
            return root;
        }
         if(root.key.compareTo(key)>0) 
            root.left = insertNode(root.left, key, value);
         else if(root.key.compareTo(key)<0)
            root.right = insertNode(root.right, key, value);
         else 
            root.value = value;
         
         root.count = 1 + (root.left==null?0:root.left.count) + (root.right==null?0:root.right.count);
         return root;
    }
    
    private class Node {
        
        private String key;
        private String value;
        private int count;
        private Node left;
        private Node right;
        
        public Node(String key, String value) {
            this.count=1;
            this.key = key;
            this.value = value;
        }

        
    }
}

}




    


   
