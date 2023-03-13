package indi.simon.learning.leetcode.gogo20230306;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2383 {

    public static void main(String[] args) {
        Quiz2383 quiz2383 = new Quiz2383();
        int res = quiz2383.minNumberOfHours(1, 1, new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 50});
        System.out.println(res);
    }

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {

        int energyNeeded = 0;
        for (int enemyEng : energy) {
            if (initialEnergy <= enemyEng) {
                energyNeeded += enemyEng - initialEnergy + 1;
                initialEnergy = 1;
            } else {
                initialEnergy -= enemyEng;
            }
        }

        int experienceNeeded = 0;
        for (int enemyExp : experience) {
            if (initialExperience <= enemyExp) {
                int addedExperience = enemyExp - initialExperience + 1;
                experienceNeeded += addedExperience;
                initialExperience += addedExperience;
            }
            initialExperience += enemyExp;
        }

        return energyNeeded + experienceNeeded;
    }


}
