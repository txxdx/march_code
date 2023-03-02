class Solution {
    public int maxArea(int[] heights) {
        int ans = 0;
        int left = 0, right = heights.length - 1;
        while (left < right) {
            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            ans = Math.max(width * height, ans);
            if (heights[left] < heights[right]) {
                left ++;
            } else {
                right --;
            }
        }

        return ans;
    }
}