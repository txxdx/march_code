class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }
}


class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Node dummy = new Node(-1);
        Node tail = dummy;
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            map.put(cur, newNode);
            tail.next = newNode;
            tail = tail.next;
            cur = cur.next;
        }

        cur = head;
        tail = dummy.next;
        while (cur != null) {
            if (cur.random != null) {
                tail.random = map.get(cur.random);
            }
            cur = cur.next;
            tail = tail.next;
        }

        return dummy.next;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node dummy = new Node(-1);
        dummy.next = head;
        // 1 -> 2 -> 3 -> 4
        while (head != null) {
            Node node = new Node(head.val); // 1
            node.next = head.next; // 1 -> 2
            head.next = node;
            head = head.next.next;
        }

        head = dummy.next;
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }

        head = dummy.next;
        Node ans = head.next;
        // dummy -> 1(head) -> 1(ans/tmp) -> 2 -> 2 -> 3 -> 3 -> null;
        while (head != null) {
            Node tmp = head.next;
            if (head.next != null) {
                head.next = head.next.next;
            }
            head = tmp;
        }

        return ans;
    }
}