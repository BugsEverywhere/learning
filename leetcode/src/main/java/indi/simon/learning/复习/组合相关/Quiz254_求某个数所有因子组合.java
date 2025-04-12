package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2025/4/12.
 */
public class Quiz254_求某个数所有因子组合 {

    public static void main(String[] args) {
        Quiz254_求某个数所有因子组合 quiz254求某个数所有因子组合 = new Quiz254_求某个数所有因子组合();
        List<List<Integer>> res = quiz254求某个数所有因子组合.getFactors(32);
        System.out.println(res);
    }


    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 1) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backTrace(res, path, n);
        return res;
    }

    //todo:
    // 1. n是递减的，且n自己肯定是一个合法的因子（除非path此时为空，那么n就是目标数自己）
    // 2. 在1的基础上，一旦path非空，那么在递归的每一层，都可以一上来就把n放进path，n肯定是path中最大的那个因子，且这就是一个符合要求的因子组合
    // 3. 最重要的，在for循环内，循环上界是i*i<=n，这样可以加快速度，避免遍历到目标数的做法。
    // 4. 因为每一层递归的for循环中都是从最小的因子2开始，为了保证添加的组合不重复，path中添加的因子必须是递增的，那么path中末尾数大于i的都可以直接跳过。
    private void backTrace(List<List<Integer>> res, List<Integer> path, int n) {
        if (1 == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (!path.isEmpty()) {
            List<Integer> collector = new ArrayList<>(path);
            collector.add(n);
            res.add(collector);
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            if (!path.isEmpty() && path.get(path.size() - 1) > i) {
                continue;
            }
            path.add(i);
            backTrace(res, path, n / i);
            path.remove(path.size() - 1);
        }
    }


}
