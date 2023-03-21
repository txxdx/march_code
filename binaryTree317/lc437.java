//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1567 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 // 遍历每一个节点，统计以每一个节点为根的路径和，注意路径和是否会溢出
class Solution {
    int ans, t;
    public int pathSum(TreeNode root, int targetSum) {
        t = targetSum;
        preOrder(root);
        return ans;
    }

    private void preOrder(TreeNode root) {
        if (root == null) return;
        sum(root, root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void sum(TreeNode root, long val) {
        if (val == t) {
            ans ++;
        }
        if (root.left != null) {
            sum(root.left, val + root.left.val);
        }
        if (root.right != null) {
            sum(root.right, val + root.right.val);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    int ans, t;
    Map<Long, Integer> map = new HashMap<>();

    // sum(a...b) - sum(0...a-1)
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        t = targetSum;
        map.put(0L, 1);
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode root, long sum) {
        if (map.containsKey(sum - t)) {
            ans += map.get(sum - t);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        if (root.left != null) {
            dfs(root.left, sum + root.left.val);
        }
        if (root.right != null) {
            dfs(root.right, sum + root.right.val);
        }
        map.put(sum, map.getOrDefault(sum, 0) - 1);
    }
}