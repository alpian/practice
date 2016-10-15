package com.ind.tests;

import com.ind.tests.tree.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.ind.tests.tree.TreeNode.leaf;
import static com.ind.tests.tree.TreeNode.node;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        final TreeNode root =
                node("M",
                        node("L",
                                leaf("A"),
                                node("D",
                                        node("F",
                                                leaf("I"),
                                                null),
                                        leaf("E"))),
                        node("R",
                                node("B",
                                        leaf("G"),
                                        null),
                                node("C",
                                        null,
                                        leaf("H"))));

        Queue<TreeNode> level = new LinkedBlockingQueue<>();
        level.add(root);
        while (!level.isEmpty()) {
            final TreeNode current = level.remove();
            System.out.println(current);
            if (current.left != null ) { level.add(current.left); }
            if (current.right != null) { level.add(current.right); }
        }
    }
}
