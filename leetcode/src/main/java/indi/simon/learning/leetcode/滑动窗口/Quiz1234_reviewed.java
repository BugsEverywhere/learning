package indi.simon.learning.leetcode.滑动窗口;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1234_reviewed {

    public static void main(String[] args) {
        Quiz1234_reviewed quiz1234NeedReview = new Quiz1234_reviewed();
        int res = quiz1234NeedReview.balancedStringOfficial("QQWE");
        System.out.println(res);
    }

    //todo: 官方滑窗
    public int balancedStringOfficial(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[idx(c)]++;
        }
        int partial = s.length() / 4;
        int res = s.length();
        if (checkIfBalance(cnt, partial)) {
            //如果已经是平衡的，直接返回0
            return 0;
        }
        for (int l = 0, r = 0; l < s.length(); ) {
            while (r < s.length() && !checkIfBalance(cnt, partial)) {
                //考察原字符串去掉右边界所在的字符之后是否是平衡字符串，如果仍不是，则移动右边界，扩大窗口，同时当做原字符串中删掉一个该字符，直到滑到某个滑窗时，
                // 除去滑窗的部分，原字符串平衡了，则记下该滑窗的长度，最终取长度最小的那个窗口的长度返回
                cnt[idx(s.charAt(r))]--;
                r++;
            }
            if (!checkIfBalance(cnt, partial)) {
                //说明右边界已经到头了，且此时去掉该滑窗中的子串，原字符串也仍然不平衡，没必要再更新左边界了，因为即使更新了左边界只会更加不平衡
                break;
            }
            //更新窗口长度最小值
            res = Math.min(res, r - l);
            //移动左边界，并且把左边界的字符还给原字符串
            l++;
            cnt[idx(s.charAt(l))]++;
        }
        return res;
    }

    public int idx(char c) {
        return c - 'A';
    }

    //判断当前是否为平衡字符串
    public boolean checkIfBalance(int[] cnt, int partial) {
        if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial || cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
            return false;
        }
        return true;
    }


    //todo: 首先，每个字母需要多少个，数量是确定的，就是s.length/4，然后看看每个字母现有个数是多少，记为xCount，可正可负，如果正的则是多出来的，需要替换成别的字母
    // 然后遍历每一个子串，考察该子串中的四种字母是否都超过了各自的xCount，如果有，那么该子串就是一个可以执行替换的子串，这些子串里面最短的子串就是答案了。结果超时，艹
    public int balancedString(String s) {
        int rCount = 0;
        int wCount = 0;
        int qCount = 0;
        int eCount = 0;

        for (char c : s.toCharArray()) {
            switch (c) {
                case 'R':
                    rCount++;
                    break;
                case 'W':
                    wCount++;
                    break;
                case 'Q':
                    qCount++;
                    break;
                case 'E':
                    eCount++;
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        rCount = rCount - s.length() / 4;
        wCount = wCount - s.length() / 4;
        qCount = qCount - s.length() / 4;
        eCount = eCount - s.length() / 4;

        if (rCount == 0 && wCount == 0 && qCount == 0 && eCount == 0) {
            return 0;
        }

        int res = s.length();

        for (int i = 0; i < s.length(); i++) {
            int r1 = 0;
            int q1 = 0;
            int w1 = 0;
            int e1 = 0;
            for (int j = i; j < s.length(); j++) {
                switch (s.charAt(j)) {
                    case 'R':
                        r1++;
                        break;
                    case 'W':
                        w1++;
                        break;
                    case 'Q':
                        q1++;
                        break;
                    case 'E':
                        e1++;
                        break;
                    default:
                        throw new RuntimeException();
                }

                if (((rCount > 0 && r1 >= rCount) //R达标
                        || rCount <= 0 //无须考虑R
                ) && ((wCount > 0 && w1 >= wCount) //W达标
                        || wCount <= 0 //无须考虑W
                ) && ((qCount > 0 && q1 >= qCount) //Q达标
                        || qCount <= 0 //无须考虑Q
                ) && ((eCount > 0 && e1 >= eCount) //E达标
                        || eCount <= 0 //无须考虑E
                )) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }

        return res;
    }


}
