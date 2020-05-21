package indi.simon.learning.leetcode.gogo2020may.week18to24;

public class P402 {

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        String res = removeKdigits(num, k);
        System.out.println(res);
    }

    private static String min = null;

    private static String removeKdigits(String num, int k) {
        revokeInternal(num, 0, k);
        return removeZeroPrefix(min);
    }

    private static void revokeInternal(String num, int index, int revokeCount) {
        if (num.equals("")) {
            min = "0";
            return;
        }
        if (revokeCount == 0) {
            if (lessThan(num, min)) {
                min = num;
            }
            return;
        }
        char[] charArr = num.toCharArray();
        String minForNow = null;
        for (int i = index; i < num.length(); i++) {
            char[] revokedArr = new char[charArr.length - 1];
            System.arraycopy(charArr, 0, revokedArr, 0, i);
            System.arraycopy(charArr, i + 1, revokedArr, i, revokedArr.length - i);
            String str = new String(revokedArr);
            if (minForNow == null) {
                minForNow = str;
                revokeInternal(str, i, revokeCount - 1);
            }
            if (lessThan(str, minForNow)) {
                revokeInternal(str, i, revokeCount - 1);
            }
        }
    }

    private static boolean lessThan(String challenger, String origin) {
        if (origin == null) {
            return true;
        }
        char[] oriArr = origin.toCharArray();
        char[] chaArr = challenger.toCharArray();
        for (int i = 0; i < origin.length(); i++) {
            if (oriArr[i] > chaArr[i]) {
                return true;
            } else if (oriArr[i] < chaArr[i]) {
                return false;
            }
        }
        return false;
    }

    private static String removeZeroPrefix(String min) {
        if (!min.startsWith("0")) {
            return min;
        }
        char[] arr = min.toCharArray();
        int i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] != '0') {
                break;
            }
        }
        if (i == arr.length) {
            return "0";
        }
        return min.substring(i);
    }
}
