/**
给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。

每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

返回 承载所有人所需的最小船数 。

示例 1：

输入：people = [1,2], limit = 3
输出：1
解释：1 艘船载 (1, 2)

 */
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int left = 0, right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left ++;
            } 
            right --;
            ans ++;
        }
        return ans;
    }
}