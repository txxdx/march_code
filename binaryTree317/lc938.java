//给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
//输出：32
// 
//
// 示例 2： 
// 
// 
//输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 2 * 10⁴] 内 
// 1 <= Node.val <= 10⁵ 
// 1 <= low <= high <= 10⁵ 
// 所有 Node.val 互不相同 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 316 👎 0


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
class Solution {
    int ans = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        dfs(root, low, high);
        return ans;
    }

    private void dfs(TreeNode node, int low, int high) {
        if (node == null) return;
        dfs(node.left, low, high);
        if (node.val >= low && node.val <= high) {
            ans += node.val;
        }
        dfs(node.right, low, high);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int ans = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addLast(root);
                root = root.left;
            }

            root = stack.pollLast();
            if (root.val >= low && root.val <= high) {
                ans += root.val;
            }

            root = root.right;

        }

        return ans;
    }

}