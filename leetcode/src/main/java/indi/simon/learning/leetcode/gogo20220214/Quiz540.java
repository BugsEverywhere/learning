package indi.simon.learning.leetcode.gogo20220214;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz540 {

    public static void main(String[] args) {

        Quiz540 quiz540 = new Quiz540();

        int[] nums = new int[]{1, 1, 2, 2, 4, 4, 5, 5, 9};

        int res = quiz540.singleNonDuplicate(nums);

        System.out.println(res);

    }

    public int singleNonDuplicate(int[] nums) {
        int resIndex = binaryBioSearch(nums, 0, nums.length - 2);
        if (resIndex == nums.length - 3) {
            if (nums[nums.length - 3] == nums[nums.length - 2]) {
                return nums[nums.length - 1];
            } else {
                return nums[nums.length - 3];
            }
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

        int middlePairLeftIndex = (maxIndex - minIndex + 1) % 4 == 0 ? minIndex + (maxIndex - minIndex + 1) / 2 - 2 : minIndex + (maxIndex - minIndex) / 2;
        //todo:这里多次出错，首先数组长度可被4整除和不能被4整除的情况下，求中间对的下标算错了；再然后忘记二分法通过下标截取数组，因而算出来的下表结果要从截取的minIndex加起

        if (originArr[middlePairLeftIndex] != originArr[middlePairLeftIndex + 1]) {
            //如果中间对，左元素不等于右元素，将包含他们的左数组继续递归
            return binaryBioSearch(originArr, minIndex, middlePairLeftIndex + 1);
        } else {
            //如果中间对，左元素等于右元素，将不包含他们的右数组继续递归
            return binaryBioSearch(originArr, middlePairLeftIndex + 2, maxIndex);
            //todo:此处粗心错了一次，第二个入参传了minIndex+2而不是middlePairLeftIndex + 2
        }

    }


}
