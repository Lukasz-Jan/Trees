package com.nature.trees;

import com.nature.trees.branches.Branch;
import com.nature.trees.branches.ConiferNode;
import com.nature.trees.branches.factory.ConiferBranchFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Conifer extends AbstractTree {

    private static final Logger logger = LogManager.getLogger(Conifer.class);

    private final Deque<ConiferNode> trunkLevelsQue = new ArrayDeque<>(100);
    private final Queue<Branch> workerQue = new ArrayDeque<>(10_000);

    public Conifer(TreeType type) {
        super(type);
        branchAbstractFactory = new ConiferBranchFactory();
    }

    @Override
    public void rise(int growingYears) {
        if (growingYears <= 0)
            throw new IllegalArgumentException("0 years for growing");
        addYears(growingYears);
    }

    @Override
    public void addYears(int growingYears) {

        if (trunkLevelsQue.isEmpty()) {
            addTrunk();
        }
        while (growingYears > 0) {
            riseForOneYear();
            increaseTreeAge();
            logger.debug("================================== age: " + getTreeAge());
            growingYears--;
        }
    }

    @Override
    public List<Branch> getBranches() {

        List<Branch> list = new ArrayList<>(1000);
        for (ConiferNode coniferNode : trunkLevelsQue) {
            if (coniferNode.hasBranches()) {
                list.addAll(coniferNode.getBranches());
            }
        }
        return list;
    }

    @Override
    public void createRoot() {
        addTrunk();
    }

    @Override
    protected int allBranchesNumber() {
        int size = 0;
        for (ConiferNode coniferNode : trunkLevelsQue) {
            size += coniferNode.getNodeBranchesSize();
        }
        return size;
    }

    private void riseForOneYear() {
        for (ConiferNode coniferNode : trunkLevelsQue) {

            coniferNode.riseOneYear();
            coniferNode.removeOlderBranches();
            coniferNode.increaseHeight();

            logger.debug("node heigt " + coniferNode.getTrunkHeightLevel() + "  total no of branches on height " + coniferNode.getTrunkHeightLevel() + ": " + coniferNode.getNodeBranchesSize());
            logger.debug("node heigt " + coniferNode.getTrunkHeightLevel() + "  trunkNodeAge: " + coniferNode.getTrunkNodeAge());
        }

        ConiferNode newConiferNode = new ConiferNode(trunkLevelsQue.size(), workerQue, branchAbstractFactory);
        trunkLevelsQue.add(newConiferNode);
        logger.debug("allBranchesNumber: " + allBranchesNumber());
    }

    private void addTrunk() {
        ConiferNode newConiferNode = new ConiferNode(trunkLevelsQue.size(), workerQue, branchAbstractFactory);
        trunkLevelsQue.add(newConiferNode);
    }
}
