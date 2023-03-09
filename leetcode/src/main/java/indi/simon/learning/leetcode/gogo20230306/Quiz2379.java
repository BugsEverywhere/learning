package indi.simon.learning.leetcode.gogo20230306;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2379 {

    public static void main(String[] args) {
        Quiz2379 quiz2379 = new Quiz2379();
        int res = quiz2379.minimumRecolors("WBWBBBW", 2);
        System.out.println(res);
    }

    public int minimumRecolors(String blocks, int k) {
        int i = 0;
        int bCount = 0;
        for (; i < k; i++) {
            if (blocks.charAt(i) == 'B') {
                bCount++;
            }
        }

        int j = 1;

        int res = k - bCount;
        while (i < blocks.length()) {
            if (blocks.charAt(i) == 'B') {
                bCount++;
            }
            if (blocks.charAt(j - 1) == 'B') {
                bCount--;
            }
            res = Math.min(res, k - bCount);
            i++;
            j++;
        }

        return res;
    }

}
