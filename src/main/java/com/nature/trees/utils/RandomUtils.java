package com.nature.trees.utils;

import java.util.Random;

public class RandomUtils {

    private RandomUtils() {
    }

    private static final Random rand = new Random();

    public static boolean chanceToSpreadAnyBranchForLeafyBranch(int parentAge) {

        int any;

        if (parentAge < 2) {
            any = rand.nextInt(2) * rand.nextInt(2);
        } else if (parentAge < 3) {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(3);
        } else if (parentAge < 4) {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(5);
        } else if (parentAge < 5) {
            any =
                    rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(6);
        } else if (parentAge < 8) {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2);
        } else if (parentAge < 12) {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2);
        } else {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2);
        }
        return any > 0;
    }

    public static int noOfBranchesForLeafTree(int levelAgeFactorPercent,
                                              int max, int parentAge, int treeAge) {
        int randomNoOfBranches;

        if (levelAgeFactorPercent < 11) {
            randomNoOfBranches = (rand.nextInt(max)) / 2;
        } else if (levelAgeFactorPercent < 22) {
            randomNoOfBranches = (rand.nextInt(max)) / 2;
        } else if (levelAgeFactorPercent < 26) {
            randomNoOfBranches = (rand.nextInt(max));
            if (parentAge > 5) randomNoOfBranches = randomNoOfBranches / 4;
        } else if (levelAgeFactorPercent < 80) {
            randomNoOfBranches = (rand.nextInt(max));
            if (treeAge > 10) {
                randomNoOfBranches = randomNoOfBranches / 5;
            }
        } else if (levelAgeFactorPercent < 90) {
            randomNoOfBranches = (rand.nextInt(max));
            if (treeAge > 10) {
                randomNoOfBranches = randomNoOfBranches / 5;
            }
        } else {
            randomNoOfBranches = (int) (0.7 * rand.nextInt(max));
            if (treeAge > 10) {
                randomNoOfBranches = randomNoOfBranches / 4;
            }
        }
        return randomNoOfBranches;
    }


    public static int nextInt(int arg) {
        return rand.nextInt(arg);
    }

    public static boolean chanceToRiseBranchForConifer(int parentAge) {

        int any;
        if (parentAge < 1) {
            any = 1;
        } else if (parentAge < 2) {
            any = rand.nextInt(2) * rand.nextInt(4);
        } else if (parentAge < 6) {
            any = rand.nextInt(2) * rand.nextInt(3);
        } else if (parentAge < 12) {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(3);
        } else {
            any = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(2);
        }
        return any > 0;
    }

    public static int noOfBranchesForConifer(int levelAgeFactorPercent, int max) {
        int randomNoOfBranches;

        if (levelAgeFactorPercent < 11) {
            randomNoOfBranches = (int) (0.8 * rand.nextInt(max));
        } else if (levelAgeFactorPercent < 25) {
            randomNoOfBranches = (int) (0.7 * rand.nextInt(max));
        } else if (levelAgeFactorPercent < 76) {
            randomNoOfBranches = (int) (0.7 * rand.nextInt(max)) / 3;
        } else if (levelAgeFactorPercent < 95) {
            randomNoOfBranches = (int) (0.8 * rand.nextInt(max));
        } else {
            randomNoOfBranches = rand.nextInt(1, 5);
        }
        return randomNoOfBranches;
    }
}
