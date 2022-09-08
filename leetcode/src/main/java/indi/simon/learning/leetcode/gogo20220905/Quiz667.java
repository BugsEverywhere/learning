package indi.simon.learning.leetcode.gogo20220905;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz667 {

    public static void main(String[] args) {


    }

    public int[] constructArray(int n, int k) {
        int[] res = new int[n];

        //为true代表到达k时最后一步停在左指针，为false代表停在右指针
        int leftP = 1;
        int rightP = k + 1;
        for (int i = 0; i <= k; i++) {
            if (i % 2 == 0) {
                res[i] = leftP;
                leftP++;
            } else {
                res[i] = rightP;
                rightP--;
            }
        }

        for (int i = k + 1; i < n; i++) {
            res[i] = i + 1;
        }

        return res;
    }

}
