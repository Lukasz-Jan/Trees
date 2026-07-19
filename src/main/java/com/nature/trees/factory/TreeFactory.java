package com.nature.trees.factory;

import com.nature.trees.AbstractTree;

public class TreeFactory {

    private TreeFactory() {
    }

    public static AbstractTree createTree(AbstractTreeFactory factory) {
        return factory.createTree();
    }
}
