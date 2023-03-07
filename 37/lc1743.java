/**
存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。

给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。

题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。

返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。

示例 1：

输入：adjacentPairs = [[2,1],[3,4],[3,2]]
输出：[1,2,3,4]
解释：数组的所有相邻元素对都在 adjacentPairs 中。
特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
 */

class Solution {
    public int[] restoreArray(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> counts = new HashMap<>();
        Map<Integer, List<Integer>> links = new HashMap<>();
        for (int[] pair: pairs) {
            int a = pair[0], b = pair[1];
            counts.put(a, counts.getOrDefault(a, 0) + 1);
            counts.put(b, counts.getOrDefault(b, 0) + 1);
            List<Integer> listA = links.getOrDefault(a, new LinkedList<>());
            listA.add(b);
            links.put(a, listA);

            List<Integer> listB = links.getOrDefault(b, new LinkedList<>());
            listB.add(a);
            links.put(b, listB);
        }

        int start = -1;
        for (int i : counts.keySet()) {
            if (counts.get(i) == 1) {
                start = i;
                break;
            }
        }

        int m = pairs.length + 1;
        int[] ans = new int[m];
        Set<Integer> visited = new HashSet<>();
        ans[0] = start;
        visited.add(start);
        for (int i = 1; i < m; i ++) {
            int pre = ans[i - 1];
            List<Integer> list = links.get(pre);
            for (int num: list) { //最多两个
                if (!visited.contains(num)) {
                    ans[i] = num;
                    visited.add(num);
                }
            }
        }

        return ans;
    }
}