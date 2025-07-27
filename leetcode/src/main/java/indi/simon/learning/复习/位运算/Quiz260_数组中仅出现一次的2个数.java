package indi.simon.learning.复习.位运算;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 不看答案鬼才能想到。需要熟悉位运算，a异或b异或b的情况下，结果等于a
public class Quiz260_数组中仅出现一次的2个数 {

    public static void main(String[] args) {

    }


    public int[] singleNumber(int[] nums) {
        //todo: 假设需要找的数是a和b，首先所有的数异或起来，最终的结果肯定等于a^b，因为其他数都成对两两抵消了
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }

        //todo: xorsum肯定不为0，因为如果为0则a肯定就等于b了，不符合题意。那么xorsum的所有位中肯定有某些位是1
        // 假设让xorsum仅保留最后一位1，其他全为0，得到整数lsb，作为分类器（其实取任何一位为1的整数都可以，
        // 影响的只不过是怎么获取到这个lsb的问题，取最后一个1最方便），那么在所有的nums中，该位为1的数是type1，
        // 该位为0的数是type2，a和b肯定分属于type1和type2，这样最终的异或出来的结果才会使该位为1.

        // todo: 这种技巧了解一下，与自己的相反数进行与运算就能得到最后一位为1的数这种操作。
        int lsb = (xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                //todo: num在l位为1，属于type1，把他和所有其他type1的数放在一起异或运算
                type1 ^= num;
            } else {
                //todo: num在l位为1，属于type2，把他和所有其他type2的数放在一起异或运算
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }

}
