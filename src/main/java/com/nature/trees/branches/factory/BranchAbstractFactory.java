package com.nature.trees.branches.factory;

import com.nature.trees.branches.Branch;

import java.util.List;
import java.util.Optional;

public abstract class BranchAbstractFactory {

    protected abstract Optional<List<Branch>> createChildBranches(Branch parentBranch, int relevantAge);
}
