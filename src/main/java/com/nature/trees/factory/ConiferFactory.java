package com.nature.trees.factory;

import com.nature.trees.TreeType;
import com.nature.trees.AbstractTree;
import com.nature.trees.Conifer;

public class ConiferFactory extends AbstractTreeFactory {
    @Override
    protected AbstractTree createTree() {
        return new Conifer(TreeType.CONIFEROUS);
    }
}
