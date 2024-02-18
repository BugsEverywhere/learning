package indi.simon.learning.leetcode.gogo20240205;

/**
 * Created by Chen Zhuo on 2024/2/9.
 */
//todo: 二分以后就这么写，闭区间，好理解
public class Quiz2594_hinted {

    public static void main(String[] args) {
        Quiz2594_hinted quiz2594Hint = new Quiz2594_hinted();
        long res = quiz2594Hint.repairCars(new int[]{31,31,5,19,19,10,31,18,19,3,16,20,4,16,2,25,10,16,23,18,21,23,28,6,7,29,11,11,19,20,24,19,26,12,29,29,1,14,17,26,24,7,11,28,22,14,31,12,3,19,16,26,11}, 736185);
        System.out.println(res);
    }

    //左闭右开写法
    public long repairCars(int[] ranks, int cars) {
        int minR = ranks[0];
        for (int r : ranks) {
            minR = Math.min(minR, r);
        }
        //todo: 第一，初始条件是左右边界，inclusive
        long left = 1;
        long right = (long) minR * cars * cars;
        //todo: 第二，while的条件是 l<=r
        while (left <= right) {
            long mid = (left + right) >> 1;
            boolean midRes = check(ranks, cars, mid);
            //todo: 第三，三个分支条件（视情况可能会有多个），其中满足条件直接返回的那个，
            // 在普通的查找中就是相等，在此处就是判断>=target的最小值，也可能判断<=target的最大值
            if (midRes && ((mid - 1 >= 1 && !check(ranks, cars, mid - 1)) || mid == 1)) {
                return mid;
            } else if (!midRes) {
                //todo: 第四，l和r的转移条件都是mid±1
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //todo: 最后大胆返回-1即可
        return -1;
    }

    private boolean check(int[] ranks, int cars, long mid) {
        long s = 0;
        for (int r : ranks) {
            s += Math.sqrt(mid / r);
        }
        return s >= cars;
    }


}
