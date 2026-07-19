package com.nature.trees;

import com.nature.trees.branches.Branch;
import com.nature.trees.branches.factory.BranchAbstractFactory;

import java.util.List;

public abstract class AbstractTree implements TreeGrowing {

    protected BranchAbstractFactory branchAbstractFactory;
    private final TreeType type;
    private int treeAge;

    protected AbstractTree(TreeType type) {
        this.type = type;
        treeAge = 0;
    }

    protected int getTreeAge() {
        return treeAge;
    }

    protected void increaseTreeAge() {
        treeAge++;
    }

    protected abstract int allBranchesNumber();

    protected abstract List<Branch> getBranches();
}
