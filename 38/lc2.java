class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur =dummy;
        int c = 0;
        while (l1 != null || l2 != null || c != 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            cur.next = new ListNode((v1 + v2 + c) % 10);
            cur = cur.next;
            c = (v1 + v2 + c) / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}