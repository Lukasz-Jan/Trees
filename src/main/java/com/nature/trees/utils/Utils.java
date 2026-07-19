package com.nature.trees.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.System.exit;

public class Utils {

    private static final Logger logger = LogManager.getLogger(Utils.class);
    private static final long HEAP_MAX_SIZE = Runtime.getRuntime().maxMemory();

    private Utils() {
    }

    public static void checkHeapForLeafyTree(int treeAge, int branches) {
        long heapSize = Runtime.getRuntime().totalMemory();
        long heapMaxSize = Runtime.getRuntime().maxMemory();
        if (heapSize > 0.95 * heapMaxSize) {
            logger.info("==============================");
            logger.info("Out of heap");
            logger.info("tree age      : " + treeAge);
            logger.info("no of branches: " + branches);
            exit(0);
        }
    }

    public static void checkHeapOnConifer(int branches) {
        long heapSize = Runtime.getRuntime().totalMemory();
        double max = 0.95 * HEAP_MAX_SIZE;

        if (heapSize > max) {
            logger.info("==============================");
            logger.info("Out of heap");
            logger.info("           no of node branches when fails: " + branches);
            exit(0);
        }
    }

    public static void showHeap() {
        long heapSize = Runtime.getRuntime().totalMemory();
        long heapMaxSize = Runtime.getRuntime().maxMemory();

            logger.info("heapSize   : " + heapSize);
            logger.info("heapMaxSize: " + heapMaxSize);

        }
}
