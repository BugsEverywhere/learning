package indi.simon.learning.leetcode.gogo20230109;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1806 {

    public static void main(String[] args) {
        Quiz1806 quiz1806 = new Quiz1806();
        int res = quiz1806.reinitializePermutation(10);
        System.out.println(res);
    }

    public int reinitializePermutation(int n) {

        int res = 0;
        int target = 1;
        while (true) {
            if (target < n / 2) {
                target = target * 2;
            } else {
                target = (target - (n / 2 - 1)) * 2 - 1;
            }
            res++;
            if (target == 1) {
                break;
            }
        }
        return res;
    }

}
