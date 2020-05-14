package indi.simon.learning.leetcode.gogo2020may.week11to17;

import java.util.HashSet;
import java.util.Set;

public class P36 {

    public static void main(String[] args) {

    }

    public static boolean isValidSudoku(char[][] board) {

        Set<Character> rowIntSet = new HashSet<>();
        Set<Character> cubic1 = new HashSet<>();
        Set<Character> cubic2 = new HashSet<>();
        Set<Character> cubic3 = new HashSet<>();

        HashSet<Character>[] colArr = new HashSet[9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if(colArr[j] == null){
                    colArr[j] = new HashSet<>();
                }
                if(colArr[j].contains(board[i][j])){
                    return false;
                }
                colArr[j].add(board[i][j]);
                if (rowIntSet.contains(board[i][j])) {
                    return false;
                }
                rowIntSet.add(board[i][j]);
                if (j / 3 == 0) {
                    if (cubic1.contains(board[i][j])) {
                        return false;
                    }
                    cubic1.add(board[i][j]);
                } else if (j / 3 == 1) {
                    if (cubic2.contains(board[i][j])) {
                        return false;
                    }
                    cubic2.add(board[i][j]);
                } else {
                    if (cubic3.contains(board[i][j])) {
                        return false;
                    }
                    cubic3.add(board[i][j]);
                }
            }
            rowIntSet.clear();
            if (i % 3 == 2) {
                cubic1.clear();
                cubic2.clear();
                cubic3.clear();
            }
        }
        return true;
    }


}
