/**
给定一个 n x n 的二进制矩阵 image ，先 水平 翻转图像，然后 反转 图像并返回 结果 。

水平翻转图片就是将图片的每一行都进行翻转，即逆序。

例如，水平翻转 [1,1,0] 的结果是 [0,1,1]。
反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。

例如，反转 [0,1,1] 的结果是 [1,0,0]。
示例 1：

输入：image = [[1,1,0],[1,0,1],[0,0,0]]
输出：[[1,0,0],[0,1,0],[1,1,1]]
解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]


 */
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n / 2; j ++) {
                int tmp = image[i][j];
                image[i][j] = image[i][n-j-1];
                image[i][n-j-1] = tmp;
            }
        }

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                image[i][j] = 1 - image[i][j];
            }
        }

        return image;
    }
}