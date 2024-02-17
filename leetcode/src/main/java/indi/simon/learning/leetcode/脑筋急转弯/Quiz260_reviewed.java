package indi.simon.learning.leetcode.脑筋急转弯;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 不看答案鬼才能想到。需要熟悉位运算，a异或b异或b的情况下，结果等于a
public class Quiz260_reviewed {

    public static void main(String[] args) {

    }


    public int[] singleNumber(int[] nums) {
        //todo: 假设需要找的数是a和b，首先所有的数异或起来，最终的结果肯定等于a^b，以为其他数都成对两两抵消了
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }

        //todo: 这里是得到xorsum中仅最后一位（假设是第l位）为1的这个整数（其实仅任何一位为1的整数都可以）作为分类器，在所有的nums中，该位为0的数是type1，该位为1的数是type2，
        // a和b肯定分属于type1和type2，这样最终的异或出来的结果才会使该位为1.
        // 这种技巧了解一下吧。。。我说的是与自己的相反数与一下得到最后一位为1的数这种操作。
        // 而且当xorsum就等于Integer.MIN_VALUE得时候，也就是0x80000000，相反数就是它自己，两者与一下也能得到结果
        int lsb = (xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                //todo: num在l位为0，属于type1，把他和所有其他type1的数放在一起异或运算
                type1 ^= num;
            } else {
                //todo: num在l位为1，属于type2，把他和所有其他type2的数放在一起异或运算
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }

}
