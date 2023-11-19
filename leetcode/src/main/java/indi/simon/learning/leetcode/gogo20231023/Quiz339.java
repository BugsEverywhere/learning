package indi.simon.learning.leetcode.gogo20231023;

import indi.simon.learning.leetcode.gogo20220411.Quiz385;

import java.util.List;

/**
 * Created by Chen Zhuo on 2023/10/29.
 */
public class Quiz339 {

    public static void main(String[] args) {

    }

    public int depthSum(List<Quiz385.NestedInteger> nestedList) {

        int sum = 0;
        int depth = 1;

        for (Quiz385.NestedInteger nestedInteger : nestedList) {
            sum += sumInternal(nestedInteger, depth);
        }

        return sum;
    }

    private int sumInternal(Quiz385.NestedInteger nestedInteger, int depth) {
        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * depth;
        } else {
            int sum = 0;
            List<Quiz385.NestedInteger> list = nestedInteger.getList();
            for (Quiz385.NestedInteger nestedInteger1 : list) {
                sum += sumInternal(nestedInteger1, depth + 1);
            }
            return sum;
        }
    }

}
