class LRUCache {
    class Node {
        int key, val;
        Node prev, next;
        Node (int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    int capacity;
    Node head, tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            refresh(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            refresh(node);
        } else {
            if (map.size() == capacity) {
                Node delNode = tail.prev;
                map.remove(delNode.key);
                delete(delNode);
            }

            Node node = new Node(key, value);
            map.put(key, node);
            refresh(node);
        }
    }

    private void refresh(Node node) {
        delete(node);
        addToHead(node);
    }

    private void delete(Node node) {
        if (node.prev !=null) {
            Node prev = node.prev;
            prev.next = node.next;
            node.next.prev = prev;
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}