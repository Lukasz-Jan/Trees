package com.nature.trees.branches;

import com.nature.trees.utils.Utils;
import com.nature.trees.branches.factory.BranchAbstractFactory;
import com.nature.trees.branches.factory.BranchFactory;

import java.util.*;


public class ConiferNode {

    private static final int MAX_AGE_FOR_BRANCHES = 20;

    private final Queue<Branch> workerQue;
    private List<Branch> branches;
    private final BranchAbstractFactory branchAbstractFactory;
    private final int trunkHeightLevel;
    private int trunkNodeAge;
    private int nodeBranchesSize;
    private float height;

    public ConiferNode(int nextTrunkLevel, Queue<Branch> workerQue, BranchAbstractFactory branchAbstractFactory) {
        this.trunkHeightLevel = nextTrunkLevel;
        this.branchAbstractFactory = branchAbstractFactory;
        trunkNodeAge = 0;
        nodeBranchesSize = 0;
        this.workerQue = workerQue;

        if (isRoot()) {
            height = 0;
        } else {
            height = 0.4f;
        }
    }

    public void riseOneYear() {
        trunkNodeAge++;

        if (trunkHeightLevel == 0) return;
        if (trunkNodeAge < MAX_AGE_FOR_BRANCHES) {
            if (Objects.isNull(branches)) {
                spreadBranchesFromTrunk();
            } else {
                addBranches();
            }
        }
    }

    public void removeOlderBranches() {
        if (trunkNodeAge == MAX_AGE_FOR_BRANCHES && heigtLevelOverGround()) {
            this.branches = new ArrayList<>(0);
            nodeBranchesSize = 0;
        }
    }

    public int getNodeBranchesSize() {
        return nodeBranchesSize;
    }

    public int getTrunkHeightLevel() {
        return trunkHeightLevel;
    }

    public int getTrunkNodeAge() {
        return trunkNodeAge;
    }

    public void increaseHeight() {
        if (heigtLevelOverGround()) {
            height += 0.5f;
        }
    }

    public List<Branch> getBranches() {

        List<Branch> nodesBranches = new ArrayList<>(500);

        if (Objects.nonNull(branches)) {
            fetchBranches(branches, nodesBranches);
            return nodesBranches;
        } else return Collections.emptyList();
    }

    public boolean hasBranches() {
        return Objects.nonNull(branches);
    }


    private void addBranches() {
        workerQue.addAll(branches);

        while (!workerQue.isEmpty()) {

            Branch parentBranch = workerQue.poll();
            parentBranch.increaseAge();
            parentBranch.removeLeavesForOlderBranches();
            parentBranch.increaseLength();

            if (Objects.nonNull(parentBranch.getBranches())) {
                workerQue.addAll(parentBranch.getBranches());
            }

            Optional<List<Branch>> childBranches = createChildBranches(parentBranch);
            childBranches.ifPresent(list -> {
                parentBranch.addChildBranches(list);
                nodeBranchesSize = nodeBranchesSize + list.size();
            });
            Utils.checkHeapOnConifer(nodeBranchesSize);
        }
    }

    private void spreadBranchesFromTrunk() {
        if (trunkNodeAge < MAX_AGE_FOR_BRANCHES) {
            branches = BranchFactory.createBranchesFromTrunk(branchAbstractFactory, trunkNodeAge);
            nodeBranchesSize = branches.size();
        }
    }

    private Optional<List<Branch>> createChildBranches(Branch parentBranch) {

        if (trunkNodeAge < MAX_AGE_FOR_BRANCHES) {
            return BranchFactory.createChildBranches(branchAbstractFactory, parentBranch, trunkNodeAge);
        } else return Optional.empty();
    }


    private boolean heigtLevelOverGround() {
        return trunkHeightLevel != 0;
    }

    private boolean isRoot() {
        return trunkHeightLevel == 0;
    }


    private void fetchBranches(List<Branch> branches, List<Branch> nodesBranches) {
        nodesBranches.addAll(branches);
        for (Branch branch : branches) {
            if (branch.hasBranches()) {
                fetchBranches(branch.getBranches(), nodesBranches);
            }
        }
    }
}
