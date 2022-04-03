package indi.simon.learning.leetcode.gogo20220328;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz77 {

    public static void main(String[] args) {
        Quiz77 quiz77 = new Quiz77();
        List<List<Integer>> res = quiz77.combine(4, 2);
        System.out.println(res);
    }

    private List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        combineInternalV2(n, k, new ArrayList<>(), 1);
        return res;
    }

    private void combineInternal(int n, int k, List<Integer> path, int currentNum) {
        if (path.size() >= k) {
            res.add(path);
            return;
        }

        if (currentNum > n) {
            return;
        }

        //不选本数
        combineInternal(n, k, new ArrayList<>(path), currentNum + 1);

        //选择本数
        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(currentNum);
        combineInternal(n, k, newPath, currentNum + 1);
    }

    /**
     * N个数递归的另一种写法
     *
     * @param n
     * @param k
     * @param path
     */
    private void combineInternalV2(int n, int k, List<Integer> path, int start) {
        if (path.size() >= k) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (start > n) {
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            combineInternalV2(n, k, path, i + 1);
            //todo 此处注意往下递归时要使用i+1而不是start+1
            path.remove(path.size() - 1);
        }
    }

}
