//给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//
// 
//输入：nums = [3,10,5,25,2,8]
//输出：28
//解释：最大运算结果是 5 XOR 25 = 28. 
//
// 示例 2： 
//
// 
//输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//输出：127
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁵ 
// 0 <= nums[i] <= 2³¹ - 1 
// 
//
// Related Topics 位运算 字典树 数组 哈希表 👍 529 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class TrieNode {
        TrieNode left;
        TrieNode right;
    }

    TrieNode root = new TrieNode();

    void insert(int num) {
        TrieNode cur = root;
        for (int i = 30; i >= 0; i--) {
            int t = (num >> i) & 1;
            if (t == 0) {
                if (cur.left == null) {
                    cur.left = new TrieNode();
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new TrieNode();
                }
                cur = cur.right;
            }
        }
    }

    int get(int num) {
        TrieNode cur = root;
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            int x = (num >> i) & 1;
            if (x == 0) {
                if (cur.right != null) {
                    ans = ans * 2 + 1;
                    cur = cur.right;
                } else {
                    ans *= 2;
                    cur = cur.left;
                }
            } else {
                if (cur.left != null) {
                    ans = ans * 2 + 1;
                    cur = cur.left;
                } else {
                    ans *= 2;
                    cur = cur.right;
                }
            }
        }
        return ans;
    }

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 1; i ++) {
            insert(nums[i]);
            ans = Math.max(ans, get(nums[i + 1]));
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    class TrieNode {
        TrieNode next[] = new TrieNode[2];
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        int ans = 0;
        for (int num : nums) {
            TrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.next[bit] == null) {
                    cur.next[bit] = new TrieNode();
                }
                cur = cur.next[bit];
            }

            int x = num;
            int res = 0;
            cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (x >> i) & 1;
                if (cur.next[1 - bit] != null) {
                    res |= (1 << i);
                    cur = cur.next[1 - bit];
                } else {
                    cur = cur.next[bit];
                }
            }

            ans = Math.max(ans, res);
        }
        return ans;
    }
}