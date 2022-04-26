package indi.simon.learning.leetcode.gogo20220418;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz131_hinted {

    public static void main(String[] args) {
        Quiz131_hinted quiz131 = new Quiz131_hinted();
        List<List<String>> res = quiz131.partition("aab");
        System.out.println(res);
    }

    private List<List<String>> res;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        char[] sCharArr = s.toCharArray();
        partitionInternal(sCharArr, 0, new ArrayList<>());
        return res;
    }

    private void partitionInternal(char[] sCharArr, int index, List<String> path) {
        if (index >= sCharArr.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < sCharArr.length; i++) {
            if (sCharArr[i] == sCharArr[index] && isPalindrome(sCharArr, index, i)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = index; j <= i; j++) {
                    stringBuilder.append(sCharArr[j]);
                }
                path.add(stringBuilder.toString());
                partitionInternal(sCharArr, i + 1, path);
                path.remove(path.size() - 1);
            }
        }

    }

    private boolean isPalindrome(char[] sCharArr, int start, int end) {
        while (start <= end) {
            if (sCharArr[start] == sCharArr[end]) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

}
