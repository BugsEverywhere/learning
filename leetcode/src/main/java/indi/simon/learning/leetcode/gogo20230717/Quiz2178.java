package indi.simon.learning.leetcode.gogo20230717;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2178 {

    public static void main(String[] args) {
        Quiz2178 quiz2178 = new Quiz2178();
        List<Long> res = quiz2178.maximumEvenSplitOfficial(28);
        System.out.println(res);
    }

    //todo: 官方贪心，学到了，反正就从2开始往上怼，finalSum递减，当finalSum递减到小于刚刚怼上来的偶数时，说明再往后怼没有意义了，
    // 因为finalSum已经比现在怼上来的偶数还小了，但是比刚怼上来的偶数小的偶数都已经用过了，所以直接把刚怼上来的这个去掉，然后一步到位怼到finalSum即可
    public List<Long> maximumEvenSplitOfficial(long finalSum) {
        List<Long> res = new ArrayList<>();
        if (finalSum % 2 > 0) {
            return res;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            res.add(i);
            finalSum -= i;
        }
        res.set(res.size() - 1, res.get(res.size() - 1) + finalSum);
        return res;
    }

    //我的超时回溯
    private List<Long> res;

    public List<Long> maximumEvenSplit(long finalSum) {
        res = new ArrayList<>();
        if (finalSum % 2 != 0) {
            return res;
        }
        maximumEvenSplitInternal(finalSum, new ArrayList<>(), 1);
        return res;
    }

    private void maximumEvenSplitInternal(long finalSum, List<Long> nums, long baseNum) {
        if (finalSum == 0) {
            if (nums.size() > res.size()) {
                res = nums;
            }
            return;
        }

        while (finalSum - baseNum * 2 >= 0) {
            List<Long> newNums = new ArrayList<>(nums);
            newNums.add(baseNum * 2);
            maximumEvenSplitInternal(finalSum - baseNum * 2, newNums, baseNum + 1);
            baseNum++;
        }
    }

}
