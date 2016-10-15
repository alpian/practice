package com.ind.tests.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Graph {
    public final Set<Vertex> vertices = new HashSet<>();

    public Vertex add(Vertex vertex) {
        vertices.add(vertex);
        return vertex;
    }

    public void visit(Consumer<Vertex> vertexConsumer) {
        final HashSet<Vertex> visited = new HashSet<>();
        vertices.forEach(v -> v.visit(vertexConsumer, visited));
    }

    public String describe() {
        final StringBuilder s = new StringBuilder();
        s.append("<roots> --> ").append(vertices).append("\n");
        visit(v -> {
            s.append(v.describe());
            s.append("\n");
        });
        return s.toString();
    }

    public Set<Vertex> findAll(String word) {
        final HashSet<Vertex> matches = new HashSet<>();
        visit(v -> {
            if (v.label.equals(word)) {
                matches.add(v);
            }
        });
        return matches;
    }
}
