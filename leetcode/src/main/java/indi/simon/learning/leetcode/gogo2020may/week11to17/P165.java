package indi.simon.learning.leetcode.gogo2020may.week11to17;

public class P165 {

    public static void main(String[] args) {

        int cal = Integer.parseInt("001");
        System.out.println(cal);
    }


    private static int compareVersion(String version1, String version2) {

        String[] version1StrArr = version1.split(".");
        String[] version2StrArr = version2.split(".");
        int i = 0;
        for (; i < version1StrArr.length && i < version2StrArr.length; i++) {
            int val1 = Integer.parseInt(version1StrArr[i]);
            int val2 = Integer.parseInt(version2StrArr[i]);

            if (val1 > val2) {
                return 1;
            } else if (val1 < val2) {
                return -1;
            }
        }

        if (version1StrArr.length == version2StrArr.length) {
            return 0;
        } else if (version1StrArr.length > version2StrArr.length) {
            for (; i < version1StrArr.length; i++) {
                if (Integer.parseInt(version1StrArr[i]) != 0) {
                    return 1;
                }
            }
            return 0;
        } else {
            for (; i < version2StrArr.length; i++) {
                if (Integer.parseInt(version2StrArr[i]) != 0) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
