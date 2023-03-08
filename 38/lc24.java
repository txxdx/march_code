class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        recursive(dummy);
        return dummy.next;
    }

    private void recursive(ListNode head) {
        if (head.next != null && head.next.next != null) {
            ListNode a = head.next;
            ListNode b = head.next.next;
            head.next = b;
            a.next = b.next;
            b.next = a;

            recursive(a);
        }
    }
}

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;

        while (cur != null && cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            cur.next = second;
            first.next = second.next;
            second.next = first;

            cur = first;
        }

        return dummy.next;
    }
}