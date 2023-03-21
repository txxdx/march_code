//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。 
//
// 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。 
//
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
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
//输出：[7,4,1]
//解释：所求结点为与目标结点（值为 5）距离为 2 的结点，值分别为 7，4，以及 1
// 
//
// 示例 2: 
//
// 
//输入: root = [1], target = 1, k = 3
//输出: []
// 
//
// 
//
// 提示: 
//
// 
// 节点数在 [1, 500] 范围内 
// 0 <= Node.val <= 500 
// Node.val 中所有值 不同 
// 目标结点 target 是树上的结点。 
// 0 <= k <= 1000 
// 
//
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 618 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<Integer, TreeNode> map = new HashMap<>();
    List<Integer> ans = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParents(root);
        findDistance(target, null, 0, k);
        return ans;
    }

    private void findParents(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            map.put(root.left.val, root);
            findParents(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            findParents(root.right);
        }
    }

    private void findDistance(TreeNode node, TreeNode from, int level, int k) {
        if (node == null) return;

        if (level == k) {
            ans.add(node.val);
            return;
        }

        if (node.left != from) {
            findDistance(node.left, node, level + 1, k);
        }
        if (node.right != from) {
            findDistance(node.right, node, level + 1, k);
        }
        if (map.get(node.val) != from) {
            findDistance(map.get(node.val), node, level + 1, k);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


// 建图 + BFS
class Solution {

    int M = 1010;
    LinkedList<Integer>[] adj = new LinkedList[M];

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null || target == null) {
            return ans;
        }

        buildGraph(root);

        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[M];
        q.addLast(target.val);
        visited[target.val] = true;

        while (!q.isEmpty() && k >= 0) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                int poll = q.pollFirst();
                if (k == 0) {
                    ans.add(poll);
                    continue;
                }

                for (Integer child: adj[poll]) {
                    if (!visited[child]) {
                        q.addLast(child);
                        visited[child] = true;
                    }
                }
            }
            k --;
        }

        return ans;
    }

    private void buildGraph(TreeNode node) {
        if (node == null) return;
        if (adj[node.val] == null) adj[node.val] = new LinkedList<>();
        if (node.left != null) {
            if (adj[node.left.val] == null) adj[node.left.val] = new LinkedList<>();
            adj[node.val].add(node.left.val);
            adj[node.left.val].add(node.val);
            buildGraph(node.left);
        }
        if (node.right != null) {
            if (adj[node.right.val] == null) adj[node.right.val] = new LinkedList<>();
            adj[node.val].add(node.right.val);
            adj[node.right.val].add(node.val);
            buildGraph(node.right);
        }
    }
}