package indi.simon.learning.leetcode.gogo20220214;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz540 {

    public static void main(String[] args) {

        Quiz540 quiz540 = new Quiz540();

        int[] nums = new int[]{1, 3, 3, 4, 4, 5, 5, 7, 7, 10, 10, 11, 11};

        int res = quiz540.singleNonDuplicate(nums);

        System.out.println(res);

    }

    public int singleNonDuplicate(int[] nums) {
        int resIndex = binaryBioSearch(nums, 0, nums.length - 2);
        if (resIndex == nums.length - 3) {
            return nums[nums.length - 1];
        } else {
            return nums[resIndex];
        }
    }

    /**
     * 需要保证传进来的一定是截去尾部一个元素的偶数长度的数组
     *
     * @param originArr
     * @return
     */
    private int binaryBioSearch(int[] originArr, int minIndex, int maxIndex) {

        if (maxIndex <= minIndex + 1) {
            return minIndex;
        }

        int middlePairLeftIndex = (maxIndex - minIndex + 1) % 4 == 0 ? (maxIndex - minIndex + 1) / 2 - 2 : (maxIndex - minIndex) / 2;

        if (originArr[middlePairLeftIndex] != originArr[middlePairLeftIndex + 1]) {
            //如果中间对，左元素不等于右元素，将包含他们的左数组继续递归
            return binaryBioSearch(originArr, minIndex, middlePairLeftIndex + 1);
        } else {
            //如果中间对，左元素等于右元素，将不包含他们的右数组继续递归
            return binaryBioSearch(originArr, middlePairLeftIndex + 2, maxIndex);
        }

    }


}
