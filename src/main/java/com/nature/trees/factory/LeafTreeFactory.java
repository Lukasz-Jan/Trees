package com.nature.trees.factory;

import com.nature.trees.AbstractTree;
import com.nature.trees.TreeType;
import com.nature.trees.LeafyTree;

public class LeafTreeFactory extends AbstractTreeFactory {

    @Override
    protected AbstractTree createTree() {
        return new LeafyTree(TreeType.DECIDUOUS);
    }
}
