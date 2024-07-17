import java.util.*;

class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {}

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node>mpcache = new HashMap<>();
    private int size;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node =mpcache.get(key);
        if (node == null) {
            return -1; 
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node =mpcache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
           mpcache.put(key, newNode);
            addNode(newNode);
            size++;

            if (size > capacity) {
                Node tail = popTail();
               mpcache.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToFront(node);
        }
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
    
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 10);
        lruCache.put(2, 20);
        System.out.println("Output here- ");
        System.out.println(lruCache.get(1));    
        lruCache.put(3, 30);                
        System.out.println(lruCache.get(2));    
        lruCache.put(4, 40);                     
        System.out.println(lruCache.get(1));    
        System.out.println(lruCache.get(3));    
        System.out.println(lruCache.get(4));    
    }
}