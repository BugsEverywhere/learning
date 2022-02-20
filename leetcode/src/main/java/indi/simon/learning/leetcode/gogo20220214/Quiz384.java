package indi.simon.learning.leetcode.gogo20220214;

import java.util.Random;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz384 {

    public static void main(String[] args) {


    }

    private int[] nums;

    public Quiz384(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        Random random = new Random();
        int[] shuffledArr = new int[nums.length];
        boolean[] occupyMarkArr = new boolean[nums.length];
        for (int num : nums) {
            int j = Math.abs(random.nextInt() % shuffledArr.length);
            while (j < shuffledArr.length) {
                if (occupyMarkArr[j]) {
                    //该位置已经被占了，往下继续找
                    j = Math.abs(random.nextInt() % shuffledArr.length);
                    //todo: 此处一开始是简单j++（到末尾返回0），没通过，改成重新随机一个索引就过了。。这题没啥意思
                } else {
                    //该位置还没被占
                    shuffledArr[j] = num;
                    occupyMarkArr[j] = true;
                    break;
                }
            }
        }
        return shuffledArr;
    }
}
