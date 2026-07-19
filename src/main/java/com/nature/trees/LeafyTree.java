package com.nature.trees;

import com.nature.trees.utils.Utils;
import com.nature.trees.branches.Branch;
import com.nature.trees.branches.LeafyBranch;
import com.nature.trees.branches.factory.BranchFactory;
import com.nature.trees.branches.factory.LeafyBranchFactory;

import java.util.*;

public class LeafyTree extends AbstractTree {

    protected List<Branch> branches = new ArrayList<>(3000);
    private Queue<Branch> que;
    private int totalNoOfBranches;

    public LeafyTree(TreeType type) {
        super(type);
        branchAbstractFactory = new LeafyBranchFactory();
    }

    @Override
    public void rise(int growingYears) {
        if (growingYears <= 0)
            throw new IllegalArgumentException("0 years for growing");
        addYears(growingYears);
    }

    @Override
    public void addYears(int growingYears) {

        que = new ArrayDeque<>(10_000);
        if (branches.isEmpty()) {
            createRoot();
        }
        while (growingYears > 0) {
            spreadBranchesForOneYear();
            increaseTreeAge();
            growingYears--;
        }
        que.clear();
    }

    @Override
    public void createRoot() {
        BranchFactory.createRoot(branchAbstractFactory, branches);
    }

    @Override
    protected List<Branch> getBranches() {
        return branches;
    }

    @Override
    protected int allBranchesNumber() {
        return totalNoOfBranches;
    }

    private void spreadBranchesForOneYear() {

        que.add(branches.getFirst());

        while (!que.isEmpty()) {

            LeafyBranch branch = (LeafyBranch) que.poll();
            branch.increaseAge();
            branch.removeLeavesForOlderBranches();
            branch.increaseLength();

            if (Objects.nonNull(branch.getBranches())) {
                que.addAll(branch.getBranches());
            }

            Optional<List<Branch>> childBranches = BranchFactory.createChildBranches(branchAbstractFactory, branch, getTreeAge());

            childBranches.ifPresent(list -> {
                branch.addChildBranches(list);
                totalNoOfBranches = totalNoOfBranches + list.size();
            });
            Utils.checkHeapForLeafyTree(getTreeAge(), totalNoOfBranches);
        }
    }
}


