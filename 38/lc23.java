class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list: lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            ListNode polled = queue.poll();
            cur.next = polled;
            cur = cur.next;
            if (polled.next != null) {
                queue.add(polled.next);
            }
        }

        return dummy.next;
    }
}


class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode leftPart = merge(lists, left, mid);
        ListNode rightPart = merge(lists, mid + 1, right);
        return merge2Lists(leftPart, rightPart);
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = merge2Lists(l1.next, l2);
            return l1;
        } else {
            l2.next = merge2Lists(l1, l2.next);
            return l2;
        }
    }
}