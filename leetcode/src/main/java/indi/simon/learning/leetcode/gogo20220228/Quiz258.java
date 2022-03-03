package indi.simon.learning.leetcode.gogo20220228;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz258 {

    public static void main(String[] args) {
        Quiz258 quiz258 = new Quiz258();
        int res = quiz258.addDigits(19);
        System.out.print(res);
    }

    public int addDigits(int num) {
        return addInternal(0, num);
    }

    private int addInternal(int sumSoFar, int num) {
        if (num == 0) {
            if (sumSoFar >= 10) {
                return addInternal(0, sumSoFar);
            } else {
                return sumSoFar;
            }
        }
        int remain = num % 10;
        return addInternal(sumSoFar + remain, num / 10);
    }

}
