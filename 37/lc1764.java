/** 
给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。

你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。（也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）

如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。

如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。

示例 1：

输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
输出：true
解释：你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
这两个子数组是不相交的，因为它们没有任何共同的元素。
*/
class Solution {
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = nums.length, m = groups.length;
        int rows = 0;
        for (int i = 0, j = 0; i < n && j < m;) {
            if (check(groups[j], nums, i)) {
                i += groups[j].length;
                j ++;
                rows ++;
            } else {
                i ++;
            }
        }

        return rows == m;
    }

    private boolean check(int[] g, int[] nums, int idx) {
        int i = 0;
        while (i < g.length && idx < nums.length) {
            if (g[i] != nums[idx]) {
                return false;
            }
            i ++;
            idx ++;
        }

        return i == g.length;
    }
}