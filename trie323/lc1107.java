//给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。 
//
// 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] 
//XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。 
//
// 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个
//查询的答案。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
//输出：[3,3,7]
//解释：
//1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
//2) 1 XOR 2 = 3.
//3) 5 XOR 2 = 7.
// 
//
// 示例 2： 
//
// 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
//输出：[15,-1,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length, queries.length <= 10⁵ 
// queries[i].length == 2 
// 0 <= nums[j], xi, mi <= 10⁹ 
// 
//
// Related Topics 位运算 字典树 数组 👍 143 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[2];
    }

    class Trie {
        private TrieNode root = new TrieNode();

        public void insert(int num) {
            TrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.next[bit] == null) {
                    cur.next[bit] = new TrieNode();
                }
                cur = cur.next[bit];
            }
        }

        public int getMaxXor(int num) {
            TrieNode cur = root;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.next[1 - bit] != null) {
                    ans |= (1 << i);
                    cur = cur.next[1 - bit];
                } else {
                    cur = cur.next[bit];
                }
            }

            return ans;
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = queries.length;
        int arr[][] = new int[m][3];
        for (int i = 0; i < m; i ++) {
            arr[i][0] = queries[i][0];
            arr[i][1] = queries[i][1];
            arr[i][2] = i;
        }
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);

        Trie trie = new Trie();
        int[] ans = new int[m];
        int idx = 0;
        for (int i = 0; i < m; i ++) {
            while (idx < n && nums[idx] <= arr[i][1]) {
                trie.insert(nums[idx]);
                idx ++;
            }

            if (idx == 0) {
                ans[arr[i][2]] = -1;
            } else {
                ans[arr[i][2]] = trie.getMaxXor(arr[i][0]);
            }
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
