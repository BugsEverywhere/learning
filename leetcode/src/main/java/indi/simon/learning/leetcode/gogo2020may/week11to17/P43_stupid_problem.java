package indi.simon.learning.leetcode.gogo2020may.week11to17;

import java.util.ArrayList;
import java.util.List;

public class P43_stupid_problem {

    public static void main(String[] args) {

        String str1 = "12";
        String str2 = "12";
        String res = multiply(str1, str2);
        System.out.println(res);

    }

    private static String multiply(String num1, String num2) {
        char[] charArr1 = num1.toCharArray();
        char[] charArr2 = num2.toCharArray();

        char[] multiArr = charArr1.length > charArr2.length ? charArr1 : charArr2;
        char[] beMultiedArr = charArr1.length <= charArr2.length ? charArr1 : charArr2;

        List<char[]> multiCharList = new ArrayList<>();

        for (int j = beMultiedArr.length - 1; j >= 0; j--) {
            int jinwei = 0;
            char[] thisLevelMultiNum = new char[multiArr.length + 1];
            int i;
            for (i = multiArr.length - 1; i >= 0; i--) {
                int res = digitToNum(multiArr[i]) * digitToNum(beMultiedArr[j]) + jinwei;
                int thisBit = res % 10;
                jinwei = res / 10;
                thisLevelMultiNum[i + 1] = numToDigit(thisBit);
            }
            if (jinwei > 0) {
                thisLevelMultiNum[0] = numToDigit(jinwei);
            }
            multiCharList.add(thisLevelMultiNum);
        }

        char[] resArr = new char[multiArr.length + beMultiedArr.length + 1];
        int jinwei = 0;
        int i = resArr.length - 1;
        for (int j = 0; i >= 0 && j < multiCharList.size(); i--, j++) {
            int arrRes = 0;
            int k = j;
            int bitCount = 1;
            while (k >= 0) {
                char[] arr = multiCharList.get(k);
                arrRes = arrRes + digitToNum(multiCharList.get(k)[arr.length - bitCount]);
                bitCount++;
                k--;
            }


            //todo fuck!!!!!!!!!!!!!!!!!!!!!


            int res = jinwei + arrRes;
            resArr[i] = numToDigit(res % 10);
            if (j == multiCharList.size() - 1) {
                jinwei = digitToNum(multiCharList.get(j)[multiCharList.get(j).length - bitCount]) + res / 10;
            } else {
                jinwei = res / 10;
            }
        }
        if (jinwei > 0) {
            resArr[i] = numToDigit(jinwei);
            i--;
        }
        if (i > -1) {
            char[] resArrNew = new char[resArr.length - (i + 1)];
            System.arraycopy(resArr, i + 1, resArrNew, 0, resArrNew.length);
            return new String(resArrNew);
        }
        return new String(resArr);
    }

    private static int digitToNum(char singleChar) {
        return singleChar - '0';
    }

    private static char numToDigit(int singleNum) {
        return (char) ('0' + singleNum);
    }

}
