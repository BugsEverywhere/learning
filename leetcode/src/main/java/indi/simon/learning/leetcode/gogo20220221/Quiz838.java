package indi.simon.learning.leetcode.gogo20220221;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz838 {

    public static void main(String[] args) {
        Quiz838 quiz838 = new Quiz838();
        String res = quiz838.pushDominoes("RR.L");
        System.out.println(res);
    }

    public String pushDominoes(String dominoes) {
        char[] dominoesArr = dominoes.toCharArray();
        char lastPushedCard = '-';
        int lastPushedIndex = 0;
        for (int i = 0; i < dominoesArr.length; i++) {
            char singleChar = dominoesArr[i];
            if (singleChar == '.') {
                //平民牌跳过，什么也不做
            } else {
                if (singleChar == 'L') {
                    //本卡牌往左推
                    if (lastPushedCard == '-' || lastPushedCard == 'L') {
                        //之前没有被推过的牌，或者上一个是往左推的牌，全都往左倒
                        for (int j = lastPushedIndex; j <= i; j++) {
                            dominoesArr[j] = 'L';
                        }
                    } else {
                        //上一个是往右推的牌，前一半往右，后一半往左
                        if ((i - lastPushedIndex) % 2 == 0) {
                            //相隔偶数位，有中间位牌
                            int j = lastPushedIndex;
                            for (; j < lastPushedIndex + (i - lastPushedIndex) / 2; j++) {
                                dominoesArr[j] = 'R';
                            }
                            dominoesArr[j] = '.';
                            j++;
                            for (; j <= i; j++) {
                                dominoesArr[j] = 'L';
                            }
                        } else {
                            //相隔奇数位，无中间位牌
                            int j = lastPushedIndex;
                            for (; j <= lastPushedIndex + (i - lastPushedIndex) / 2; j++) {
                                dominoesArr[j] = 'R';
                            }
                            for (; j <= i; j++) {
                                dominoesArr[j] = 'L';
                            }
                        }
                    }
                    lastPushedCard = 'L';
                    lastPushedIndex = i;
                } else {
                    //本卡牌往右推
                    if (lastPushedCard == '-') {
                        //之前没有被推过的牌，中间的不用动

                    } else if (lastPushedCard == 'L') {
                        //上一个是往左推的牌，除了它，中间的不用动

                    } else {
                        //上一个也是往右推的，中间的全部往右
                        for (int j = lastPushedIndex; j <= i - 1; j++) {
                            dominoesArr[j] = 'R';
                        }
                    }
                    lastPushedCard = 'R';
                    lastPushedIndex = i;
                }
            }
        }

        if (lastPushedCard == '-' || lastPushedCard == 'L') {
            //全程都没有被推倒的，或者上一个是往左推的
        } else {
            //上一个往右推，最后的全都往右
            for (int j = lastPushedIndex; j < dominoesArr.length; j++) {
                dominoesArr[j] = 'R';
            }
        }
        return new String(dominoesArr);
    }

}
