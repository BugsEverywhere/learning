package indi.simon.leetcode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P17PhoneNumCharCombination {

    public static void main(String[] args) {

        List<String> result = letterCombinations("23");

        System.out.println(result);

    }

    public static List<String> letterCombinations(String digits) {

        Map<Character, Character[]> dictionary = new HashMap<>(8);
        dictionary.put('2', new Character[]{'a', 'b', 'c'});
        dictionary.put('3', new Character[]{'d', 'e', 'f'});
        dictionary.put('4', new Character[]{'g', 'h', 'i'});
        dictionary.put('5', new Character[]{'j', 'k', 'l'});
        dictionary.put('6', new Character[]{'m', 'n', 'o'});
        dictionary.put('7', new Character[]{'p', 'q', 'r', 's'});
        dictionary.put('8', new Character[]{'t', 'u', 'v'});
        dictionary.put('9', new Character[]{'w', 'x', 'y', 'z'});

        char[] charArr = digits.toCharArray();
        TrieNode root = new TrieNode(null);
        List<TrieNode> lastLayerTrienodes = new ArrayList<>();
        lastLayerTrienodes.add(root);
        for (int i = 0; i < charArr.length; i++) {
            Character thisNum = charArr[i];
            Character[] numCharArr = dictionary.get(thisNum);
            List<TrieNode> newLastLayerTrieNodes = new ArrayList<>();
            for (TrieNode singleFatherNode : lastLayerTrienodes) {
                for (Character singleChar : numCharArr) {
                    TrieNode child = new TrieNode(singleChar);
                    if (i == charArr.length - 1) {
                        child.isEndingChar = true;
                    }
                    singleFatherNode.children.put(singleChar, child);
                    newLastLayerTrieNodes.add(child);
                }
            }
            lastLayerTrienodes = newLastLayerTrieNodes;
        }
        List<String> result = new ArrayList<>();
        loadStringInternal(result, root, "");
        return result;
    }


    private static void loadStringInternal(List<String> str, TrieNode node, String string) {
        for (TrieNode singleChild : node.children.values()) {
            if (singleChild.isEndingChar) {
                //todo: 注意递归函数里面如果有循环，基本上在循环体里面是不需要使用return语句的
                str.add(string + singleChild.data);
            } else {
                loadStringInternal(str, singleChild, new String(string + singleChild.data));
            }
        }
    }


    public static class TrieNode {
        public Character data;
        public boolean isEndingChar;
        public Map<Character, TrieNode> children = new HashMap<>();

        public TrieNode(Character data) {
            this.data = data;
        }
    }


}

//todo: 这里本人使用了前缀树，空间复杂度会很高，但是都这样了空间复杂度还击败了80%的人。。。
// 注意点是，递归函数里面如果有循环，基本上在循环体里面是不需要使用return语句的，因为一般这种情形是需要
// 对循环的每一个元素都进行递归的，有点类似于回溯