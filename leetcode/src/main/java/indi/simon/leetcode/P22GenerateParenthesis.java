package indi.simon.leetcode;

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
        fillBracket(resultSet, 1, "", n);
        return new ArrayList<>(resultSet);
    }

    private static void fillBracket(Set<String> result, int i, String whatWeHaveRightNow, int n) {
        whatWeHaveRightNow = whatWeHaveRightNow + "(";
        if (i >= n) {
            int lackRightCount = 2 * n - whatWeHaveRightNow.length();
            for (int j = 0; j < lackRightCount; j++) {
                whatWeHaveRightNow = whatWeHaveRightNow + ")";
            }
            result.add(whatWeHaveRightNow);
            return;
        }
        fillBracket(result, i + 1, whatWeHaveRightNow, n);
        int lackRightCount = 2 * i - whatWeHaveRightNow.length();
        for (int j = 0; j < lackRightCount; j++) {
            whatWeHaveRightNow = whatWeHaveRightNow + ")";
            fillBracket(result, i + 1, whatWeHaveRightNow, n);
        }

    }

}

//todo: 其实已经有动态规划的思想了
