package indi.simon.learning.复习.纯回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class Quiz131_分割回文串 {

    public static void main(String[] args) {
        Quiz131_分割回文串 quiz131 = new Quiz131_分割回文串();
        List<List<String>> res = quiz131.partition("aab");
        System.out.println(res);
    }

    private List<String> path;
    private List<List<String>> res;

    public List<List<String>> partition(String s) {
        path = new ArrayList<>();
        res = new ArrayList<>();
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int i) {
        if (i >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int k = i; k < s.length(); k++) {
            if (isHuiwen(s, i, k)) {
                String subStr = s.substring(i, k + 1);
                path.add(subStr);
                //todo: 必须从k+1往后递推，而不是i+1
                dfs(s, k + 1);
                path.remove(path.size() - 1);
            }
        }

    }

    private boolean isHuiwen(String s, int i, int j) {

        while(i <= j && s.charAt(i) == s.charAt(j)){
            i++;
            j--;
        }

        if(i <= j){
            return false;
        } else {
            return true;
        }

    }

}
