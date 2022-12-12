package indi.simon.learning.leetcode.gogo20221121;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//思路是，将数组使用大数（大于right的数）分成若干块，计算每一块内的复合条件的子串个数，然后求和。
//每一块内的数分为[left,right]之间的数以及小于left的数（称之为小数），显而易见的是，只包含小数的子串肯定是不符合条件的，
// 该块的总子串个数减去这些小数子串的个数，就是每一块符合条件的子串的个数了
public class Quiz795 {

    public static void main(String[] args) {
        Quiz795 quiz795 = new Quiz795();
        int res = quiz795.numSubarrayBoundedMax(new int[]{73, 55, 36, 5, 55, 14, 9, 7, 72, 52}, 32, 69);
        System.out.println(res);
    }

    //todo: 第三次提交失败是因为溢出错误，所以中间计算的类型都使用了long，最终返回结果转为int，这种反正真实面试的时候不会care
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        long totalRes = 0;
        for (int i = 0; i < nums.length; ) {
            //统计该块内小于left的连续的数的下标
            List<long[]> subBlocks = new ArrayList<>();
            long[] currSubBlock = new long[2];
            Arrays.fill(currSubBlock, -1);
            int start = i;
            while (true) {
                if (i < nums.length && nums[i] < left) {
                    if (currSubBlock[0] == -1) {
                        currSubBlock[0] = i;
                        currSubBlock[1] = i;
                        //todo: 这里要注意，只有当currSubBlock都填充了值之后才能add进subBlocks，也就是add进去的必须是一个有效的subBlock
                        subBlocks.add(currSubBlock);
                    } else {
                        currSubBlock[1] = i;
                    }
                    i++;
                } else if (i < nums.length && nums[i] >= left && nums[i] <= right) {
                    if (currSubBlock[0] != -1) {
                        currSubBlock = new long[2];
                        Arrays.fill(currSubBlock, -1);
                    }
                    i++;
                } else if (i == nums.length || nums[i] > right) {
                    //计算所有小于left的连续子串的个数
                    int smallStrCount = 0;
                    for (long[] subBlock : subBlocks) {
                        smallStrCount += calculateSubStrCount(subBlock[1] - subBlock[0] + 1);
                    }
                    //计算该块内符合条件的子串个数
                    long blockRes = calculateSubStrCount(i - start) - smallStrCount;
                    totalRes += blockRes;
                    i++;
                    break;
                }
            }
        }
        return (int) totalRes;
    }

    /**
     * 计算strLength长度的字符串的所有连续子串个数，注意这里可能会是一个很大结果，要返回long
     * @param strLength
     * @return
     */
    private long calculateSubStrCount(long strLength) {
        //首项加末项乘以项数除以2
        return (strLength + 1) * strLength / 2;
    }

}
