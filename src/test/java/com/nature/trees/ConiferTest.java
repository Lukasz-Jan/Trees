package com.nature.trees;

import com.nature.trees.factory.ConiferFactory;
import com.nature.trees.factory.TreeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConiferTest {

    private static final Logger logger = LogManager.getLogger(ConiferTest.class);

    @Test
    public void rise_in_steps() {

        TreeGrowing tree = TreeFactory.createTree(new ConiferFactory());

        tree.createRoot();
        tree.addYears(5);
        tree.addYears(5);
        tree.addYears(5);

        assertEquals(15, ((Conifer) tree).getTreeAge());
        assertTrue(((Conifer) tree).getBranches().size() > 100, " number of " +
                "branches greater than ");

        logger.info("age                : " + ((Conifer) tree).getTreeAge());
        logger.info("all branches number: " + ((Conifer) tree).getBranches().size());
    }

    @Test
    public void rise_first_20_year() {

        int yearsToGrow = 20;
        AbstractTree tree = TreeFactory.createTree(new ConiferFactory());
        tree.rise(yearsToGrow);
        assertEquals(yearsToGrow, ((Conifer) tree).getTreeAge());
        logger.info("age                : " + tree.getTreeAge());
        logger.info("all branches number: " + tree.getBranches().size());
    }

    @Test
    void rise_120_years_and_show_branches_on_levels() {

        int yearsToGrow = 120;

        AbstractTree tree = new Conifer(TreeType.CONIFEROUS);
        tree.rise(yearsToGrow);

        assertEquals(yearsToGrow, tree.getTreeAge());
        assertTrue(tree.getBranches().size() > 1000, " number of " +
                "branches greater than ");
    }
}