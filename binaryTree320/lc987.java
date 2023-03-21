//给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。 
//
// 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的
//根结点位于 (0, 0) 。 
//
// 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则
//按结点的值从小到大进行排序。 
//
// 返回二叉树的 垂序遍历 序列。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[9],[3,15],[20],[7]]
//解释：
//列 -1 ：只有结点 9 在此列中。
//列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
//列  1 ：只有结点 20 在此列中。
//列  2 ：只有结点 7 在此列中。 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//列 -2 ：只有结点 4 在此列中。
//列 -1 ：只有结点 2 在此列中。
//列  0 ：结点 1 、5 和 6 都在此列中。
//          1 在上面，所以它出现在前面。
//          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
//列  1 ：只有结点 3 在此列中。
//列  2 ：只有结点 7 在此列中。
// 
//
// 示例 3： 
// 
// 
//输入：root = [1,2,3,4,6,5,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
//因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。 
//
// 
//
// 提示： 
//
// 
// 树中结点数目总数在范围 [1, 1000] 内 
// 0 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 哈希表 二叉树 👍 233 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

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
    class Info {
        int col, row, val;

        Info (int col, int row, int val) {
            this.col = col;
            this.row = row;
            this.val = val;
        }
    }

    Map<TreeNode, Info> map = new HashMap<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        map.put(root, new Info(0, 0, root.val));
        dfs(root);

        List<Info> list = new ArrayList<>(map.values());
        Collections.sort(list, (a, b) -> {
            if (a.col != b.col) return a.col - b.col;
            if (a.row != b.row) return a.row - b.row;
            return a.val - b.val;
        });

        int i = 0;
        int n = list.size();
        while (i < n) {
            int j = i;
            List<Integer> tmp = new ArrayList<>();
            while (j < n && list.get(i).col == list.get(j).col) {
                tmp.add(list.get(j).val);
                j ++;
            }
            ans.add(tmp);
            i = j;
        }

        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        Info info = map.get(root);

        if (root.left != null) {
            map.put(root.left, new Info(info.col - 1, info.row + 1, root.left.val));
            dfs(root.left);
        }
        if (root.right != null) {
            map.put(root.right, new Info(info.col + 1, info.row + 1, root.right.val));
            dfs(root.right);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    class Info {
        int col, row, val;

        Info (int col, int row, int val) {
            this.col = col;
            this.row = row;
            this.val = val;
        }
    }

    PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> {
        if (a.col != b.col) {
            return a.col - b.col;
        }
        if (a.row != b.row) {
            return a.row - b.row;
        }
        return a.val - b.val;
    });

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return null;
        Info rootInfo = new Info(0, 0, root.val);
        pq.add(rootInfo);
        dfs(root, rootInfo);

        List<List<Integer>> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            Info poll = pq.peek();
            while (!pq.isEmpty() && poll.col == pq.peek().col) {
                tmp.add(pq.poll().val);
            }
            ans.add(tmp);
        }

        return ans;
    }

    private void dfs(TreeNode root, Info info) {
        if (root == null) return;
        if (root.left != null) {
            Info linfo = new Info(info.col - 1, info.row + 1, root.left.val);
            pq.add(linfo);
            dfs(root.left, linfo);
        }
        if (root.right != null) {
            Info rinfo = new Info(info.col + 1, info.row + 1, root.right.val);
            pq.add(rinfo);
            dfs(root.right, rinfo);
        }
    }
}