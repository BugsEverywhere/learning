package indi.simon.learning.leetcode.gogo20220411;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz806 {

    public static void main(String[] args) {
        Quiz806 quiz806 = new Quiz806();
        int[] width = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int[] res = quiz806.numberOfLines(width, "abcdefghijklmnopqrstuvwxyz");
        System.out.println(res);
    }

    public int[] numberOfLines(int[] widths, String s) {
        char[] charArr = s.toCharArray();
        int row = 0;
        int widthOfThisRowSoFar = 0;
        for (char c : charArr) {
            if (100 - widthOfThisRowSoFar >= widths[c - 'a']) {
                widthOfThisRowSoFar += widths[c - 'a'];
            } else {
                row++;
                widthOfThisRowSoFar = widths[c - 'a'];
            }
        }

        int[] res = new int[2];

        res[0] = widthOfThisRowSoFar > 0 ? row + 1 : row;
        res[1] = widthOfThisRowSoFar;
        return res;
    }
}
