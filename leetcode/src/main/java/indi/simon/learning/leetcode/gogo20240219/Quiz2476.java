package indi.simon.learning.leetcode.gogo20240219;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz2476 {

    public static void main(String[] args) {

    }


    public List<List<Integer>> closestNodesV3(TreeNode root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<>();
        dfs(root, arr);

        List<List<Integer>> res = new ArrayList<>();
        for (int val : queries) {
            int maxVal = -1, minVal = -1;
            int idx = binarySearch(arr, val);
            if (idx != arr.size()) {
                maxVal = arr.get(idx);
                if (arr.get(idx) == val) {
                    minVal = val;
                    List<Integer> list = new ArrayList<>();
                    list.add(minVal);
                    list.add(maxVal);
                    res.add(list);
                    continue;
                }
            }
            if (idx > 0) {
                minVal = arr.get(idx - 1);
            }
            List<Integer> list2 = new ArrayList<>();
            list2.add(minVal);
            list2.add(maxVal);
            res.add(list2);
        }
        return res;
    }

    public void dfs(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        dfs(root.left, arr);
        arr.add(root.val);
        dfs(root.right, arr);
    }

    public int binarySearch(List<Integer> arr, int target) {
        int low = 0, high = arr.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    //================================================================================
    public List<List<Integer>> closestNodesV2(TreeNode root, List<Integer> queries) {

        List<Integer> arr = new ArrayList<>();

        fillArr(root,arr);

        List<List<Integer>> res = new ArrayList<>();
        for (int query : queries) {
            List<Integer> singleRes = new ArrayList<>();
            int l = 0;
            int r = arr.size() - 1;
            int mid;
            while (l <= r) {
                mid = (l + r) >> 1;
                if (arr.get(mid) == query) {
                    singleRes.add(query);
                    singleRes.add(query);
                    break;
                } else if (arr.get(mid) < query && mid + 1 < arr.size() && arr.get(mid + 1) > query) {
                    singleRes.add(arr.get(mid));
                    singleRes.add(arr.get(mid + 1));
                    break;
                } else if (arr.get(mid) > query && mid - 1 >= 0 && arr.get(mid - 1) < query) {
                    singleRes.add(arr.get(mid - 1));
                    singleRes.add(arr.get(mid));
                    break;
                } else if (arr.get(mid) < query && mid + 1 == arr.size()) {
                    singleRes.add(arr.get(mid));
                    singleRes.add(-1);
                    break;
                } else if (arr.get(mid) > query && mid == 0) {
                    singleRes.add(-1);
                    singleRes.add(arr.get(mid));
                    break;
                } else if(arr.get(mid) < query){
                    l = mid + 1;
                } else if(arr.get(mid) > query){
                    r = mid - 1;
                }
            }
            res.add(singleRes);
        }

        return res;
    }

    private void fillArr(TreeNode node, List<Integer> treeArr) {
        if (node == null) {
            return;
        }

        fillArr(node.left, treeArr);
        treeArr.add(node.val);
        fillArr(node.right, treeArr);
    }

    //======================================================================================
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> res = new ArrayList<>();
        for (Integer query : queries) {
            List<Integer> singleRes = new ArrayList<>();
            find(root, null, null, query, singleRes);
            res.add(singleRes);
        }
        return res;
    }

    private void find(TreeNode node, Integer ceiling, Integer floor, int target, List<Integer> res) {
        if (node == null) {
            res.add(ceiling == null ? -1 : ceiling);
            res.add(floor == null ? -1 : floor);
            return;
        }

        if (node.val == target) {
            res.add(target);
            res.add(target);
            return;
        }

        if (node.val > target && (floor == null || floor > node.val)) {
            floor = node.val;
            find(node.left, ceiling, floor, target, res);
            return;
        }

        if (node.val < target && (ceiling == null || ceiling < node.val)) {
            ceiling = node.val;
            find(node.right, ceiling, floor, target, res);
            return;
        }

    }


}
