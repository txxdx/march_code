//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
//
// Related Topics 链表 👍 1512 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        // (dummy/cur) -> 1 -> 2 -> 3 -> 4 -> 5 -> null
        for (int i = 0; i < left - 1 && cur != null; i ++) {
            cur = cur.next;
        }
        // (dummy) -> 1(cur) -> 2 -> 3 -> 4 -> 5 -> null
        if (cur == null) return head;

        ListNode first = cur.next;
        ListNode second = first.next;
        // (dummy) -> 1(cur) -> 2(first) -> 3(second) -> 4 -> 5 -> null
        for(int i = left; i < right; i ++) {
            ListNode next = second.next;
            second.next = first;
            first = second;
            second = next;
        }
        // (dummy) -> 1(cur) -> 2 <- 3 <- 4(first) -> 5(second) -> null

        cur.next.next = second;
        cur.next = first;

        // (dummy) -> 1(cur) -> 4(first) -> 3 -> 2 -> 5(second) -> null
        return  dummy.next;
    }
}