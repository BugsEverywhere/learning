package indi.simon.learning.leetcode.gogo2020may.week11to17;


import java.util.ArrayList;
import java.util.List;

public class Quiz43 {

    public static void main(String[] args) {
        Quiz43 quiz43 = new Quiz43();
        String res = quiz43.multiply("123", "456");
        System.out.println(res);
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        List<String> bitMultiplyRes = new ArrayList<>();
        if (num1.length() > num2.length()) {
            char[] num2CharArr = num2.toCharArray();
            for (int i = num2.length() - 1; i >= 0; i--) {
                bitMultiplyRes.add(multiplySingleBitInternal(num1, Character.toString(num2CharArr[i]), num2.length() - 1 - i));
            }
        } else {
            char[] num1CharArr = num1.toCharArray();
            for (int i = num1.length() - 1; i >= 0; i--) {
                bitMultiplyRes.add(multiplySingleBitInternal(num2, Character.toString(num1CharArr[i]), num1.length() - 1 - i));
            }
        }

        String res = "0";
        for (String multiplyRes : bitMultiplyRes) {
            res = plusStrInternal(multiplyRes, res);
        }

        return res;
    }

    private String multiplySingleBitInternal(String longStr, String singleBit, int zeroCount) {
        char[] longNumCharArr = longStr.toCharArray();
        int singleBitInt = Integer.parseInt(singleBit);

        List<String> resStrList = new ArrayList<>();
        int carry = 0;
        for (int j = longNumCharArr.length - 1; j >= 0; j--) {
            int singleBitOfLongNum = longNumCharArr[j] - '0';
            int multipleRes = singleBitInt * singleBitOfLongNum + carry;
            int bitRes = multipleRes % 10;
            carry = multipleRes / 10;
            resStrList.add(Integer.toString(bitRes));
        }

        if (carry > 0) {
            resStrList.add(Integer.toString(carry));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = resStrList.size() - 1; i >= 0; i--) {
            stringBuilder.append(resStrList.get(i));
        }

        for (int i = 1; i <= zeroCount; i++) {
            stringBuilder.append("0");
        }

        return stringBuilder.toString();
    }

    private String plusStrInternal(String num1Str, String num2Str) {
        char[] charArrNum1 = num1Str.toCharArray();
        char[] charArrNum2 = num2Str.toCharArray();

        List<String> bitStrList = new ArrayList<>();
        int carry = 0;
        for (int i = 0; ; i++) {
            if (charArrNum1.length - 1 - i >= 0 && charArrNum2.length - 1 - i >= 0) {
                int num1Bit = charArrNum1[charArrNum1.length - 1 - i] - '0';
                int num2Bit = charArrNum2[charArrNum2.length - 1 - i] - '0';
                int plusRes = num1Bit + num2Bit + carry;
                int bitRes = plusRes % 10;
                carry = plusRes / 10;
                bitStrList.add(Integer.toString(bitRes));
            } else if (charArrNum1.length - 1 - i >= 0) {
                int num1Bit = charArrNum1[charArrNum1.length - 1 - i] - '0';
                int plusRes = num1Bit + carry;
                int bitRes = plusRes % 10;
                carry = plusRes / 10;
                bitStrList.add(Integer.toString(bitRes));
            } else if (charArrNum2.length - 1 - i >= 0) {
                int num2Bit = charArrNum2[charArrNum2.length - 1 - i] - '0';
                int plusRes = num2Bit + carry;
                int bitRes = plusRes % 10;
                carry = plusRes / 10;
                bitStrList.add(Integer.toString(bitRes));
            } else {
                break;
            }
        }

        if (carry > 0) {
            bitStrList.add(Integer.toString(carry));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int j = bitStrList.size() - 1; j >= 0; j--) {
            stringBuilder.append(bitStrList.get(j));
        }

        return stringBuilder.toString();
    }

}
