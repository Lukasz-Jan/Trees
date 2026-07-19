package com.nature.trees.branches.factory;

import com.nature.trees.utils.RandomUtils;
import com.nature.trees.branches.Branch;
import com.nature.trees.branches.ConiferBranch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConiferBranchFactory extends BranchAbstractFactory {
    private static final int MAX_SPREADING_BRANCHES = 10;

    public List<Branch> createBranchesFromTrunk(int trunkNodeAge) {

        int size = noOfBranches(0, 0, trunkNodeAge);
        List<Branch> branches = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Branch trunkSpread = new ConiferBranch(0);
            branches.add(trunkSpread);
        }
        return branches;
    }

    @Override
    protected Optional<List<Branch>> createChildBranches(Branch parentBranch, int relevantAge) {
        int size = noOfBranches(parentBranch.getNextLevel(), parentBranch.getAge(), relevantAge);
        if (size > 0) {
            List<Branch> childBranches = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Branch newBranch = new ConiferBranch(parentBranch.getNextLevel());
                childBranches.add(newBranch);
            }
            return Optional.of(childBranches);
        } else return Optional.empty();
    }

    private int noOfBranches(int nextLevel, int parentAge, int trunkNodeAge) {
        if (RandomUtils.chanceToRiseBranchForConifer(parentAge)) {
            int levelFactor = calculateLevelFactor(nextLevel, trunkNodeAge);
            return RandomUtils.noOfBranchesForConifer(levelFactor, MAX_SPREADING_BRANCHES);
        } else return 0;
    }

    private int calculateLevelFactor(int level, int trunkNodeAge) {
        if (level == 0) {
            return 100;
        } else {
            float factor = ((float) level / trunkNodeAge) * 100;
            return Math.round(factor);
        }
    }
}
