class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

// 1. n的范围， 1 <= n <= size
// 2. head为空

class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode slow = head;
    ListNode fast = head;
    while (n-- > 0) {
      fast = fast.next;
    }

    if (fast == null) {
      return head.next;
    }

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;

    return head;
  }
}
