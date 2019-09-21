package indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P8AtoiFunc_Stupid_Problem {

    public static void main(String[] args) {
        int result = myAtoi("   +0 123");
        System.out.println(result);
    }


    public static int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }
        char[] charArr = str.toCharArray();
        boolean sign = true;
        //0代表初始状态,sb中未插入任何字符，也没有遇到符号位，遇到的还全是空格，1代表已经开始录入第一个字符，1代表上一个字符是符号位，2代表上一个字符是数字
        int status = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < charArr.length) {
            if (status == 0 && charArr[i] == ' ') {
                i++;
                continue;
            }
            if (status < 2 && (charArr[i] - '0' > 9 || charArr[i] - '0' < 0) && charArr[i] != '-' && charArr[i] != '+') {
                return 0;
            }
            if (status >= 2 && (charArr[i] - '0' > 9 || charArr[i] - '0' < 0)) {
                break;
            }

            if (charArr[i] == '+' || charArr[i] == '-') {
                if (charArr[i] == '-' && status < 1) {
                    sign = false;
                    status = 1;
                    i++;
                    continue;
                } else if (charArr[i] == '+' && status < 1) {
                    sign = true;
                    status = 1;
                    i++;
                    continue;
                } else if (status == 1) {
                    return 0;
                }
            }

            sb.append(charArr[i]);
            status = 2;
            i++;
        }
        Long rawNum;
        if (sb.length() == 0) {
            return 0;
        }
        try {
            if (sign) {
                rawNum = Long.parseLong(sb.toString());
            } else {
                rawNum = -1 * Long.parseLong(sb.toString());
            }
        } catch (Exception e) {
            if (sign) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        if (rawNum > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (rawNum < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return rawNum.intValue();
    }


}

//todo: 没啥好说的，他妈的这种题如果哪家公司面试的时候让做就可以直接走人了，浪费时间
