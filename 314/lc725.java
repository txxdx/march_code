class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = getLength(head); // 10
        int perLen = len / k; // 3
        ListNode[] ans = new ListNode[k];
        int sum = 0;
        for (int i = 0; i < k; i ++) {
            ans[i] = head;
            sum ++;
            ListNode cur = ans[i];
            for (int j = 0; j < perLen - 1; j ++) {
                cur = cur.next;
                sum ++;
            }
            int rest = k - i - 1;
            if (perLen != 0 && sum + perLen * rest < len) {
                cur = cur.next;
                sum ++;
            }

            head = cur != null ? cur.next : null;
            if (cur != null) {
                cur.next = null;
            }
        }

        return ans;
    }

    private int getLength(ListNode node) {
        return node == null ? 0 : (1 + getLength(node.next));
    }
}