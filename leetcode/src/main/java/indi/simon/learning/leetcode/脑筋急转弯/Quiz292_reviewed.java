package indi.simon.learning.leetcode.脑筋急转弯;

/**
 * Created by Chen Zhuo on 2024/2/6.
 */
public class Quiz292_reviewed {

    public static void main(String[] args) {
        Quiz292_reviewed quiz292Reviewed = new Quiz292_reviewed();
        boolean res = quiz292Reviewed.canWinNim(34);
        System.out.println(res);
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

}
