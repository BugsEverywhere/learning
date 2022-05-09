package indi.simon.learning.leetcode.gogo20220509;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 两种回溯递归方式，注意一下挑选
public class Quiz216 {

    public static void main(String[] args) {
        Quiz216 quiz216 = new Quiz216();
        List<List<Integer>> res = quiz216.combinationSum3(3, 9);
        System.out.println(res);
    }

    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        combinationSum3Internal(0, n, k, 0, new ArrayList<>());
        return res;
    }


    private void combinationSum3Internal(int num, int target, int k, int sumForNow, List<Integer> path) {
        if (sumForNow == target && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (num > 9 || path.size() >= k || sumForNow > target) {
            return;
        }
        //遍历下一次添加余下各数的所有情况
        for (int i = num + 1; i <= 9; i++) {
            path.add(i);
            combinationSum3Internal(i, target, k, sumForNow + i, path);
            path.remove(path.size() - 1);
        }
    }



    //以下第二种递归方式：

    public List<List<Integer>> combinationSum3V2(int k, int n) {
        res = new ArrayList<>();
        combinationSum3InternalV2(1, n, k, 0, new ArrayList<>());
        return res;
    }


    private void combinationSum3InternalV2(int num, int target, int k, int sumForNow, List<Integer> path) {
        if (sumForNow == target && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (num > 9 || path.size() >= k || sumForNow > target) {
            return;
        }

        //添加本数
        path.add(num);
        combinationSum3InternalV2(num + 1, target, k, sumForNow + num, path);
        path.remove(path.size() - 1);


        //不添加本数
        combinationSum3InternalV2(num + 1, target, k, sumForNow, path);

    }



}
