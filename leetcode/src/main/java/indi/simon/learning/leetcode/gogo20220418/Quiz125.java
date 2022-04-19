package indi.simon.learning.leetcode.gogo20220418;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz125 {

    public static void main(String[] args) {
        Quiz125 quiz125 = new Quiz125();
        boolean res = quiz125.isPalindrome(".,");
        System.out.println(res);
    }

    public boolean isPalindrome(String s) {
        char[] charArr = s.toCharArray();

        int i = 0;
        int j = charArr.length - 1;

        while (i < j) {
            //找到有效的i
            while (i < s.length() && !(charArr[i] >= 48 && charArr[i] <= 57) && !(charArr[i] >= 65 && charArr[i] <= 90) && !(charArr[i] >= 97 && charArr[i] <= 122)) {
                i++;
            }

            //找到有效的j
            while (j >= 0 && !(charArr[j] >= 48 && charArr[j] <= 57) && !(charArr[j] >= 65 && charArr[j] <= 90) && !(charArr[j] >= 97 && charArr[j] <= 122)) {
                j--;
            }

            if (i >= j) {
                return true;
            }

            //i是数字
            if (charArr[i] >= 48 && charArr[i] <= 57) {
                if (charArr[i] == charArr[j]) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            } else {
                //i是字母
                int iDistance;
                if (charArr[i] >= 97 && charArr[i] <= 122) {
                    //i是小写字母
                    iDistance = charArr[i] - 'a';
                } else {
                    //i是大写字母
                    iDistance = charArr[i] - 'A';
                }

                int jDistance;
                if (charArr[j] >= 97 && charArr[j] <= 122) {
                    //i是小写字母
                    jDistance = charArr[j] - 'a';
                } else {
                    //i是大写字母
                    jDistance = charArr[j] - 'A';
                }

                if (iDistance == jDistance) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }

        return true;
    }


}
