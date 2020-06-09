package indi.simon.learning.leetcode.tobereviewed;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P39SumCombination {

    public static void main(String[] args) {

        int[] test = new int[]{2, 3, 5};
        List<List<Integer>> result = combinationSum(test, 8);
        System.out.println(result);

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        TrieNode root = new TrieNode(0, new HashMap<>(), false, true);
        combinationSumInternal(new ArrayList<>(), candidates, target, root);
        List<List<Integer>> result = new ArrayList<>();
        TrieNode.getAllIntegerList(result, new ArrayList<>(), root);
        return result;
    }

    private static void combinationSumInternal(ArrayList<Integer> sofarWhatWeGet, int[] candidates, int target, TrieNode root) {

        for (int candidate : candidates) {
            if (target - candidate < 0) {
                continue;
            } else if (target - candidate == 0) {
                ArrayList<Integer> myList = new ArrayList<>(sofarWhatWeGet);
                myList.add(candidate);
                myList.sort(Comparator.naturalOrder());
                TrieNode.put(myList, root);
            } else if (target - candidate > 0) {
                ArrayList<Integer> myList = new ArrayList<>(sofarWhatWeGet);
                myList.add(candidate);
                combinationSumInternal(myList, candidates, target - candidate, root);
            }
        }
    }

    private static class TrieNode {

        public TrieNode(int value, Map<Integer, TrieNode> children, boolean isEnd, boolean isRoot) {
            this.value = value;
            this.children = children;
            this.isEnd = isEnd;
            this.isRoot = isRoot;
        }

        private int value;
        private Map<Integer, TrieNode> children;
        private boolean isEnd;
        private boolean isRoot;

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Map<Integer, TrieNode> getChildren() {
            return children;
        }

        public void setChildren(Map<Integer, TrieNode> children) {
            this.children = children;
        }

        public static void put(ArrayList<Integer> letsSee, TrieNode root) {
            TrieNode currentNode = root;
            for (int i = 0; i < letsSee.size(); i++) {
                if (currentNode.children.get(letsSee.get(i)) == null) {
                    currentNode.children.put(letsSee.get(i), new TrieNode(letsSee.get(i), new HashMap<>(), i == letsSee.size() - 1, false));
                }
                currentNode = currentNode.children.get(letsSee.get(i));
            }
        }

        public static void getAllIntegerList(List<List<Integer>> result, List<Integer> thisList, TrieNode currentNode) {
            if (currentNode.isEnd) {
                thisList.add(currentNode.value);
                result.add(thisList);
                return;
            }
            Iterator<Map.Entry<Integer, TrieNode>> iterator = currentNode.children.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, TrieNode> singleEntry = iterator.next();
                List<Integer> newList = new ArrayList<>(thisList);
                if (!currentNode.isRoot) {
                    newList.add(currentNode.value);
                }
                getAllIntegerList(result, newList, singleEntry.getValue());
            }
        }
    }


//todo: 思路，target减去candidates每一个数，然后回溯遍历所有可能，使用前缀树作为去重结果集，


//============================================================================================================another

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;

        // 排序是为了提前终止搜索
        Arrays.sort(candidates);

        dfs(candidates, len, target, 0, new ArrayDeque<>(), res);
        return res;
    }


    /**
     * @param candidates 数组输入
     * @param len        输入数组的长度，冗余变量
     * @param residue    剩余数值
     * @param begin      本轮搜索的起点下标
     * @param path       从根结点到任意结点的路径
     * @param res        结果集变量
     */
    private void dfs(int[] candidates,
                     int len,
                     int residue,
                     int begin,
                     Deque<Integer> path,
                     List<List<Integer>> res) {
        if (residue == 0) {
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {

            // 在数组有序的前提下，剪枝
            if (residue - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, len, residue - candidates[i], i, path, res);
            path.removeLast();

        }
    }

    //todo 根本不需要前缀树这样复杂的数据结构嘛。回溯递归出来的每一条path，本来就都是独一无二的。。。。


}