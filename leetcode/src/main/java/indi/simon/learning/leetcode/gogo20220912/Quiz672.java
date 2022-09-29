package indi.simon.learning.leetcode.gogo20220912;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz672 {

    public static void main(String[] args) {

    }

    private Set<String> reachedStates;

    public int flipLights(int n, int presses) {
        reachedStates = new HashSet<>();
        flipLightsInternal(new ArrayList<>(), presses, n);
        return reachedStates.size();
    }

    private void flipLightsInternal(List<Integer> pressedSwitchIndexes, int presses, int n) {
        if (pressedSwitchIndexes.size() == presses) {
            String thisCombination = computeState(pressedSwitchIndexes, n);
            if (reachedStates.contains(thisCombination)) {
                return;
            }
            reachedStates.add(thisCombination);
            return;
        }
        for (int i = 1; i <= 4; i++) {
            List<Integer> newIndexes = new ArrayList<>(pressedSwitchIndexes);
            newIndexes.add(i);
            flipLightsInternal(newIndexes, presses, n);
        }
    }

    private String computeState(List<Integer> pressedSwitchIndexes, int n) {
        char[] stateArr = new char[n];
        boolean oneCount = false;
        boolean twoCount = false;
        boolean threeCount = false;
        boolean fourCount = false;
        for (Integer flip : pressedSwitchIndexes) {
            if (flip.equals(1)) {
                oneCount = !oneCount;
            } else if (flip.equals(2)) {
                twoCount = !twoCount;
            } else if (flip.equals(3)) {
                threeCount = !threeCount;
            } else {
                fourCount = !fourCount;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                //偶数灯
                if ((i - 1) % 3 == 0) {
                    //偶数灯中的3k+1灯

                } else {
                    if (oneCount && twoCount) {
                        //1号开关需要按下，2号也需要按下


                    } else if (oneCount) {
                        //1号开关需要按下，2号不需要按下


                    } else if (twoCount) {
                        //2号开关需要按下，1号不需要按下


                    } else {
                        //两个开关都不需要按下

                    }
                }
            } else {
                //奇数灯
                if ((i - 1) % 3 == 0) {
                    //奇数灯中的3k+1灯


                } else {

                }
            }

        }

        return new String(stateArr);
    }

}
