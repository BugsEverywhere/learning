package indi.simon.learning.leetcode.gogo2020june;

import java.util.HashMap;
import java.util.Map;

public class PPP {


    public static void main(String[] args) {
        boolean res = checkInclusion("rvwrk", "lznomzggwrvrkxecjaq");
        System.out.println(res);


    }

    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> s1CharMap = new HashMap<>();
        char[] charArr = s1.toCharArray();
        for (char singleChar : charArr) {
            Integer count = s1CharMap.get(singleChar);
            if (count == null) {
                s1CharMap.put(singleChar, 1);
            } else {
                s1CharMap.put(singleChar, count + 1);
            }
        }

        char[] charArrS2 = s2.toCharArray();
        for (int j = 0; j < charArrS2.length; ) {
            char singleChar = charArrS2[j];
            Integer count = s1CharMap.get(singleChar);
            if (count == null) {
                j++;
                continue;
            }

            Map<Character, Integer> s1CharMapCopy = new HashMap<>(s1CharMap);
            if (count == 1) {
                s1CharMapCopy.remove(singleChar);
            } else {
                s1CharMapCopy.put(singleChar, count - 1);
            }

            if (s1.length() == 1) {
                return true;
            }

            for (int i = 1; i < s1.length() && j + i < charArrS2.length; i++) {
                Integer count1 = s1CharMapCopy.get(charArrS2[j + i]);
                if (count1 == null) {
                    break;
                } else {
                    if (count1 == 1) {
                        s1CharMapCopy.remove(charArrS2[j + i]);
                    } else {
                        s1CharMapCopy.put(charArrS2[j + i], count1 - 1);
                    }
                }

                if (s1CharMapCopy.size() == 0) {
                    return true;
                }
            }

            j++;
        }

        return false;

    }

}

//todo 超时了