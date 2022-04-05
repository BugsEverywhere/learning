package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz81_notfinish {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1};
        Quiz81_notfinish quiz81 = new Quiz81_notfinish();
        boolean res = quiz81.search(arr, 0);
        System.out.println(res);
    }

    public boolean search(int[] nums, int target) {
        //先找到旋转点
        int left = 0;
        int right = nums.length - 1;

        int rotatePoint = 0;
        while (left <= right) {
            if (left == right) {
                rotatePoint = left;
                break;
            }
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                //mid在小数区
                if (mid - 1 > left && nums[mid - 1] < nums[right]) {
                    right = mid - 1;
                } else {
                    rotatePoint = mid - 1;
                    break;
                }
            } else if (nums[mid] > nums[right]) {
                //mid在大数区
                if (mid + 1 < right && nums[mid + 1] > nums[right]) {
                    left = mid + 1;
                } else {
                    rotatePoint = mid;
                    break;
                }
            } else if (nums[mid] == nums[right]) {
                //存在两种情况，mid在大数区，以及mid在小数区
                if (nums[mid] > nums[left]) {
                    //mid在大数区
                    if (mid + 1 < right && nums[mid + 1] > nums[right]) {
                        left = mid + 1;
                    } else {
                        rotatePoint = mid;
                        break;
                    }
                } else if (nums[mid] < nums[left]) {
                    //mid在小数区
                    if (mid - 1 > left && nums[mid - 1] < nums[right]) {
                        right = mid - 1;
                    } else {
                        rotatePoint = mid - 1;
                        break;
                    }
                } else if (nums[mid] == nums[left]) {
                    //此时mid与nums[left]以及nums[right]相等，无法判断此时mid处于大数区还是小数区，例如[3,5,1,3,3,3,3]，left为0，right为6的情况
                    while (mid >= left) {
                        if (nums[mid] == nums[left]) {
                            mid--;
                        } else {
                            break;
                        }
                    }

                    if (mid < left) {
                        //原mid在大数区
                        left++;
                    } else {
                        //原mid在小数区
                        right--;
                    }
                }
            }
        }

        if (target > nums[rotatePoint]) {
            //比大区最大还大
            return false;
        } else if (rotatePoint + 1 < nums.length && target < nums[rotatePoint + 1]) {
            //比小区最小还小
            return false;
        } else if (target >= nums[0] && target <= nums[rotatePoint]) {
            //在大数区找
            return pureBinarySearch(nums, target, 0, rotatePoint);
        } else {
            //在小数区找
            return pureBinarySearch(nums, target, rotatePoint + 1, nums.length - 1);
        }
    }

    private boolean pureBinarySearch(int[] nums, int target, int leftFrontier, int rightFrontier) {
        while (leftFrontier <= rightFrontier) {
            int mid = (leftFrontier + rightFrontier) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                rightFrontier = mid - 1;
            } else if (nums[mid] < target) {
                leftFrontier = mid + 1;
            }
        }
        return false;
    }


    //todo: 以上是我的题解，败在了[1,3]这个用例上，但是私以为这个用例不合理，没有在任何下标进行旋转，所以也比较无语。。。。。以下是官方的题解：


    public boolean searchOfficial(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                //找到直接返回
                return true;
            }
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                //这就是我上面那种最复杂的情况，[3,5,1,3,3,3,3]，left为0，right为6的情况，此时只能缩小一点left和right窗口
                ++left;
                --right;
            } else if (nums[left] <= nums[mid]) {
                //mid大于等于left，要么mid和left都在大数区，要么两者都在小数区，反正这两者在同一个单调区间，也就是[left,mid]肯定是一个单调区间
                if (nums[left] <= target && target < nums[mid]) {
                    //target就在此单调区间内，缩小right到mid-1
                    right = mid - 1;
                } else {
                    //target在[mid,right]这个区间内，该区间不一定单调，把left置为mid+1
                    left = mid + 1;
                }
            } else {
                //mid比left小，只可能mid在小数区，而left在大数区，那么此时[mid,right]肯定是单调区间
                if (nums[mid] < target && target <= nums[right]) {
                    //target在[mid,right]这个单调区间内，left置为mid+1
                    left = mid + 1;
                } else {
                    //target在[left,mid]这个区间内，该区间肯定不单调，right移动过来继续找
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    //todo: 在排除了left==mid==right的特殊情况之后，
    // 通过判断mid和left的大小来判断mid的位置，mid比left大和小两种情况，都能将[left,right]用mid分为一个单调区间和一个不知道是否单调的区间，
    // 如果target落在单调区间内，则事情就好办了，缩小窗口继续递归找。如果target不在单调区间内，那就到另一个区间去找，每次如此循环，最终能确定是否能找到target

}
