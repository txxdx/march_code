//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 1072 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return preOrder(root);
    }

    private String preOrder(TreeNode root) {
        if (root == null) {
            return "null";
        }
        return root.val + "#" + preOrder(root.left) + "#" + preOrder(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split("#");
        Deque<String> queue = new ArrayDeque<>();
        for (String s: strs) {
            queue.addLast(s);
        }

        return deserialize(queue);
    }

    private TreeNode deserialize(Deque<String> q) {
        if (q.isEmpty()) return null;

        String poll = q.pollFirst();
        if (poll.equals("null")) return null;

        TreeNode root = new TreeNode(Integer.valueOf(poll));
        root.left = deserialize(q);
        root.right = deserialize(q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


public class Codec {
    int INF = 1009;
    TreeNode emptyNode = new TreeNode(INF);

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            TreeNode poll = q.pollFirst();
            sb.append(poll.val + "#");
            if (poll.val != INF) {
                q.addLast(poll.left != null ? poll.left : emptyNode);
                q.addLast(poll.right != null ? poll.right : emptyNode);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "" || data == null) return null;

        String[] ss = data.split("#");
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);

        for (int i = 1; i < ss.length - 1;  i += 2) {
            TreeNode node = q.pollFirst();
            int a = Integer.parseInt(ss[i]);
            int b = Integer.parseInt(ss[i+1]);
            if (a != INF) {
                node.left = new TreeNode(a);
                q.addLast(node.left);
            }
            if (b != INF) {
                node.right = new TreeNode(b);
                q.addLast(node.right);
            }
        }

        return root;
    }

}