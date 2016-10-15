package com.ind.tests;

import com.ind.tests.tree.TreeNode;

import static com.ind.tests.tree.TreeNode.leaf;
import static com.ind.tests.tree.TreeNode.left;
import static com.ind.tests.tree.TreeNode.node;
import static com.ind.tests.tree.TreeNode.right;
import static com.ind.tests.tree.TreeNode.size;

public class Reconstruct1012 {

    public static void main(String[] args) {
        final TreeNode root =
                node("H", node("B",
                                leaf("F"),
                                left("E", leaf("A"))),
                          right("C",
                                right("D",
                                        left("G", leaf("I")))));
        System.out.println(root.describe());
        System.out.println(reconstruct("FBAEHCDIG", "HBFEACDGI").describe());
    }

    private static TreeNode reconstruct(String inorder, String preorder) {
        if (inorder.length() == 0) {
            return null;
        }

        if (inorder.length() == 1) {
            return leaf(inorder);
        }

        final String root = preorder.substring(0, 1);

        final int inorderRootIndex = inorder.indexOf(root);
        final String leftInorder = inorder.substring(0, inorderRootIndex);
        final String rightInorder = inorder.substring(inorderRootIndex + 1);

        final TreeNode left = reconstruct(leftInorder, preorder.substring(1));
        return node(root,
                left,
                reconstruct(rightInorder, preorder.substring(1 + size(left))));
    }

}
