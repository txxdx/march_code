//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。 
//
// 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。 
//
// 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。 
//
// 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
// 
//
// 示例 3： 
//
// 
//
// 
//输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点数介于 2 到 100 之间。 
// 每个节点的值都是唯一的、范围为 1 到 100 的整数。 
// 
//
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 290 👎 0


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
    public boolean isCousins(TreeNode root, int x, int y) {
        int[] xinfo = dfs(root, null, x, 0);
        int[] yinfo = dfs(root, null, y, 0);
        return xinfo[0] != yinfo[0] && (xinfo[1] == yinfo[1]);
    }

    private int[] dfs(TreeNode node, TreeNode parent, int t, int depth) {
        if (node == null) {
            return new int[] { -1, -1 };
        }
        if (node.val == t) {
            return new int[] { parent == null ? 0 : parent.val, depth };
        }

        int[] left = dfs(node.left, node, t, depth + 1);
        if (left[0] != -1) {
            return left;
        }

        return dfs(node.right, node, t, depth + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    class Info {
        TreeNode node;
        TreeNode parent;
        int depth;

        Info (TreeNode node, TreeNode parent, int depth) {
            this.node = node;
            this.parent = parent;
            this.depth = depth;
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        int[] xinfo = bfs(root, x);
        int[] yinfo = bfs(root, y);
        return xinfo[0] != yinfo[0] && (xinfo[1] == yinfo[1]);
    }

    private int[] bfs(TreeNode node, int t) {
        if (node == null) {
            return new int[] { -1, -1 };
        }
        Deque<Info> q = new ArrayDeque<>();
        q.add(new Info(node, null, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                Info poll = q.pollFirst();

                if (poll.node.val == t) {
                    return new int[]{ poll.parent != null ? poll.parent.val : 0, poll.depth };
                }
                if (poll.node.left != null) {
                    q.addLast(new Info(poll.node.left, poll.node, poll.depth + 1));
                }
                if (poll.node.right != null) {
                    q.addLast(new Info(poll.node.right, poll.node, poll.depth + 1));
                }
            }
        }

        return new int[] { -1, - 1 };
    }
}