package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz744 {

    public static void main(String[] args) {
        char[] letters = new char[]{'c', 'f', 'j'};
        Quiz744 quiz744 = new Quiz744();
        char res = quiz744.nextGreatestLetter(letters, 'j');
        System.out.println(res);
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else if (letters[mid] > target && (mid - 1 < 0 || letters[mid - 1] > target)) {
                right = mid - 1;
            } else if (letters[mid] > target && (mid - 1 < 0 || letters[mid - 1] <= target)) {
                return letters[mid];
            }
        }
        return letters[0];
    }
}
