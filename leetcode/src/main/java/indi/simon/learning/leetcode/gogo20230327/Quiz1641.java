package indi.simon.learning.leetcode.gogo20230327;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1641 {

    public static void main(String[] args) {
        Quiz1641 quiz1641 = new Quiz1641();
        int res = quiz1641.countVowelStrings(33);
        System.out.println(res);
    }

    private int res;

    public int countVowelStrings(int n) {
        res = 0;
        countVowelStringsInternal(5, 0, n, 0);
        return res;
    }

    private void countVowelStringsInternal(int charLimit, int index, int n, int currLength) {
        if (currLength >= n) {
            res++;
            return;
        }

        if (index >= charLimit) {
            return;
        }

        for (int i = index; i < charLimit; i++) {
            countVowelStringsInternal(charLimit, i, n, currLength + 1);
        }
    }

}
