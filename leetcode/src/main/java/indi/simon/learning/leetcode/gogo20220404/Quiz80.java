package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz80 {

    public static void main(String[] args) {
        Quiz80 quiz80 = new Quiz80();
        int[] arr = new int[]{1, 1, 1, 1};
        int res = quiz80.removeDuplicates(arr);
        System.out.println(res);
    }

    public int removeDuplicates(int[] nums) {
        int cursor = 0;
        int totalRedundantCount = 0;
        while (cursor < nums.length - totalRedundantCount) {
            int countOfThisNum = 0;
            int point = cursor;
            int currentNum = nums[cursor];
            while (point < nums.length && nums[point] == currentNum) {
                countOfThisNum++;
                if (countOfThisNum > 2) {
                    //多余的数，往后移动，point不用动
                    moveToEnd(nums, point);
                    totalRedundantCount++;
                    if (point == nums.length - totalRedundantCount) {
                        break;
                    }
                    //todo 前两次提交失败都是此处没有考虑point跳出循环的边界条件，需要在每次坍缩之后比对当前point与有效数组结尾是否遇到
                } else {
                    //2个以内，往前进位
                    point++;
                }
            }
            cursor = point;
        }
        return nums.length - totalRedundantCount;
    }

    private void moveToEnd(int[] nums, int index) {
        while (index + 1 < nums.length) {
            swap(nums, index, index + 1);
            index = index + 1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
