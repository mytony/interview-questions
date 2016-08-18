public class HashMapWithBST {
	
	private Node[] nodes;
	private static final int MAX_CAPACITY = 41;
	
	public HashMapWithBST() {
		nodes = new Node[MAX_CAPACITY];
	}
	
	/**
	 * If key is a non-null object then return the hash code of key modulo hash map size as value. If key is null then return 0.
	 * 
	 * @param key
	 * @return hash
	 */
	public int getHash1(String key) {
		
		if(key == null) {
			return 0;
		}
		
		int hash = key.hashCode();
		
		hash = hash >>> 16; // Spread the higher bits

		hash = hash % MAX_CAPACITY;
		
		return hash;
	}

	public int getHash(String key) {
		
		if(key == null) {
			return 0;
		}
		int hash = ( key.hashCode() & 0x7FFFFFFF)% MAX_CAPACITY; 
		return hash;
	}
	
	/**
	 * In case of collisions, put the new key-value pair in a BST based on key comparisons.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		
		int hashOfKey = getHash(key);
		
		final Node newNode = new Node(key, value);
		
		if(nodes[hashOfKey] == null) {
			
			nodes[hashOfKey] = newNode;
		} else {

			Node root = nodes[hashOfKey];
			
			try {
				addToBSTBucket(root, newNode);
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * If a collision happens while adding a node to Hashmap, add new node to the hashed bucket represented with a BST.
	 * 
	 * @param root		root of BST bucket
	 * @param newNode 	New Node to be added in BST bucket
	 */
	private void addToBSTBucket(Node root, final Node newNode) {
		
		if(root == null) {
			root = newNode;
			return;
		}
		
		Node currentNode = root;
		Node parentNode = root;
		
		while(true) {
			
			parentNode = currentNode;
			
			if(newNode.key.compareTo(currentNode.key) == 0) {
				
				// if key values are same then just overwrite the vale in same node as duplicate keys are not allowed in this map
				currentNode.value = newNode.value;
				return;
				
			} else if(newNode.key.compareTo(currentNode.key) < 0) {
				currentNode = currentNode.left;
				
				if(currentNode == null) {
					parentNode.left = newNode;
					return;
				}
			} else {
				
				currentNode = currentNode.right;
				
				if(currentNode == null) {
					parentNode.right = newNode;
					return;
				}
			} 
		}
		
	}
	
	/**
	 * Get the value for a particular key. If no key found then return null.
	 * 
	 * @param key
	 * @return value or null
	 */
	public String get(String key) {
		
		Node node = nodes[getHash(key)];
		
		if(node != null) {
			return getValueFromBST(node, key);
		}
		
		return null;
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
	
	private static class Node {
		
		private String key;
		private String value;
		private Node left;
		private Node right;
		
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
	}

	public static void main(String[] args) {
		
		HashMapWithBST map = new HashMapWithBST();
		
		// null test
		map.put(null, null);
		System.out.println("Null value is: " + map.get(null));
		
		map.put("Country", "US");
		map.put("Fruit", "Apple");
		map.put("Fruit", "Mango");
		map.put("Planet", "Earth");
		map.put("App", "PayPal");
		
		String fruit = map.get("Fruit");
		System.out.println("Fruit value is: " + fruit);
		
		String country = map.get("Country");
		System.out.println("Country value is: " + country);
		
		String planet = map.get("Planet");
		System.out.println("Planet value is: " + planet);
		
		String app = map.get("App");
		System.out.println("App value is: " + app);
		
	}

}