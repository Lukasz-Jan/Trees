package com.nature.trees.branches.factory;

import com.nature.trees.utils.RandomUtils;
import com.nature.trees.branches.Branch;
import com.nature.trees.branches.LeafyBranch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeafyBranchFactory extends BranchAbstractFactory {

    private static final Logger logger = LogManager.getLogger(LeafyBranchFactory.class);

    private static final int MAX_SPREADING_BRANCHES = 8;

    protected void createRoot(List<Branch> branches) {
        LeafyBranch newBranch = new LeafyBranch(0);
        branches.add(newBranch);
    }

    @Override
    protected Optional<List<Branch>> createChildBranches(Branch parent, int relevantAge) {
        int size;
        if (parent.isRoot() && parent.isEmpty()) {
            size = 1;
        } else if (parent.isRoot()) {
            return Optional.empty();
        } else {
            size = noOfBranchesByAgeAndLevel(parent.getLevel(), parent.getAge(), relevantAge);
        }

        if (size > 0) {
            int newLevel = parent.getLevel() + 1;
            List<Branch> newBranches = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Branch newBranch = new LeafyBranch(newLevel);
                newBranches.add(newBranch);
            }
            return Optional.of(newBranches);
        } else return Optional.empty();
    }

    protected int noOfBranchesByAgeAndLevel(int level, int parentAge, int treeAge) {

        int levelAgeFactorPercent = calculateLevelFactor(level, treeAge);

        int noOfBranches = 0;

        if (RandomUtils.chanceToSpreadAnyBranchForLeafyBranch(parentAge)) {
            noOfBranches = RandomUtils.noOfBranchesForLeafTree(levelAgeFactorPercent,
                    MAX_SPREADING_BRANCHES, parentAge, treeAge);
        }
        if (noOfBranches == 0 && treeAge < 4) noOfBranches += 1;

        return noOfBranches;
    }

    private int calculateLevelFactor(int level, int treeAge) {
        if (treeAge > 0) {
            float factor = ((float) level / treeAge) * 100;
            return Math.round(factor);
        } else return 0;
    }
}
