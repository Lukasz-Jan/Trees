package com.nature.trees.branches.factory;

import com.nature.trees.branches.Branch;
import java.util.List;
import java.util.Optional;

public class BranchFactory {

    private BranchFactory(){}

    public static List<Branch> createBranchesFromTrunk(BranchAbstractFactory factory, int trunkNode) {
        return ((ConiferBranchFactory)factory).createBranchesFromTrunk(trunkNode);
    }

    public static Optional<List<Branch>> createChildBranches(BranchAbstractFactory factory, Branch parent, int age) {
        return factory.createChildBranches(parent, age);
    }

    public static void createRoot(BranchAbstractFactory factory, List<Branch> branches) {
        ((LeafyBranchFactory)factory).createRoot(branches);
    }
}
