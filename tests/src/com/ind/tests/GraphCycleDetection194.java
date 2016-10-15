package com.ind.tests;

import com.ind.tests.graph.Graph;
import com.ind.tests.graph.Vertex;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ind.tests.graph.Vertex.vertex;

public class GraphCycleDetection194 {
    public static void main(String[] args) {
        final Graph graph = new Graph();
        final Vertex A = vertex("A");
        final Vertex C = vertex("C");
        A.edge(vertex("B").edge(C))
         .edge(vertex("D").edge(C));
        C.edge(A);
        graph.add(A);
        System.out.println(graph.describe());

        System.out.println(recurse(graph.vertices.stream().findFirst().get()));
    }

    private static boolean recurse(Vertex vertex) {
        System.out.println(vertex);
        if (vertex.color == Color.BLACK) {
            return false;
        }
        if (vertex.color == Color.WHITE) {
            vertex.color = Color.GRAY;
            boolean cycle = vertex.children.stream().map(c -> recurse(c)).anyMatch(b -> b);
            vertex.color = Color.BLACK;
            return cycle;
        }
        if (vertex.color == Color.GRAY) {
            return true;
        }
        return false;
    }

    public static void rubbish(Graph graph) {
        final Map<Vertex, Set<Vertex>> ancestors = new HashMap<>();
        graph.visit(parent -> {
            parent.children.forEach(child -> {
                final Set<Vertex> parents = ancestors.getOrDefault(child, new HashSet<>());
                parents.add(parent);
                ancestors.put(child, parents);
            });
        });
        System.out.println(ancestors);
        for (Map.Entry<Vertex, Set<Vertex>> e : ancestors.entrySet()) {
            final Stack<Vertex> stack = new Stack<>();
            final Vertex v = e.getKey();
            System.out.println("V="+v);
            final Set<Vertex> parents = e.getValue();
            parents.forEach(stack::push);
            while (!stack.isEmpty()) {
                final Set<Vertex> allAncestors = ancestors.getOrDefault(v, new HashSet<>());
                final Vertex ancestor = stack.pop();
                final Set<Vertex> previousGeneration = ancestors.get(ancestor);
                allAncestors.addAll(previousGeneration);
                System.out.println(v + ", ancestor=" + ancestor + ", allAncestors=" + allAncestors + ", previousGeneration=" + previousGeneration + ", ancestors=" + ancestors);
                ancestors.put(v, allAncestors);
                if (!allAncestors.contains(v)) {
                    System.out.println("not contained, v=" + v + ", allAncestors=" + allAncestors);
                    previousGeneration.stream().filter(p -> !allAncestors.contains(p)).forEach(stack::push);
                }
            }
        }
        System.out.println(ancestors);
    }
}
