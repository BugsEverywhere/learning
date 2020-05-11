package indi.simon.learning.leetcode.may2020.week11to17;

public class P277 {

    private boolean knows(int a, int b){
        return false;
    }

    public int findCelebrity(int n) {
        int celebrity = -1;
        for(int i = 0;i< n;i++){
            int knownCount = 0;
            for(int j = 0;j<n;j++){
                if(i==j){
                    knownCount++;
                    continue;
                }
                if(knows(j,i)&&!knows(i,j)){
                    knownCount++;
                }else{
                    break;
                }
            }
            if(knownCount==n&&celebrity==-1){
                celebrity = i;
            }else if(knownCount==n&&celebrity!=-1){
                return -1;
            }
        }
        return celebrity;
    }
}
