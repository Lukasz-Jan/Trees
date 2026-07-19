package com.nature.trees.branches;

import com.nature.trees.utils.RandomUtils;
import com.nature.trees.leaves.Kind;

import java.util.ArrayList;

public class LeafyBranch extends Branch {

    private static final int NO_OF_LEAVES = 400;
    private static final int MIN_LEAVES = 90;
    private static final int MAX_BRANCH_AGE_FOR_LEAVES = 2;

    public LeafyBranch(int level) {
        super(level);
        age = 0;
        int noOfLeaves = MIN_LEAVES + RandomUtils.nextInt(NO_OF_LEAVES);
        createLeaves(noOfLeaves, Kind.DECIDUOUS);

        if (isRoot()) {
            length = 0.3f;
        } else {
            length = 0.4f;
        }
    }

    @Override
    public void removeLeavesForOlderBranches() {
        if (this.age > MAX_BRANCH_AGE_FOR_LEAVES) {
            leaves = new ArrayList<>(0);
        }
    }

    @Override
    public void increaseLength() {
        if (isRoot()) {
            length = length + 0.5f;
        } else {
            if (age < 3) {
                length = length + 0.3f;
            }
            length = length + 0.5f;
        }
    }

    @Override
    public boolean isRoot() {
        return level == 0;
    }
}
