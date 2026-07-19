package com.nature.trees;

import com.nature.trees.branches.Branch;
import com.nature.trees.factory.LeafTreeFactory;
import com.nature.trees.factory.TreeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LeafyTreeTest {

    private static final Logger logger = LogManager.getLogger(LeafyTreeTest.class);
    public static final Random rand = new Random();

    @Test
    void rise_in_steps() {

        TreeGrowing tree = TreeFactory.createTree(new LeafTreeFactory());
        tree.createRoot();
        tree.addYears(5);
        tree.addYears(5);
        tree.addYears(5);
        tree.addYears(5);


        summary((AbstractTree) tree);
        assertTrue(((AbstractTree) tree).allBranchesNumber() > 100, " number of " +
                "branches greater than ");
    }

    @Test
    void rise_first_20_year() {

        AbstractTree tree = TreeFactory.createTree(new LeafTreeFactory());
        tree.rise(20);

        summary(tree);
        assertTrue(tree.allBranchesNumber() > 100, " number of branches greater than ");
    }

    @Test
    void rise_first_40_year() {

        AbstractTree tree = TreeFactory.createTree(new LeafTreeFactory());
        tree.rise(40);

        summary(tree);
        assertTrue(tree.allBranchesNumber() > 100, " number of branches greater than ");
    }

    @Test
    void rise_80_years_and_show_branches_on_levels() {

        AbstractTree tree = TreeFactory.createTree(new LeafTreeFactory());
        tree.rise(80);
        summary(tree);

        assertTrue(tree.allBranchesNumber() > 1000, " number of branches greater than ");
    }

    private void summary(AbstractTree tree) {

        logger.info("tree age      :" + ((AbstractTree) tree).getTreeAge());
        logger.info("no of branches:" + ((AbstractTree) tree).allBranchesNumber());

        logger.debug("----------------LOGS------------------------");
        printLevels(tree.getBranches(), 0);
        logger.debug("treeAge               : " + tree.getTreeAge());
        logger.debug("Number of all branches: " + tree.allBranchesNumber());
    }

    private void printLevels(List<Branch> branches, int level) {

        logger.debug("level: " + level);

        if (Objects.nonNull(branches)) {
            logger.debug("    no of branches: " + branches.size());
        }
        if (Objects.nonNull(branches)) {
            branches.forEach(branch -> {
                logger.debug("           leaves per branch : " + branch.getLeaves().size());
                branch.getLeaves().size();
            });
        }

        level++;
        if (Objects.nonNull(branches)) {
            for (int i = 0; i < branches.size(); i++) {
                Branch branch = branches.get(i);
                if (Objects.nonNull(branch.getBranches())) {
                    printLevels(branch.getBranches(), level);
                }
            }
        }
    }


    void pseudoRandomNumbers() {
        for (int i = 0; i < 20; i++) {
            int k = rand.nextInt(2) * rand.nextInt(2) * rand.nextInt(4);
        }
    }

}