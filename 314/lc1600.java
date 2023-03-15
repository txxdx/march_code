class ThroneInheritance {

    class Node {
        String name;
        Node next;
        Node last;
        boolean isDead = false;
        Node(String name) {
            this.name = name;
        }
    }

    Map<String, Node> map = new HashMap<>();
    Node head, tail;

    public ThroneInheritance(String kingName) {
        Node root = new Node(kingName);
        head = new Node("");
        tail = new Node("");
        head.next = root;
        root.next = tail;
        map.put(kingName, root);
    }
    
    public void birth(String parentName, String childName) {
        Node childNode = new Node(childName);
        map.put(childName, childNode);
        Node parentNode = map.get(parentName);
        Node tmp = parentNode;
        while (tmp.last != null) tmp = tmp.last;
        childNode.next = tmp.next;
        tmp.next = childNode;
        parentNode.last = childNode;
    }
    
    public void death(String name) {
        Node node = map.get(name);
        node.isDead = true;
    }
    
    public List<String> getInheritanceOrder() {
        List<String> ans = new LinkedList<>();
        Node tmp = head.next;
        while(tmp.next != null) {
            if (tmp.isDead == false) {
                ans.add(tmp.name);
            }
            tmp = tmp.next;
        }

        return ans;
    }
}