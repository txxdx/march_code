//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
//
// Related Topics 链表 双指针 👍 1069 👎 0


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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        // (dummy/prev) -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> null
        while (prev.next != null) {
            ListNode cur = prev.next;
            ListNode tail = cur.next;
            // (dummy/prev) -> 1(cur) -> 2(tail) -> 3 -> 3 -> 4 -> 4 -> 5 -> null
            // (dummy) -> 1 -> 2(prev) -> 3(cur) -> 3(tail) -> 4 -> 4 -> 5 -> null
            // (dummy) -> 1 -> 2(prev) -> 4(cur) -> 4(tail) -> 5 -> null
            // (dummy) -> 1 -> 2(prev)  -> 5(cur) -> null(tail)
            while (tail != null && tail.val == cur.val) {
                tail = tail.next;
            }
            // (dummy/prev) -> 1(cur) -> 2(tail) -> 3 -> 3 -> 4 -> 4 -> 5 -> null
            // (dummy) -> 1 -> 2(prev) -> 3(cur) -> 3 -> 4(tail) -> 4 -> 5 -> null
            // (dummy) -> 1 -> 2(prev) -> 4(cur) -> 4 -> 5(tail) -> null
            if (cur.next == tail) {
                prev = prev.next;
                // (dummy) -> 1(cur/prev) -> 2(tail) -> 3 -> 3 -> 4 -> 4 -> 5 -> null
                // (dummy) -> 1 -> 2  -> 5(prev) -> null(tail)
            } else {
                prev.next = tail;
                // (dummy) -> 1 -> 2(prev) -> 4(tail) -> 4 -> 5 -> null
                // (dummy) -> 1 -> 2(prev)  -> 5(tail) -> null
            }
        }

        // (dummy) -> 1 -> 2  -> 5 -> null
        return dummy.next;
    }
}


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (head != null) {
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = tail.next;
            }

            while (head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            head = head.next;
        }
        
        tail.next = null;
        return dummy.next;
    }
}