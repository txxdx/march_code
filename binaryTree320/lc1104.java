//在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。 
//
// 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记； 
//
// 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。 
//
// 
//
// 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。 
//
// 
//
// 示例 1： 
//
// 输入：label = 14
//输出：[1,3,4,14]
// 
//
// 示例 2： 
//
// 输入：label = 26
//输出：[1,2,6,10,26]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= label <= 10^6 
// 
//
// Related Topics 树 数学 二叉树 👍 192 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> ans = new LinkedList<>();
        while (label > 0) {
            ans.add(0, label);
            label >>= 1;
        }

        boolean reverse = false;
        for (int i = ans.size() - 1; i >= 0; i --) {
            if (reverse) {
                int num = (1 << i) + (1 << i + 1) - 1 - ans.get(i);
                ans.set(i, num);
            }
            reverse = !reverse;
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> list = new LinkedList<>();
        int row = 1, val = 1;
        while (val * 2 <= label) {
            row ++;
            val <<= 1;
        }

        boolean reversed = false;
        for (int i = row - 1; i >= 0; i --) {
            if (reversed) {
                int num = (1 << i) + (1 << i + 1) - 1 - label;
                list.add(0, num);
            } else {
                list.add(0, label);
            }
            label >>= 1;
            reversed = !reversed;
        }

        return list;
    }
}