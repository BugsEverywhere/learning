package indi.simon.learning.leetcode.gogo20220711;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 牛逼啊，可以可以
public class Quiz42 {

    public static void main(String[] args) {
        Quiz42 quiz42 = new Quiz42();
        int res = quiz42.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(res);
    }

    public int trap(int[] height) {
        //代表height中的第i个柱子上的水位，如果是-1标识该柱子是容器柱
        int[] dp = new int[height.length];

        dp[0] = -1;

        for (int i = 1; i < height.length; i++) {
            if (height[i] <= height[i - 1]) {
                //如果比前面一根柱子矮或者跟之前的相等，直接赋值-1并继续循环
                dp[i] = -1;
            } else {
                //如果大于之前的，遍历前面的柱子，
                dp[i] = -1;
                int justShorterPilarHeight = -1;
                int justShorterPilarIndex = -1;
                int j = i - 1;
                for (; j >= 0; j--) {
                    if (height[j] >= height[i]) {
                        //之前存在比本柱更高的，补齐中间的水位，跳出循环
                        fillWaterMark(height[i], j, i, dp, height);
                        break;
                    } else if (height[j] < height[i] && height[j] > justShorterPilarHeight) {
                        justShorterPilarHeight = height[j];
                        justShorterPilarIndex = j;
                    }
                }
                //遍历完了发现之前所有柱子都比本柱矮，以次高柱子高度补齐中间的水位
                if (justShorterPilarHeight > 0 && j < 0) {
                    fillWaterMark(justShorterPilarHeight, justShorterPilarIndex, i, dp, height);
                }
            }
        }

        //按照水位计算总的水量
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (dp[i] >= 0) {
                sum += dp[i] - height[i];
            }
        }

        return sum;
    }

    private void fillWaterMark(int waterMark, int start, int end, int[] dp, int[] height) {
        for (int index = start; index < end; index++) {
            if (waterMark > height[index]) {
                dp[index] = waterMark;
            }
        }
    }

}
