package indi.simon.learning.leetcode.gogo20230116;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2299 {

    public static void main(String[] args) {

    }

    private String specialCharStr = "!@#$%^&*()-+";

    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }

        Set<Character> specialCharSet = new HashSet<>();
        for (char c : specialCharStr.toCharArray()) {
            specialCharSet.add(c);
        }

        boolean upperCase = false;
        boolean lowerCase = false;
        boolean num = false;
        boolean specialChar = false;

        char[] cArr = password.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            if (i + 1 < cArr.length && cArr[i + 1] == cArr[i]) {
                return false;
            }
            //校验小写字符
            if (cArr[i] >= 'a' && cArr[i] <= 'z') {
                lowerCase = true;
            }
            //校验大写字符
            if (cArr[i] >= 'A' && cArr[i] <= 'Z') {
                upperCase = true;
            }
            //校验特殊字符
            if (specialCharSet.contains(cArr[i])) {
                specialChar = true;
            }
            //校验数字
            if (cArr[i] >= '0' && cArr[i] <= '9') {
                num = true;
            }
        }
        return upperCase && lowerCase && num && specialChar;
    }


}
