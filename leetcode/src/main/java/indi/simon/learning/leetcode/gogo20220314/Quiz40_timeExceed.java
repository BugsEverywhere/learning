package indi.simon.learning.leetcode.gogo20220314;


import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz40_timeExceed {

    public static void main(String[] args) {
        int[] candidates = new int[]{3, 1, 3, 5, 1, 1};
        Quiz40_timeExceed quiz40 = new Quiz40_timeExceed();
        List<List<Integer>> res = quiz40.combinationSum2(candidates, 8);
        System.out.println(res);
    }

    List<List<Integer>> res;
    Set<Map<Integer, Integer>> mem;

    Map<String, Boolean> memMem;


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        mem = new HashSet<>();
        memMem = new HashMap<>();
        List<Integer> nums = new ArrayList<>();
        combinationSum2Internal(candidates, 0, target, nums, 0);
        return res;
    }

    private boolean combinationSum2Internal(int[] candidates, int index, int target, List<Integer> numsForNow, int sumForNow) {
        if (memMem.get(index + "_" + sumForNow) != null && !memMem.get(index + "_" + sumForNow)) {
            return false;
        }

        if (sumForNow > target) {
            return false;
        }

        if (sumForNow == target) {
            Map<Integer, Integer> registerTable = new HashMap<>();
            for (Integer num : numsForNow) {
                Integer countOfNum = registerTable.get(num);
                if (countOfNum == null) {
                    registerTable.put(num, 1);
                } else {
                    countOfNum = countOfNum + 1;
                    registerTable.put(num, countOfNum);
                }
            }
            if (!mem.contains(registerTable)) {
                res.add(numsForNow);
                mem.add(registerTable);
            }
            return true;
        }

        if (index >= candidates.length) {
            return false;
        }

        //加上本数
        List<Integer> list1 = new ArrayList<>(numsForNow);
        list1.add(candidates[index]);
        boolean res1 = combinationSum2Internal(candidates, index + 1, target, list1, sumForNow + candidates[index]);

        //不加本数
        List<Integer> list2 = new ArrayList<>(numsForNow);
        boolean res2 = combinationSum2Internal(candidates, index + 1, target, list2, sumForNow);

        boolean res = res1 || res2;

        memMem.put(index + "_" + sumForNow, res);
        return res;
    }
    //todo: 上面是我自己的解法，过了172/175个用例之后，在倒数第三个用例超时。。。。无法改进继续下去


//    private List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        List<Integer> path = new ArrayList<>();
//        backtrack(path, candidates, target, 0, 0);
//        return res;
//    }
//
//    private void backtrack(List<Integer> path, int[] candidates, int target, int sum, int begin) {
//        if (sum == target) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for (int i = begin; i < candidates.length; i++) {
//            if (i > begin && candidates[i] == candidates[i - 1]) {
//                continue;
//            }
//            int rs = candidates[i] + sum;
//            if (rs <= target) {
//                path.add(candidates[i]);
//                backtrack(path, candidates, target, rs, i + 1);
//                path.remove(path.size() - 1);
//            } else {
//                break;
//            }
//        }
//    }

    //todo: 这里妙就妙在，先排序，排序完之后，从小到大，每个数总是一连出现好几个，那只需要考虑在连续好几个相同数字的第一个往下递归回溯，后面几个不用递归直接跳过，就能避免重复的情况，
    // 例如：1,1,1,3,3,5,5,5   只需要在下标0，3，5几个地方递归即可，其余地方无须递归。这是因为连续好几个相同数字的第一个往下递归的时候，已经覆盖了该数字出现的所有情况，一个1，两个1
    // 三个1，都在本次覆盖到，所以后续的递归无须再考虑1了。

}
