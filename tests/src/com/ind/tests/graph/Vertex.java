package com.ind.tests.graph;

import com.ind.tests.Strings;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Vertex {
    public final Set<Vertex> children = new HashSet<>();
    public final String label;
    public Color color = Color.WHITE;

    public Vertex(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return label.equals(vertex.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label + "(" + color + ")";
    }

    public void visit(Consumer<Vertex> vertexConsumer, Set<Vertex> visited) {
        if (visited.contains(this)) {
            return;
        }
        vertexConsumer.accept(this);
        visited.add(this);
        children.forEach(v -> {
            v.visit(vertexConsumer, visited);
        });
    }

    public Vertex edge(Vertex destination) {
        if (destination.equals(this)) {
            return this;
        }
        System.out.println("Made edge from " + label + " to " + destination.label);
        children.add(destination);
        return this;
    }

    public String describe() {
        return "[" + label + "] --> " + children.stream().map(v -> v.label).collect(Collectors.joining(", "));
    }

    public List<Vertex> seek(String sought, Set<Vertex> visited) {
        final List<Vertex> path = new ArrayList<>();
        if (visited.contains(this)) {
            return path;
        }
        System.out.println("Seeking at " + this + "(path=" + path + ")");
        path.add(this);
        if (sought.equals(label)) {
            return path;
        }
        visited.add(this);
        children.forEach(v -> {
            final List<Vertex> found = v.seek(sought, visited);
            if (found.stream().anyMatch(p -> p.label.equals(sought))) {
                if (path.size() == 1 || found.size() < (path.size() - 1)) {
                    path.addAll(found);
                }
            }
        });
        return path;
    }

    public boolean close(String other) {
        return label.length() == other.length() && Strings.differences(label, other) < 2;
    }

    public static Vertex vertex(String label) {
        return new Vertex(label);
    }
}
