class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (head != null) {
            if (head.val != val) {
                tail.next = head;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null;
        return dummy.next;
    }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        helper(dummy, head, val);
        return dummy.next;
    }

    private void helper(ListNode prev, ListNode head, int val) {
        if (head == null) return;
        if (head.val == val) {
            prev.next = head.next;
        } else {
            prev = head;
        }
        helper(prev, prev.next, val);
    }
}