package indi.simon.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P3NoDuplicateCharString {

    public static void main(String[] args) {

        lengthOfLongestSubstring("");

    }

    public static int lengthOfLongestSubstring(String s) {
        int i = 0;
        int j = 0;
        int maxCount = 0;
        char[] inputArray = s.toCharArray();
        Set<Character> resultSet = new HashSet<>();
        while(j<inputArray.length) {
            if (!resultSet.contains(inputArray[j])) {
                resultSet.add(inputArray[j]);
                maxCount = Math.max(maxCount,resultSet.size());
                j++;
            } else {
                resultSet.remove(inputArray[i]);
                i++;
            }
        }
        return maxCount;
    }
}


//todo: 万能的双指针法！屡试不爽