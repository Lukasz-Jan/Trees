package com.nature.trees.branches;

import com.nature.trees.leaves.Kind;
import com.nature.trees.leaves.Leaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Branch {

    protected List<Branch> branches;
    protected List<Leaf> leaves;
    protected int age;
    protected final int level;
    protected float length;


    public Branch(int level) {
        this.level = level;
    }

    public void removeLeavesForOlderBranches() {
    }

    public int getLevel() {
        return level;
    }

    public int getNextLevel() {
        int i = level;
        return ++i;
    }

    public void addChildBranches(List<Branch> branches) {
        if (Objects.isNull(this.branches)) {
            assignBranches(branches);
        } else {
            this.branches.addAll(branches);
        }
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void increaseAge() {
        age++;
    }

    public boolean isRoot() {
        return false;
    }

    public boolean isEmpty() {
        if (Objects.isNull(branches) || branches.isEmpty()) {
            return true;
        } else return false;
    }

    public int getAge() {
        return age;
    }

    public List<Leaf> getLeaves() {
        return leaves;
    }

    protected void increaseLength() {
    }

    protected boolean hasBranches() {
        return Objects.nonNull(branches);
    }

    protected void createLeaves(int noOfLeaves, Kind leafKind) {

        leaves = new ArrayList<>(noOfLeaves);
        for (int i = 0; i < noOfLeaves; i++) {
            leaves.add(new Leaf(leafKind));
        }
    }

    private void assignBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
