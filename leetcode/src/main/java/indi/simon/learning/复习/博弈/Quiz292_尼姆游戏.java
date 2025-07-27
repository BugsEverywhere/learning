package indi.simon.learning.复习.博弈;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2024/2/6.
 */
public class Quiz292_尼姆游戏 {

    public static void main(String[] args) {
        Quiz292_尼姆游戏 quiz292Reviewed = new Quiz292_尼姆游戏();
        boolean res = quiz292Reviewed.canWinNim(34);
        System.out.println(res);
    }

    //取巧解法
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }



    //正常解法
    //===================================================================
    private Map<Integer, Boolean> mem = new HashMap<>();
    public boolean myCanWinNim(int n) {
        if(n <= 0){
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if(mem.containsKey(n)){
            return mem.get(n);
        }
        //拿走1块
        boolean res1 = !myCanWinNim(n - 1);
        //拿走2块
        boolean res2 = !myCanWinNim(n - 2);
        //拿走3块
        boolean res3 = !myCanWinNim(n - 3);
        boolean res = res1 || res2 || res3;
        mem.put(n, res);
        return res;
    }


}
