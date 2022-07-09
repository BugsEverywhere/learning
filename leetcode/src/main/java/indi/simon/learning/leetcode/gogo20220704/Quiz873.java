package indi.simon.learning.leetcode.gogo20220704;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz873 {

    public static void main(String[] args) {
        Quiz873 quiz873 = new Quiz873();
        int[] arr = new int[]{1,3,7,11,12,14,18};
        int res = quiz873.lenLongestFibSubseq(arr);
        System.out.println(res);
    }

    public int lenLongestFibSubseq(int[] arr) {

        Map<Integer, List<List<Integer>>> arrays = new HashMap<>();
        List<List<Integer>> zeroList = new ArrayList<>();
        zeroList.add(Collections.singletonList(arr[0]));
        arrays.put(arr[0], zeroList);

        List<List<Integer>> oneList = new ArrayList<>();
        oneList.add(Collections.singletonList(arr[1]));
        arrays.put(arr[1], oneList);

        int maxLength = 0;
        for (int i = 2; i < arr.length; i++) {
            List<List<Integer>> iList = new ArrayList<>();
            iList.add(Collections.singletonList(arr[i]));
            arrays.put(arr[i], iList);
            for (int j = 1; j < i; j++) {
                if (j == 1 && arr[i] - arr[j] == arr[0]) {
                    List<Integer> singleList = new ArrayList<>();
                    singleList.add(arr[0]);
                    singleList.add(arr[j]);
                    singleList.add(arr[i]);
                    maxLength = Math.max(maxLength, singleList.size());
                    iList.add(singleList);
                } else {
                    for (List<Integer> singleJList : arrays.get(arr[j])) {
                        if (singleJList.size() >= 3 && arr[i] - arr[j] == singleJList.get(singleJList.size() - 2)) {
                            List<Integer> singleIlist = new ArrayList<>(singleJList);
                            singleIlist.add(arr[i]);
                            maxLength = Math.max(maxLength, singleIlist.size());
                            iList.add(singleIlist);
                        } else if (singleJList.size() == 1 && arrays.containsKey(arr[i] - arr[j]) && arr[j] > arr[i] - arr[j]) {
                            List<Integer> singleIlist = new ArrayList<>();
                            singleIlist.add(arr[i] - arr[j]);
                            singleIlist.add(arr[j]);
                            singleIlist.add(arr[i]);
                            maxLength = Math.max(maxLength, singleIlist.size());
                            iList.add(singleIlist);
                        }
                    }
                }
            }
        }

        return maxLength;
    }
}
