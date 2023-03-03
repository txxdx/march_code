class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                int curIdx = queue.poll();
                if (curIdx == n - 1) {
                    return ans;
                }

                for (int nextIdx = curIdx + 1; nextIdx <= curIdx + nums[curIdx] && nextIdx < n; nextIdx ++) {
                    if (!visited[nextIdx]) {
                        queue.add(nextIdx);
                        visited[nextIdx] = true;
                    }
                }
            }
            ans ++;
        }
        return ans;
    }
}


// 动态规划
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1, j = 0; i < n; i ++) {
            while (j + nums[j] < i) j ++;
            dp[i] = dp[j] + 1;
        }
        return dp[n-1];
    }
}