package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz58 {

    public static void main(String[] args) {
        Quiz58 quiz58 = new Quiz58();
        int res = quiz58.lengthOfLastWord("luffy is still joyboy");
        System.out.println(res);
    }

    public int lengthOfLastWord(String s) {
        String[] arr = s.split(" +");
        for (int j = arr.length - 1; j >= 0; j--) {
            if (arr[j].length() > 0) {
                return arr[j].length();
            }
        }
        return 0;
    }
}
