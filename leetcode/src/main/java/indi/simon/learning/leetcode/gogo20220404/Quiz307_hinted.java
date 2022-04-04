package indi.simon.learning.leetcode.gogo20220404;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz307_hinted {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 8, 4, 2, 6, 4};
        Quiz307_hinted quiz307 = new Quiz307_hinted(arr);
        int res1 = quiz307.sumRange(1, 1);
        System.out.println(res1);
        quiz307.update(1,9);
        int res2 = quiz307.sumRange(1, 1);
        System.out.println(res2);
        quiz307.update(2,6);
        int res3 = quiz307.sumRange(1, 1);
        System.out.println(res3);
    }

    private int[] nums;
    private int[] segmentTree;
    private Map<Integer, Integer> mem;

    public Quiz307_hinted(int[] nums) {
        this.nums = nums;
        segmentTree = new int[nums.length * 2 - 1];
        mem = new HashMap<>();
        buildTree(0, nums, 0, nums.length - 1);
    }

    private int buildTree(int currentNodeIndex, int[] nums, int leftIndex, int rightIndex) {
        if (leftIndex > nums.length - 1 || rightIndex > nums.length - 1) {
            return 0;
        }

        if (leftIndex > rightIndex) {
            return 0;
        }

        int sum = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
            sum += nums[i];
        }

        //扩容
        if (currentNodeIndex > segmentTree.length - 1) {
            int[] newTree = new int[segmentTree.length * 2];
            System.arraycopy(segmentTree, 0, newTree, 0, segmentTree.length);
            segmentTree = newTree;
        }

        if (leftIndex == rightIndex) {
            //已经到达叶子节点
            segmentTree[currentNodeIndex] = sum;
            mem.put(leftIndex, currentNodeIndex);
        } else {
            //构建左子节点
            int leftNode = buildTree(2 * currentNodeIndex + 1, nums, leftIndex, (leftIndex + rightIndex) / 2);
            //构建右子节点
            int rightNode = buildTree(2 * currentNodeIndex + 2, nums, ((leftIndex + rightIndex) / 2) + 1, rightIndex);
            segmentTree[currentNodeIndex] = leftNode + rightNode;
        }
        return segmentTree[currentNodeIndex];
    }

    private void updateTree(int treeNodeIndex, int val, boolean isLeaf) {
        if (treeNodeIndex < 0) {
            return;
        }
        if (isLeaf) {
            //是叶子节点
            segmentTree[treeNodeIndex] = val;
        } else {
            //是非叶子节点
            segmentTree[treeNodeIndex] = segmentTree[2 * treeNodeIndex + 1] + segmentTree[2 * treeNodeIndex + 2];
        }
        //递归更新父节点
        if (treeNodeIndex % 2 == 0) {
            //是父节点的右子节点
            updateTree((treeNodeIndex - 2) / 2, 0, false);
        } else {
            //是父节点的左子节点
            updateTree((treeNodeIndex - 1) / 2, 0, false);
        }
    }

    public void update(int index, int val) {
        nums[index] = val;
        updateTree(mem.get(index), val, true);
    }

    public int sumRange(int left, int right) {
        if (left == right) {
            return nums[left];
        }
        return sumRangeTreeInternal(0, nums.length - 1, 0, left, right);
    }

    private int sumRangeTreeInternal(int treeNodeLeftRange, int treeNodeRightRange, int treeNodeIndex, int numsLeftRange, int numsRightRange) {
        if (treeNodeLeftRange == numsLeftRange && treeNodeRightRange == numsRightRange) {
            return segmentTree[treeNodeIndex];
        }

        int mid = (treeNodeLeftRange + treeNodeRightRange) / 2;

        if (numsRightRange <= mid) {
            //只递归左子节点即可
            return sumRangeTreeInternal(treeNodeLeftRange, mid, 2 * treeNodeIndex + 1, numsLeftRange, numsRightRange);
        } else if (numsLeftRange > mid) {
            //只递归右子节点即可
            return sumRangeTreeInternal(mid + 1, treeNodeRightRange, 2 * treeNodeIndex + 2, numsLeftRange, numsRightRange);
        } else {
            //分开递归求和
            return sumRangeTreeInternal(treeNodeLeftRange, mid, 2 * treeNodeIndex + 1, numsLeftRange, mid) + sumRangeTreeInternal(mid + 1, treeNodeRightRange, 2 * treeNodeIndex + 2, mid + 1, numsRightRange);
        }
    }


}
