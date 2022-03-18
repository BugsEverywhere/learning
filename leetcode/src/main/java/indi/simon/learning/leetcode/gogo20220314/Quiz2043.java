package indi.simon.learning.leetcode.gogo20220314;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2043 {

    public static void main(String[] args) {

    }

    public Quiz2043(long[] balance) {
        totalMoneyAccount = balance;
    }

    private long[] totalMoneyAccount;

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > totalMoneyAccount.length || account2 > totalMoneyAccount.length) {
            return false;
        }
        if (totalMoneyAccount[account1 - 1] >= money) {
            totalMoneyAccount[account1 - 1] = totalMoneyAccount[account1 - 1] - money;
            totalMoneyAccount[account2 - 1] = totalMoneyAccount[account2 - 1] + money;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(int account, long money) {
        if (account > totalMoneyAccount.length || totalMoneyAccount[account - 1] + money < 0) {
            return false;
        }
        totalMoneyAccount[account - 1] = totalMoneyAccount[account - 1] + money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > totalMoneyAccount.length || totalMoneyAccount[account - 1] < money) {
            return false;
        }
        totalMoneyAccount[account - 1] = totalMoneyAccount[account - 1] - money;
        return true;
    }

}
