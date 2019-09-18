package main.java.indi.simon.leetcode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class ThreeNumSumEqualsZero15 {

    public static void main(String[] args) {

        int[] test = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> result = threeSum(test);
        System.out.println(result);

    }

    private static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return new ArrayList<>();
        }
        int i = 0;
        List<List<Integer>> list = new ArrayList<>();
        while (i < nums.length - 2) {
            int z = nums.length - 1;
            if (i > 0 && nums[i - 1] == nums[i]) {
                i++;
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int j = i + 1;
            Integer lastJ = null;
            Integer lastZ = null;
            while (z > j) {
                if (lastJ != null && lastJ.equals(nums[j])) {
                    lastJ = nums[j];
                    j++;
                    continue;
                }
                if (lastZ != null && lastZ.equals(nums[z])) {
                    lastZ = nums[z];
                    z--;
                    continue;
                }
                if (nums[i] + nums[j] + nums[z] == 0) {
                    List<Integer> integerList = new ArrayList<>();
                    integerList.add(nums[i]);
                    integerList.add(nums[j]);
                    integerList.add(nums[z]);
                    list.add(integerList);
                    lastJ = nums[j];
                    lastZ = nums[z];
                    j++;
                    z--;
                    continue;
                }
                if (nums[i] + nums[j] + nums[z] > 0) {
                    lastZ = nums[z];
                    z--;
                }
                if (nums[i] + nums[j] + nums[z] < 0) {
                    lastJ = nums[j];
                    j++;
                }
            }
            i++;
        }
        return list;
    }


    //    private static List<List<Integer>> threeSum(int[] nums) {
//        if (nums.length < 3) {
//            return new ArrayList<>();
//        }
//        Map<Integer, TrieNode> trieTreeMap = new HashMap<>();
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> positiveList = new ArrayList<>();
//        List<Integer> negtiveList = new ArrayList<>();
//        boolean alreadyAddedZero = false;
//        int zeroCount = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                zeroCount++;
//            }
//            if (nums[i] > 0) {
//                positiveList.add(nums[i]);
//            } else if (nums[i] < 0) {
//                negtiveList.add(nums[i]);
//            } else if (!alreadyAddedZero) {
//                positiveList.add(nums[i]);
//                negtiveList.add(nums[i]);
//                alreadyAddedZero = true;
//            }
//        }
//        if (zeroCount >= 3) {
//            Integer[] zeroArr = new Integer[]{0, 0, 0};
//            result.add(Arrays.asList(zeroArr));
//        }
//        for (int i = 0; i < positiveList.size(); i++) {
//            for (int j = i + 1; j < positiveList.size(); j++) {
//                int oneSideResult = positiveList.get(i) + positiveList.get(j);
//                if (!negtiveList.contains(oneSideResult * -1)) {
//                    continue;
//                }
//                if (!yesWeAlreadyHaveThisCombination(positiveList.get(i), positiveList.get(j), oneSideResult * -1, trieTreeMap)) {
//                    List<Integer> list = new ArrayList<>(3);
//                    list.add(positiveList.get(i));
//                    list.add(positiveList.get(j));
//                    list.add(oneSideResult * -1);
//                    result.add(list);
//                    putCombination(positiveList.get(i), positiveList.get(j), oneSideResult * -1, trieTreeMap);
//                }
//            }
//        }
//
//        for (int i = 0; i < negtiveList.size(); i++) {
//            for (int j = i + 1; j < negtiveList.size(); j++) {
//                int oneSideResult = negtiveList.get(i) + negtiveList.get(j);
//                if (!positiveList.contains(oneSideResult * -1)) {
//                    continue;
//                }
//                if (!yesWeAlreadyHaveThisCombination(negtiveList.get(i), negtiveList.get(j), oneSideResult * -1, trieTreeMap)) {
//                    List<Integer> list = new ArrayList<>(3);
//                    list.add(negtiveList.get(i));
//                    list.add(negtiveList.get(j));
//                    list.add(oneSideResult * -1);
//                    result.add(list);
//                    putCombination(negtiveList.get(i), negtiveList.get(j), oneSideResult * -1, trieTreeMap);
//                }
//            }
//        }
//
//        return result;
//    }
//
//    private static boolean yesWeAlreadyHaveThisCombination(int numA, int numB, int numC, Map<Integer, TrieNode> trieNodeMap) {
//        return yesWeAlreadyHaveThisCombinationWithThisKey(numA, numB, numC, trieNodeMap) || yesWeAlreadyHaveThisCombinationWithThisKey(numB, numA, numC, trieNodeMap) || yesWeAlreadyHaveThisCombinationWithThisKey(numC, numB, numA, trieNodeMap);
//    }
//
//    private static boolean yesWeAlreadyHaveThisCombinationWithThisKey(int key, int val1, int val2, Map<Integer, TrieNode> trieNodeMap) {
//        TrieNode keyNode = trieNodeMap.get(key);
//        if (keyNode == null) {
//            return false;
//        }
//        return yesWeHaveThisComWithThisKeyVal1Val2(keyNode, val1, val2) || yesWeHaveThisComWithThisKeyVal1Val2(keyNode, val2, val1);
//    }
//
//    private static boolean yesWeHaveThisComWithThisKeyVal1Val2(TrieNode keyNode, int val1, int val2) {
//        TrieNode val1Node = keyNode.children.get(val1);
//        if (val1Node == null) {
//            return false;
//        }
//        TrieNode val1Val2Node = val1Node.children.get(val2);
//        if (val1Val2Node == null) {
//            return false;
//        }
//        return true;
//    }
//
//
//    private static void putCombination(int numA, int numB, int numC, Map<Integer, TrieNode> trieTreeMap) {
//        putCombinationInSingleKey(numA, numB, numC, trieTreeMap);
//        putCombinationInSingleKey(numB, numA, numC, trieTreeMap);
//        putCombinationInSingleKey(numC, numB, numA, trieTreeMap);
//    }
//
//
//    private static void putCombinationInSingleKey(int key, int val1, int val2, Map<Integer, TrieNode> trieTreeMap) {
//        TrieNode keyNode;
//        if ((keyNode = trieTreeMap.get(key)) == null) {
//            keyNode = new TrieNode(key);
//        }
//        TrieNode key1Node = new TrieNode(val1);
//        TrieNode key2Node = new TrieNode(val2);
//
//        keyNode.children.put(val1, key1Node);
//        key1Node.children.put(val2, key2Node);
//
//        keyNode.children.put(val2, key2Node);
//        key2Node.children.put(val1, key1Node);
//
//        trieTreeMap.put(key, keyNode);
//    }
//
//
//    public static class TrieNode {
//        public int data;
//        public Map<Integer, TrieNode> children = new HashMap<>();
//
//        public TrieNode(int data) {
//            this.data = data;
//        }
//    }

}



