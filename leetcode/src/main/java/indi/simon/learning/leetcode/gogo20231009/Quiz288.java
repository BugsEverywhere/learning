package indi.simon.learning.leetcode.gogo20231009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/10/15.
 */
public class Quiz288 {

    public static void main(String[] args) {

    }

    private Map<String, List<String>> dictionary;

    public Quiz288(String[] dictionary) {
        this.dictionary = new HashMap<>();
        for (String word : dictionary) {
            if (word.length() > 2) {
                String newStr = word.substring(0, 1) + (word.length() - 2) + word.charAt(word.length() - 1);
                List<String> list = this.dictionary.getOrDefault(newStr, new ArrayList<>());
                list.add(word);
                this.dictionary.put(newStr,list);
            } else {
                List<String> list = this.dictionary.getOrDefault(word, new ArrayList<>());
                this.dictionary.put(word, list);
            }
        }
    }

    public boolean isUnique(String word) {
        String newStr = null;
        if(word.length() > 2){
            newStr = word.substring(0, 1) + (word.length() - 2) + word.charAt(word.length() - 1);
        } else {
            newStr = word;
        }

        if(this.dictionary.containsKey(newStr)){
            List<String> origins = dictionary.get(newStr);
            return origins.size()==1&&origins.contains(word);
        } else{
            return true;
        }
    }
}
