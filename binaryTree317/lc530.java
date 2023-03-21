class Solution {
    TreeNode preNode = null;
    int minVal = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return minVal;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (preNode != null) {
            minVal = Math.min(root.val - preNode.val, minVal);
        }
        preNode = root;
        dfs(root.right);
    }
}