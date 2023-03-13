//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
//
// Related Topics 递归 链表 👍 1941 👎 0

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null) {
            ListNode tmp = cur;
            for (int i = 0; i < k && tmp != null; i ++) {
                tmp = tmp.next;
            }
            if (tmp == null) {
                break;
            }
            // dummy(cur) -> [1 -> 2 -> 3(tmp)] -> [4 -> 5 -> 6 -> 7]
            // reverse
            ListNode first = cur.next;
            ListNode second = first.next;
            // dummy(cur) -> [1(first) -> 2(second) -> 3(tmp)] -> [4 -> 5 -> 6 -> 7]
            for (int i = 0; i < k - 1; i ++) {
                ListNode next = second.next;
                second.next = first;
                first = second;
                second = next;
            }
            // dummy(cur) -> [1 <- 2 <- 3(tmp/first)] -> [4(second) -> 5 -> 6 -> 7]

            tmp = cur.next;
            // dummy(cur) -> [1(tmp) <- 2 <- 3(first)] -> [4(second) -> 5 -> 6 -> 7]
            cur.next.next = second;
            // dummy(cur) -> [1(tmp) <- 2 <- 3(first)] -> [4(second) -> 5 -> 6 -> 7]
            //                  |                            ^
            //                  | ---------------------------|

            cur.next = first;
                     |---------------------------|
            // dummy(cur) -> [1(tmp) <- 2 <- 3(first)] -> [4(second) -> 5 -> 6 -> 7]
            //                  |                            ^
            //                  | ---------------------------|

            // dummy(cur) -> 3(first) -> 2 -> 1(tmp) -> [4(second) -> 5 -> 6 -> 7]
            cur = tmp;
            // dummy -> 3(first) -> 2 -> 1(tmp/cur) -> [4(second) -> 5 -> 6 -> 7]
        }

        return dummy.next;
    }
}


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        // check
        ListNode cur = head;
        for (int i = 0; i < k - 1 && cur != null; i ++) {
            cur = cur.next;
        }
        if (cur == null) {
            return head;
        }


        // 1(head) - > 2 -> 3 -> 4 -> 5 -> 6 -> 7;
        ListNode first = head;
        ListNode second = head.next;
        // 1(head/first) - > 2(second) -> 3 -> 4 -> 5 -> 6 -> 7;
        for (int i = 0; i < k - 1; i ++) {
            ListNode next = second.next;
            second.next = first;
            first = second;
            second = next;
        }

        // 1(head) <- 2 <- 3(first) -> [4(second) -> 5 -> 6 -> 7];

        head.next = reverseKGroup(second, k);
        head = first;

        return head;
    }
}
