package indi.simon.learning.leetcode.commonmodel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class TrieNode {
    public Character data;
    public boolean isEndingChar;
    public Map<Character, TrieNode> children = new HashMap<>();

    public TrieNode(Character data) {
        this.data = data;
    }


}

