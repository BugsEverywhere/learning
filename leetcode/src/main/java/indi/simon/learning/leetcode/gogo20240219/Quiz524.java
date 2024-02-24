package indi.simon.learning.leetcode.gogo20240219;

import java.util.*;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz524 {

    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> dictionary = new ArrayList<>();
        dictionary.add("ale");
        dictionary.add("apple");
        dictionary.add("monkey");
        dictionary.add("plea");
        Quiz524 quiz524 = new Quiz524();
        String res = quiz524.findLongestWord(s, dictionary);
        System.out.println(res);
    }

    public String findLongestWord(String s, List<String> dictionary) {

        Map<Character, TreeSet<Integer>> sCharIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            TreeSet<Integer> list = sCharIndexMap.getOrDefault(c, new TreeSet<>());
            list.add(i);
            sCharIndexMap.put(c, list);
        }

        PriorityQueue<String> queue = new PriorityQueue<>();
        for (String word : dictionary) {
            int index = 0;
            Integer indexInS = null;
            while (index < word.length()) {
                char c = word.charAt(index);
                TreeSet<Integer> listInS = sCharIndexMap.get(c);
                if (listInS == null) {
                    break;
                }
                if (indexInS == null) {
                    indexInS = listInS.first();
                    index++;
                    continue;
                }
                indexInS = listInS.higher(indexInS);
                if (indexInS == null) {
                    break;
                }
                index++;
            }
            if (index == word.length()) {
                if (queue.size() == 0) {
                    queue.offer(word);
                } else if (word.length() > queue.peek().length()) {
                    queue.clear();
                    queue.offer(word);
                } else if (word.length() == queue.peek().length()) {
                    queue.offer(word);
                }
            }
        }

        if(queue.size() == 0){
            return "";
        }

        return queue.poll();
    }

}
