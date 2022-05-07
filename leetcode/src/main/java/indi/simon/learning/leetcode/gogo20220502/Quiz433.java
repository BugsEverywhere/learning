package indi.simon.learning.leetcode.gogo20220502;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一次提交忘了还有迂回达成的情况，想简单了，DFS记住，一定是可能有迂回到达终点的情况
public class Quiz433 {

    public static void main(String[] args) {
        Quiz433 quiz433 = new Quiz433();
        String[] bank = new String[]{"AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"};
        int res = quiz433.minMutation("AACCGGTT", "AAACGGTA", bank);
        System.out.println(res);
    }

    private int minStep = Integer.MAX_VALUE;

    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        minMutationInternal(start, end, 0, bankSet);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }


    public void minMutationInternal(String start, String end, int changeTimes, Set<String> bankSet) {
        if (start.equals(end)) {
            minStep = Math.min(minStep, changeTimes);
            return;
        }

        if (bankSet.size() == 0) {
            return;
        }

        char[] startCharArr = start.toCharArray();

        //遍历将start的每一个字符修改为其余三个的情况，命中了bank就往下递归，同时将命中的bankSet中的字符串去掉
        for (int i = 0; i < 8; i++) {
            char originI = startCharArr[i];
            recursive(originI, 'A', startCharArr, i, bankSet, end, changeTimes);
            recursive(originI, 'C', startCharArr, i, bankSet, end, changeTimes);
            recursive(originI, 'G', startCharArr, i, bankSet, end, changeTimes);
            recursive(originI, 'T', startCharArr, i, bankSet, end, changeTimes);
            //还原
            startCharArr[i] = originI;
        }

    }

    private void recursive(char originI, char targetChar, char[] startCharArr, int i, Set<String> bankSet, String end, int changeTimes) {
        if (targetChar != originI) {
            startCharArr[i] = targetChar;
            String newStart = new String(startCharArr);
            if (bankSet.contains(newStart)) {
                Set<String> newBank = new HashSet<>(bankSet);
                newBank.remove(newStart);
                minMutationInternal(newStart, end, changeTimes + 1, newBank);
            }
        }
    }

}
