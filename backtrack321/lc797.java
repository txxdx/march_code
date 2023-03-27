//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序） 
//
// 
// graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i（即不存在自环） 
// graph[i] 中的所有元素 互不相同 
// 保证输入为 有向无环图（DAG） 
// 
//
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 391 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] graph;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        if (graph == null || graph.length == 0 || graph[0].length == 0) {
            return ans;
        }
        this.graph = graph;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, path, ans);
        return ans;
    }

    private void dfs(int v, List<Integer> path, List<List<Integer>> ans) {
        if (v == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int num: graph[v]) {
            path.add(num);
            dfs(num, path, ans);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        if (graph == null || graph.length == 0 || graph[0].length == 0) {
            return ans;
        }

        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        queue.offer(path);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                List<Integer> curr = queue.poll();
                int u = curr.get(curr.size() - 1);
                if (u == graph.length - 1) {
                    ans.add(curr);
                    continue;
                }

                for (int v: graph[u]) {
                    List<Integer> next = new ArrayList<>(curr);
                    next.add(v);
                    queue.offer(next);
                }
            }
        }

        return ans;
    }
}