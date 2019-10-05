package indi.simon.leetcode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P39SumCombination {

    public static void main(String[] args) {

        int[] test = new int[]{2,3,5};
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

        for (int i = 0; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                continue;
            } else if (target - candidates[i] == 0) {
                ArrayList<Integer> myList = new ArrayList<>(sofarWhatWeGet);
                myList.add(candidates[i]);
                myList.sort(Comparator.naturalOrder());
                TrieNode.put(myList, root);
            } else if (target - candidates[i] > 0) {
                ArrayList<Integer> myList = new ArrayList<>(sofarWhatWeGet);
                myList.add(candidates[i]);
                combinationSumInternal(myList, candidates, target - candidates[i], root);
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
}


//todo: 思路，target减去candidates每一个数，然后回溯遍历所有可能，使用前缀树作为去重结果集，