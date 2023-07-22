package indi.simon.learning.leetcode.gogo20220314;


import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz40 {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,5,2,1,2};
        Quiz40 quiz40 = new Quiz40();
        List<List<Integer>> res = quiz40.combinationSum2Again(candidates, 5);
        System.out.println(res);
    }

    private List<List<Integer>> resAgain;

    public List<List<Integer>> combinationSum2Again(int[] candidates, int target) {
        Arrays.sort(candidates);
        resAgain = new ArrayList<>();
        combinationSum2AgainInternal(0, 0, candidates, target, new ArrayList<>());
        return resAgain;
    }

    private void combinationSum2AgainInternal(int sumSoFar, int index, int[] candidates, int target, List<Integer> combination) {
        if (sumSoFar > target) {
            return;
        }
        if (sumSoFar == target) {
            resAgain.add(combination);
            return;
        }
        if (index >= candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sumSoFar + candidates[i] <= target) {
                List<Integer> newArr = new ArrayList<>(combination);
                newArr.add(candidates[i]);
                combinationSum2AgainInternal(sumSoFar + candidates[i], i + 1, candidates, target, newArr);
            }
            while (i + 1 < candidates.length && candidates[i + 1] == candidates[i]) {
                i++;
            }
        }
    }


    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(path, candidates, target, 0, 0);
        return res;
    }

    /**
     * 在已有path的基础上，对candidates，从currentIndex开始往后遍历，考虑将所遍历的元素加上然后往下递归
     * 因此每种元素都在现有的path基础上存在被加上并被递归（遍历到他），以及不被加上往下递归（跳过他的之后的历次循环）两种情况，因此能覆盖现有path下的所有情形
     *
     * @param path
     * @param candidates
     * @param target
     * @param sum
     * @param currentIndex
     */
    private void backtrack(List<Integer> path, int[] candidates, int target, int sum, int currentIndex) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        //从currentIndex开始循环，每一次循环都是将第i个元素纳入考虑，往下递归，跳过去之后的循环就是不考虑该元素往下递归的情况
        for (int i = currentIndex; i < candidates.length; i++) {
            if (i > currentIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //将currentIndex位置的数纳入考虑，往下递归
            int rs = candidates[i] + sum;
            if (rs <= target) {
                path.add(candidates[i]);
                backtrack(path, candidates, target, rs, i + 1);
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }

    //todo: 这里妙就妙在，先排序，排序完之后，从小到大，每个数总是一连出现好几个，那只需要考虑在连续好几个相同数字的第一个往下递归回溯，后面几个不用递归直接跳过，就能避免重复的情况，
    // 例如：1,1,1,3,3,5,5,5   只需要在下标0，3，5几个地方递归即可，其余地方无须递归。这是因为连续好几个相同数字的第一个往下递归的时候，已经覆盖了该数字出现的所有情况，一个1，两个1
    // 三个1，都在本次覆盖到，所以后续的递归无须再考虑1了。

}
