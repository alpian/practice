package com.ind.tests;

import com.ind.tests.tree.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.ind.tests.tree.TreeNode.height;
import static com.ind.tests.tree.TreeNode.leaf;
import static com.ind.tests.tree.TreeNode.left;
import static com.ind.tests.tree.TreeNode.node;
import static java.lang.Integer.max;
import static java.lang.Math.abs;

public class HeightBalanced101 {

    public static void main(String[] args) {
        final TreeNode root =
                node("M",
                        node("L",
                                left("A", leaf("J")),
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
        System.out.println(root.describe());
        System.out.println(isHeightBalanced(root));
    }

    public static boolean isHeightBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }
        return
                isHeightBalanced(node.left)
                && isHeightBalanced(node.right)
                && (abs(height(node.left) - height(node.right)) <= 1);
    }
}
