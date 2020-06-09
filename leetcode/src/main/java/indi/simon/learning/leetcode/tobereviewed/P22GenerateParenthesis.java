package indi.simon.learning.leetcode.tobereviewed;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P22GenerateParenthesis {

    public static void main(String[] args) {

        List<String> result = generateParenthesis(4);

        System.out.println(result);

    }


    public static List<String> generateParenthesis(int n) {
        Set<String> resultSet = new HashSet<>();
        fillBracket(resultSet, 0, "", n);
        return new ArrayList<>(resultSet);
    }

    private static void fillBracket(Set<String> result, int leftBracketCount, String whatWeHaveRightNow, int n) {
        //每一层优先加左括号，保证合法性，即左括号数永远大于等于右括号数
        whatWeHaveRightNow = whatWeHaveRightNow + "(";
        leftBracketCount++;
        //递归终止条件，当左括号数过半了，补全右括号，返回
        if (leftBracketCount >= n) {
            int lackRightCount = 2 * n - whatWeHaveRightNow.length();
            for (int j = 0; j < lackRightCount; j++) {
                whatWeHaveRightNow = whatWeHaveRightNow + ")";
            }
            result.add(whatWeHaveRightNow);
            return;
        }
        //继续加左括号递归
        fillBracket(result, leftBracketCount, whatWeHaveRightNow, n);
        //根据当前缺的右括号数目，循环递归补满右括号的情况
        int lackRightCount = 2 * leftBracketCount - whatWeHaveRightNow.length();
        for (int j = 0; j < lackRightCount; j++) {
            whatWeHaveRightNow = whatWeHaveRightNow + ")";
            fillBracket(result, leftBracketCount, whatWeHaveRightNow, n);
        }
    }

}
