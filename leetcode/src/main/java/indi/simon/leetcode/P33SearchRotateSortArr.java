package indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P33SearchRotateSortArr {

    public static void main(String[] args) {

        int[] test = new int[]{4, 5, 6, 7, 0, 1, 2};

        int position = search(test, 0);

        System.out.println(position);

    }

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int rotateIndex = findRotatePoint(nums, 0, nums.length - 1);
        if (rotateIndex == -1) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }
        if (target > nums[0]) {
            return binarySearch(nums, 0, rotateIndex - 1, target);
        } else if (target < nums[0]) {
            return binarySearch(nums, rotateIndex, nums.length - 1, target);
        } else {
            return 0;
        }
    }

    private static int findRotatePoint(int[] nums, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return startIndex;
        }
        int mid = (startIndex + endIndex) / 2;
        if (mid == startIndex && nums[endIndex] < nums[0]) {
            return endIndex;
        } else if (mid == startIndex) {
            return -1;
        }
        if (nums[mid] > nums[0]) {
            return findRotatePoint(nums, mid, endIndex);
        } else if (nums[mid] < nums[0]) {
            return findRotatePoint(nums, startIndex, mid);
        }
        return -1;
    }

    private static int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
        if (startIndex > endIndex || (startIndex == endIndex && nums[startIndex] != target)) {
            return -1;
        }
        if (startIndex == endIndex) {
            return startIndex;
        }
        int mid = (startIndex + endIndex) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, startIndex, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, endIndex, target);
        }
    }


}
//todo: 思路：首先找到塌方点，然后根据给点的值比nums[0]大还是小分两种情况，大则在0到塌方点之间进行二分查找，小则在塌方点
// 到数组结尾进行二分查找。那关键就是以低于O(logn)的复杂度找到塌方点，考虑先比较开头为0和正中间的两个元素，如果后者大于前者
// 则说明塌方点还在后面，比较正中间和0.75n两个元素，以此类推