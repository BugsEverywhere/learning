package indi.simon.learning.leetcode.gogo20221226;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2027 {

    public static void main(String[] args) {
        Quiz2027 quiz2027 = new Quiz2027();


    }

    public int minimumMoves(String s) {

        int res = 0;
        int index = 0;

        while (index < s.length()) {
            if (s.charAt(index) == 'X') {
                index += 3;
                res++;
            } else {
                index++;
            }
        }

        return res;
    }

}
