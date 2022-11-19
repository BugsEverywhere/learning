package indi.simon.learning.leetcode.gogo20221114;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz792_reviewed {

    public static void main(String[] args) {
        Quiz792_reviewed quiz792Reviewed = new Quiz792_reviewed();
        int res = quiz792Reviewed.numMatchingSubseqOfficialV2("abcde", new String[]{"a","bb","acd","ace"});
        System.out.println(res);
    }

    //todo: 官方二分查找，最底下自己基于他实现了一个改良的
    public int numMatchingSubseqOfficial(String s, String[] words) {
        //思路跟我是一样的，先记录下s中所有字符出现过的所有下标
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        //先认为所有单词都是子串，然后一个个排除
        int res = words.length;
        //然后遍历每个单词
        for (String w : words) {
            if (w.length() > s.length()) {
                --res;
                continue;
            }
            //跟我一样，也有一个标识当前s中下标的指针
            int p = -1;
            //遍历该单词的每一个字符
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                //s中不包括该字符、该字符在s中的最大下标都比当前下标要小，这两种情况都能直接否决该word
                if (pos[c - 'a'].isEmpty() || pos[c - 'a'].get(pos[c - 'a'].size() - 1) <= p) {
                    --res;
                    break;
                }
                //在该字符的下标列表中二分查找，这个二分查找肯定会找到一个数，所以无须判断返回的p是否合法
                p = binarySearch(pos[c - 'a'], p);
            }
        }
        return res;
    }

    /**
     * @param list
     * @param target
     * @return 返回比传进来的target大的第一个数，类似TreeMap的ceiling方法
     */
    public int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                //同样是技巧，right=mid而不是mid-1
                right = mid;
            } else {
                //list.get(mid)==target的情况也需要这样做，这样就保证下一次
                left = mid + 1;
            }
        }
        return list.get(left);
    }


    //todo: 自己的提交，超时
    public int numMatchingSubseq(String s, String[] words) {
        //记录下s中所有字符出现的下标
        Map<Character, Queue<Integer>> characterIndexMap = new HashMap<>();
        char[] cArr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            Queue<Integer> indexQueue = characterIndexMap.computeIfAbsent(cArr[i], k -> new ArrayDeque<>());
            indexQueue.offer(i);
        }

        //遍历每一个word，判断其是否是子串
        int subStrCount = 0;
        for (String word : words) {
            char[] wordCharArr = word.toCharArray();
            //每次处理一个word的时候new一个登记簿，因为会对其中的queue做更改
            Map<Character, Queue<Integer>> newMap = new HashMap<>();
            characterIndexMap.forEach((k, v) -> newMap.put(k, new ArrayDeque<>(v)));
            //记录每次考察word时在s中的当前下标
            int currIndex = -1;
            int j = 0;
            for (; j < wordCharArr.length; j++) {
                char curr = wordCharArr[j];
                Queue<Integer> indexQueue = newMap.get(curr);
                if (indexQueue == null) {
                    //s中压根儿没有这个字符，直接跳出考察下一个word
                    break;
                }
                //看看indexList中是否有大于当前s下标currIndex的下标，如果没有，则不满足条件，跳出
                Integer index = null;
                while (indexQueue.size() > 0) {
                    int thisIndex = indexQueue.poll();
                    if (thisIndex > currIndex) {
                        //找到了
                        index = thisIndex;
                        break;
                    }
                }
                if (index == null) {
                    //没找到这样一个index，该word不是子串
                    break;
                } else {
                    currIndex = index;
                }
            }
            //判断该word的结果
            if (j == wordCharArr.length) {
                //word的每一个字符都遍历完了，是一个子串，subStrCount加1
                subStrCount++;
            }
        }

        return subStrCount;
    }

    //todo: 自己基于官方二分查找实现的一个，利用了TreeSet的排序属性，需要熟记ceiling、floor、lower、higher这几个方法的用途和区别
    public int numMatchingSubseqOfficialV2(String s, String[] words) {
        //思路跟我是一样的，先记录下s中所有字符出现过的所有下标
        TreeSet<Integer>[] pos = new TreeSet[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new TreeSet<>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        //先认为所有单词都是子串，然后一个个排除
        int res = words.length;
        //然后遍历每个单词
        for (String w : words) {
            if (w.length() > s.length()) {
                --res;
                continue;
            }
            //跟我一样，也有一个标识当前s中下标的指针
            Integer p = -1;
            //遍历该单词的每一个字符
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                //s中不包括该字符、该字符在s中的最大下标都比当前下标要小，这两种情况都能直接否决该word
                if (pos[c - 'a'].isEmpty()) {
                    --res;
                    break;
                }
                //找一下比该p大且最小的下标
                p = pos[c - 'a'].higher(p);
                if (p == null) {
                    //没找着，该word不符合
                    --res;
                    break;
                }
            }
        }
        return res;
    }
}
