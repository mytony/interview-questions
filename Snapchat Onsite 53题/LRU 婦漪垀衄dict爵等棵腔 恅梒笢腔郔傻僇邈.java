// LRU
import java.util.*;



class Abbr{
	public static void main(String[] args){
		String[] para = {"a","c","a","d","e","g","f","e","c"};
		Set<String> dict = new HashSet<>();
		dict.add("a");
		dict.add("d");
		dict.add("g");
		
		LRU lru = new LRU(3);
		for(int i=0;i<para.length;i++){
			if(dict.contains(para[i])){
				System.out.println(lru.put(para[i],i));
			}
		}
	}
	

	static class LRU{
	static Map<String,Node> map;
	static Node head = new Node("",-1) ;
	static Node tail = new Node("",-1);
	static int capcity;
	LRU(int capcity){
		map = new HashMap<>();
		head.next = tail;
		tail.prev = head;
		this.capcity = capcity;
	}
	public static  int get(String key){
		if(!map.containsKey(key))  return -1;
		
		Node node = map.get(key);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		movetotail(node);

		return node.idx; 
	}
	public static int put(String key, int idx){
		if(get(key)!=-1)
			map.get(key).idx = idx;
		
		//插入新Node,移动到tail
		Node node = new Node(key, idx);
		map.put(key,node);	
		movetotail(node);

		//满了说明找到了，返回长度
		int length=-1;
		if(map.size()==capcity){
			length = tail.prev.idx - head.next.idx + 1;
			//删除头结点
			map.remove(head.next.key);
			head.next = head.next.next;
			head.next.prev = head;
		}
		return length;
	}

	public static void movetotail(Node node){
		node.next = tail;
		node.prev = tail.prev;
		tail.prev.next = node;
		tail.prev = node;
	}

	static class Node{
		String key;
		int idx;
		Node next;
		Node prev;
		Node(String key, int idx){
			this.key = key;
			this.idx = idx;
		}
	}
}
}

