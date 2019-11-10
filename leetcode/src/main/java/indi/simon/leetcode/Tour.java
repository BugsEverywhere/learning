package indi.simon.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Tour {

    private volatile static Set<Tourist> spotASet = new HashSet<>();
    private volatile static Set<Tourist> spotBSet = new HashSet<>();
    private volatile static Set<Tourist> spotCSet = new HashSet<>();
    private volatile static Set<Tourist> spotDSet = new HashSet<>();

    public static void main(String[] args) {

        Tourist tourist1 = new Tourist("1", '0');
        Tourist tourist2 = new Tourist("2", '0');
        Tourist tourist3 = new Tourist("3", '0');

        Thread tourist1Thread = new Thread(new RunTour(tourist1));
        Thread tourist2Thread = new Thread(new RunTour(tourist2));
        Thread tourist3Thread = new Thread(new RunTour(tourist3));

        tourist1Thread.start();
        tourist2Thread.start();
        tourist3Thread.start();

    }


    private static class Tourist {

        private String name;
        private char lastSpot;

        public Tourist(String name, char lastSpot) {
            this.name = name;
            this.lastSpot = lastSpot;
        }

        public void gotoNextSpot() {
            if (lastSpot == '0') {
                spotASet.add(this);
                lastSpot = 'A';
            } else if (lastSpot == 'A') {
                spotBSet.add(this);
                lastSpot++;
            } else if (lastSpot == 'B') {
                spotCSet.add(this);
                lastSpot++;
            } else if (lastSpot == 'C') {
                spotDSet.add(this);
                lastSpot++;
            }

        }

    }

    private static class RunTour implements Runnable {

        private Tourist tourist;

        public RunTour(Tourist tourist) {
            this.tourist = tourist;
        }

        @Override
        public void run() {
            for (int i = 8; i <= 13; i++) {
                if (i < 11 && tourist.lastSpot < 'B') {
                    tourist.gotoNextSpot();
                } else if (i == 11) {
                    while (spotBSet.size() < 3) {

                    }
                } else {
                    tourist.gotoNextSpot();
                }
            }
        }
    }


}
