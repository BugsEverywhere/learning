package indi.simon.learning.leetcode.gogo20230424;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1033 {

    public static void main(String[] args) {
        Quiz1033 quiz1033 = new Quiz1033();
        int[] res = quiz1033.numMovesStones(3, 5, 1);
        System.out.println(res);
    }

    public int[] numMovesStones(int a, int b, int c) {

        int min = Math.min(Math.min(a, b), c);
        int max = Math.max(Math.max(a, b), c);

        int[] res = new int[2];

        if (max - min <= 2) {
            return res;
        }

        if (Math.abs(b - a) == 1 || Math.abs(c - a) == 1 || Math.abs(b - c) == 1) {
            res[0] = 1;
        } else if (Math.abs(b - a) == 2 || Math.abs(c - a) == 2 || Math.abs(b - c) == 2) {
            res[0] = 1;
        } else {
            res[0] = 2;
        }

        res[1] = max - min - 1 - 1;

        return res;
    }

}
