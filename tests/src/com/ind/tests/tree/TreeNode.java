package com.ind.tests.tree;

import static com.ind.tests.Strings.repeat;
import static java.lang.Integer.max;
import static java.lang.String.format;

public class TreeNode {
    public final String name;
    public TreeNode parent;
    public final TreeNode left;
    public final TreeNode right;

    private TreeNode(String name, TreeNode left, TreeNode right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return name;
    }

    public String describe() {
        return describe(repeat(' ', name.length()));
    }

    public String describe(String prefix) {
        StringBuilder s = new StringBuilder(name);
        final String indent = prefix + repeat(' ', name.length() + 4);
        if (left != null) {
            s.append(" -- ").append(left.describe(indent));
        }
        if (right != null) {
            s.append(format("%n%s -- ", prefix)).append(right.describe(indent));
        }
        return s.toString();
    }

    public TreeNode find(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        final TreeNode foundLeft = findIn(left, name);
        if (foundLeft != null) {
            return foundLeft;
        }
        final TreeNode foundRight = findIn(right, name);
        if (foundRight != null) {
            return foundRight;
        }
        return null;
    }

    public int depth() {
        if (parent == null) {
            return 0;
        }
        return 1 + parent.depth();
    }

    public int size() {
        return 1 +
                (left == null ? 0 : left.size()) +
                (right == null ? 0 : right.size());
    }

    public static int size(TreeNode node) {
        return node == null ? 0 : node.size();
    }

    public static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + max(height(node.left), height(node.right));
    }

    private static TreeNode findIn(TreeNode node, String name) {
        if (node != null) {
            final TreeNode found = node.find(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static TreeNode node(String name, TreeNode left, TreeNode right) {
        return setParent(new TreeNode(name, left, right));
    }

    public static TreeNode leaf(String name) {
        return new TreeNode(name, null, null);
    }

    private static TreeNode setParent(TreeNode node) {
        if (node.left != null) {
            node.left.parent = node;
        }
        if (node.right != null) {
            node.right.parent = node;
        }
        return node;
    }

    public static TreeNode left(String name, TreeNode left) {
        return setParent(new TreeNode(name, left, null));
    }

    public static TreeNode right(String name, TreeNode right) {
        return setParent(new TreeNode(name, null, right));
    }
}
