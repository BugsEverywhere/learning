package indi.simon.learning.leetcode.gogo20220523;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz241 {

    public static void main(String[] args) {
        Quiz241 quiz241 = new Quiz241();
        quiz241.diffWaysToCompute("2-1-1");
    }

    private List<Integer> res;
    private List<String> strList;

    public List<Integer> diffWaysToCompute(String expression) {
        res = new ArrayList<>();
        strList = new ArrayList<>();
        findInternal(expression.toCharArray(), 0, 0, 0, "");
        System.out.println(strList);
        return null;
    }

    private void findInternal(char[] charArr, int index, int leftBracketCount, int rightBracketCount, String str) {
        if (index == charArr.length - 1) {
            str = str + charArr[index];
            //遍历到最后一个数字了，加完剩余的右括号，返回
            while (leftBracketCount - rightBracketCount > 0) {
                str = str + ")";
                leftBracketCount--;
            }
            strList.add(str);
            return;
        }

        //整个字符串能加的左括号个数
        int totalLeftBracketCount = (charArr.length + 1) / 2 - 1;

        //在该数上加左括号，遍历在该数上所有加左括号的可能
        for (int i = 1; i <= totalLeftBracketCount - leftBracketCount; i++) {
            String newStr = str;
            for (int j = 1; j <= i; j++) {
                newStr = newStr + "(";
            }
            newStr = newStr + charArr[index];
            newStr = newStr + charArr[index + 1];
            findInternal(charArr, index + 2, leftBracketCount + i, rightBracketCount, newStr);
        }

        //在该数上加右括号，遍历在该数上所有加右括号的可能
        for (int i = 0; i <= leftBracketCount - rightBracketCount; i++) {
            String newStr = str + charArr[index];
            for (int j = 1; j <= i; j++) {
                newStr = newStr + ")";
            }
            newStr = newStr + charArr[index + 1];
            findInternal(charArr, index + 2, leftBracketCount, rightBracketCount + i, newStr);
        }
    }
}
