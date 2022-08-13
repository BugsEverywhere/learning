package indi.simon.learning.leetcode.gogo20220808;

/**
 * @author chenzhuo(zhiyue)
 */
//todo 各种情况要if else....
public class Quiz640 {

    public static void main(String[] args) {
        Quiz640 quiz640 = new Quiz640();
        String res = quiz640.solveEquation("-3x=33+22+11");
        System.out.println(res);
    }

    public String solveEquation(String equation) {
        String[] equationArr = equation.split("=");

        int[] leftRes = simplifySingleSide(equationArr[0]);
        int leftXCount = leftRes[0];
        int leftInt = leftRes[1];

        int[] rightRes = simplifySingleSide(equationArr[1]);
        int rightXCount = rightRes[0];
        int rightInt = rightRes[1];

        int finalXCount = leftXCount - rightXCount;
        int finalNum = rightInt - leftInt;
        if (finalXCount == 0 && finalNum == 0) {
            return "Infinite solutions";
        } else if (finalXCount == 0 && finalNum != 0) {
            return "No solution";
        }

        return "x=" + finalNum / finalXCount;
    }

    private int[] simplifySingleSide(String singleSideExpr) {
        int xCount = 0;
        int num = 0;
        char[] cArr = singleSideExpr.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == '-') {
                //解析单个符号后面的表达式
                int[] exprRes = parseSingleExpr(cArr, i + 1);
                if (exprRes[0] == 1) {
                    //是x表达式
                    xCount -= exprRes[1];
                } else {
                    //是纯数字
                    num -= exprRes[1];
                }
                i = exprRes[2];
            } else {
                if (cArr[i] == '+') {
                    i++;
                }
                //解析单个符号后面的表达式
                int[] exprRes = parseSingleExpr(cArr, i);
                if (exprRes[0] == 1) {
                    //是x表达式
                    xCount += exprRes[1];
                } else {
                    //是纯数字
                    num += exprRes[1];
                }
                i = exprRes[2];
            }
        }
        int[] res = new int[2];
        res[0] = xCount;
        res[1] = num;
        return res;
    }

    private int[] parseSingleExpr(char[] cArr, int index) {
        int[] res = new int[3];

        int num = 0;

        while (index < cArr.length) {
            if (cArr[index] == '-' || cArr[index] == '+') {
                res[1] = num;
                res[2] = index - 1;
                return res;
            }

            if (cArr[index] == 'x') {
                res[0] = 1;
                if (index == 0 || cArr[index - 1] == '+' || cArr[index - 1] == '-') {
                    res[1] = 1;
                } else {
                    res[1] = num;
                }
                res[2] = index;
                return res;
            } else {
                num = num * 10 + cArr[index] - '0';
            }
            index++;
        }

        res[1] = num;
        res[2] = index - 1;
        return res;

    }

}
