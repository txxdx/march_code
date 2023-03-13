class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = 0;
        ListNode cur = head ;
        while (cur != null) {
            len ++;
            cur = cur.next;
        }

        k = k % len;
        if (len == 0) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        // dummy(fast/slow) -> 1 -> 2 -> 3 -> 4 -> 5 -> null;
        for (int i = 0; i < k; i ++) {
            fast = fast.next;
        }
        // dummy(slow) -> 1 -> 2(fast) -> 3 -> 4 -> 5 -> null;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // dummy -> 1 -> 2 -> 3(slow) -> 4 -> 5(fast) -> null;
        fast.next = dummy.next;
        head = slow.next;
        // dummy -> 1 -> 2 -> 3(slow) -> 4(head) -> 5(fast) -> 1 ->;
        slow.next = null;
        //  4(head) -> 5(fast) -> 1 -> 2 -> 3(slow) ;
        return head;
    }
}