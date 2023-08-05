package indi.simon.learning.leetcode.gogo20230410;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz313_needReview {

    public static void main(String[] args) {
        Quiz313_needReview quiz313NeedReview = new Quiz313_needReview();
        int res = quiz313NeedReview.nthSuperUglyNumberDpAgain(800, new int[]{37, 43, 59, 61, 67, 71, 79, 83, 89, 97, 101, 103, 113, 127, 131, 157, 163, 167, 173, 179, 191, 193, 197, 199, 211, 229, 233, 239, 251, 257});
        System.out.println(res);
    }

    public int nthSuperUglyNumberDpAgain(int n, int[] primes) {
        //将primes排序确保从小到大
        Arrays.sort(primes);
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            //遍历i之前的每一个超级丑数dp[j]，从dp[j]转移而来
            for (int j = i - 1; j >= 1; j--) {
                for (int prime : primes) {
                    long m = dp[j] * prime;
                    if (m > dp[i - 1]) {
                        dp[i] = Math.min(m, dp[i]);
                    }
                }
            }
        }

        return (int) dp[n];
    }


    //todo: 宫酱的优先级队列
    public int nthSuperUglyNumberGongshui(int n, int[] primes) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        while (n-- > 0) {
            int x = q.poll();
            if (n == 0) {
                return x;
            }
            for (int k : primes) {
                if (k <= Integer.MAX_VALUE / x) {
                    q.add(k * x);
                }
                if (x % k == 0) {
                    break;
                }
            }
        }
        return -1; // never
    }

    //todo: 官方dp
    public int nthSuperUglyNumberOfficialDp(int n, int[] primes) {
        int[] dp = new int[n + 1];
        int m = primes.length;
        int[] pointers = new int[m];
        int[] nums = new int[m];
        Arrays.fill(nums, 1);
        for (int i = 1; i <= n; i++) {
            int minNum = Arrays.stream(nums).min().getAsInt();
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (nums[j] == minNum) {
                    pointers[j]++;
                    nums[j] = dp[pointers[j]] * primes[j];
                }
            }
        }
        return dp[n];
    }


    //todo: 自己的dp，超时
    public int nthSuperUglyNumber(int n, int[] primes) {

        Set<Integer> primesSet = new HashSet<>();
        for (int prime : primes) {
            primesSet.add(prime);
        }

        List<Integer> primeRes = new ArrayList<>();
        List<Set<Integer>> dp = new ArrayList<>();
        int curr = 1;
        while (primeRes.size() < n) {
            if (curr == 1) {
                primeRes.add(curr);
                dp.add(new HashSet<>());
                curr++;
                continue;
            }
            //用于记录当前数的所有质因数
            Set<Integer> currPrime = new HashSet<>();
            //如果他本身是质数，先把他自己添加进来
            if (isPrime(curr)) {
                currPrime.add(curr);
            }
            //遍历过往所有因数，来添加他们的质因数，状态转移
            for (int i = 1; i <= curr / 2; i++) {
                if (curr % i == 0) {
                    //i本身是curr的因数，那么把i下面的所有质因数统计进来
                    currPrime.addAll(dp.get(i - 1));
                    //如果现在已经统计到i的乘数了，那么还要统计i的乘数的所有质因数
                    if (dp.size() >= curr / i) {
                        currPrime.addAll(dp.get(curr / i - 1));
                    }
                }
            }
            //记录一下curr的所有质因数
            dp.add(currPrime);
            if (primesSet.containsAll(currPrime)) {
                primeRes.add(curr);
            }
            curr++;
        }


        return primeRes.get(n - 1);
    }

    private boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

}
