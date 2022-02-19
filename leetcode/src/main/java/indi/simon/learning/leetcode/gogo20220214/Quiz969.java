package indi.simon.learning.leetcode.gogo20220214;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz969 {

    public static void main(String[] args) {
        Quiz969 quiz969 = new Quiz969();
        int[] arr = new int[]{1, 2, 3};

        List<Integer> res = quiz969.pancakeSort(arr);
        System.out.println(res);

    }

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int lengthToBeRevert = arr.length;
        for (int i = 0; i < lengthToBeRevert; ) {
            if (lengthToBeRevert <= 1) {
                break;
            }
            //找到目前最大的那个元素
            if (arr[i] == lengthToBeRevert) {
                if (i + 1 == lengthToBeRevert) {
                    //他目前就处于我们需要的位置，无须翻转，跳过去
                    lengthToBeRevert--;
                    i = 0;
                    //todo:第一次提交在这里忘了补一下循环递进条件
                    continue;
                }
                //以他目前的位置翻转一次
                revertAtCertainPosition(arr, i + 1);
                res.add(i + 1);
                //以整个数组长度翻转一次
                revertAtCertainPosition(arr, lengthToBeRevert);
                res.add(lengthToBeRevert);
                //剩下的需要处理的长度减一
                lengthToBeRevert--;
                i = 0;
            } else {
                //不是当前最大的那个元素，继续往下找
                i++;
            }
        }
        return res;
    }

    private void revertAtCertainPosition(int[] arr, int length) {
        for (int i = 0; i < length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[length - i - 1];
            arr[length - i - 1] = temp;
        }
    }

}
