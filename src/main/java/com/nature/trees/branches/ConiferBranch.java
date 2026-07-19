package com.nature.trees.branches;

import com.nature.trees.utils.RandomUtils;
import com.nature.trees.leaves.Kind;

import java.util.ArrayList;

public class ConiferBranch extends Branch {

    private static final int NO_OF_LEAVES = 4000;
    private static final int MIN_LEAVES = 500;
    private static final int MAX_BRANCH_AGE_FOR_LEAVES = 5;

    public ConiferBranch(int level) {
        super(level);
        age = 0;
        this.length = 0.2f;
        int noOfLeaves = MIN_LEAVES + RandomUtils.nextInt(NO_OF_LEAVES);
        createLeaves(noOfLeaves, Kind.CONIFEROUS);
    }

    @Override
    public void removeLeavesForOlderBranches() {
        if (this.age > MAX_BRANCH_AGE_FOR_LEAVES) {
            leaves = new ArrayList<>(0);
        }
    }

    @Override
    public void increaseLength() {
        if (age < 3) {
            length = length + 0.3f;
        } else {
            length = length + 0.5f;
        }
    }
}

