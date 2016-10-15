package com.ind.tests;

import com.ind.tests.tree.TreeNode;

import static com.ind.tests.tree.TreeNode.leaf;
import static com.ind.tests.tree.TreeNode.left;
import static com.ind.tests.tree.TreeNode.node;
import static com.ind.tests.tree.TreeNode.right;

public class FindLca104 {

    public static void main(String[] args) {
        final TreeNode root =
                node("M", node("L", left("A", leaf("J")),
                                    node("D", left("F", leaf("I")),
                                    leaf("E"))),
                          node("R", left("B", leaf("G")),
                                    right("C",
                                            leaf("H"))));
        System.out.println(root.describe());
        System.out.println(lca(root.find("F"), root.find("F")));
    }

    public static TreeNode lca(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return null;
        }
        if (a == b) {
            return a;
        }
        if (a.depth() > b.depth()) {
            return lca(a.parent, b);
        }
        return lca(a, b.parent);
    }
}
