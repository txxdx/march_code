//在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。 
//
// 当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间
//移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。 
//
// 你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: grid = [[0,2],[1,3]]
//输出: 3
//解释:
//时间为0时，你位于坐标方格的位置为 (0, 0)。
//此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
//等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
// 
//
// 示例 2: 
//
// 
//
// 
//输入: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,
//9,8,7,6]]
//输出: 16
//解释: 最终的路线用加粗进行了标记。
//我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
// 
//
// 
//
// 提示: 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 50 
// 0 <= grid[i][j] < n² 
// grid[i][j] 中每个值 均无重复 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 264 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int left = 0, right = n * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(grid, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int[][] grid, int time) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            if (x == n - 1 && y == n - 1) {
                return true;
            }

            for (int d = 0; d < 4; d ++) {
                int newX = x + dirs[d][0];
                int newY = y + dirs[d][1];
                int[] newPos = new int[] {newX, newY};
                if (inArea(grid, newX, newY) && !visited[newX][newY] && canMove(grid, cur, newPos, time)) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        return false;
    }

    private boolean inArea(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid.length;
    }

    private boolean canMove(int[][] grid, int[] from, int[] to, int time) {
        return time >= Math.max(grid[from[0]][from[1]], grid[to[0]][to[1]]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    private int[][] grid;
    private int n;

    public int swimInWater(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        List<int[]> edges = new ArrayList<>();
        // Connect each cell to its right and bottom neighbors
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int u = i * n + j;
                if (j < n - 1) { // connect to bottom
                    int v = i * n + (j + 1);
                    int w = Math.max(grid[i][j], grid[i][j + 1]);
                    edges.add(new int[]{u, v, w});
                }
                if (i < n - 1) { // connect to right
                    int v = (i + 1) * n + j;
                    int w = Math.max(grid[i][j], grid[i + 1][j]);
                    edges.add(new int[]{u, v, w});
                }
            }
        }
        // Sort edges by weight
        Collections.sort(edges, Comparator.comparingInt(e -> e[2]));
        // Initialize disjoint sets, union-find
        int[] parent = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            parent[i] = i;
        }
        // Find minimum spanning tree
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            union(parent, u, v);
            if (find(parent, 0)  == find(parent, n * n - 1)) {
                return w; // Exit early if start and end nodes are connected
            }
        }
        return 0;
    }

    private int find(int[] parent, int u) {
        while (parent[u] != u) {
            parent[u] = parent[parent[u]];
            u = parent[u];
        }
        return u;
    }

    private void union(int[] parent, int u, int v) {
        parent[find(parent, u)] = parent[find(parent, v)];
    }
}