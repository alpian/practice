package com.ind.tests;

import com.ind.tests.graph.Graph;
import com.ind.tests.graph.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransformString197 {
    public static void main(String[] args) {
        final String s = "cat";
        final String t = "dog";
        final Set<String> dictionary = new HashSet<>();
        dictionary.addAll(Arrays.asList("bat", "cot", "dog", "dag", "dot", "cat"));
        shortestSequence(s, t, dictionary);
    }

    private static int shortestSequence(String s, String t, Set<String> dictionary) {
        System.out.println("start: dict: "+ dictionary);
        final Graph graph = new Graph();

        dictionary.forEach(word -> {
            System.out.println("adding " + word);
            final Vertex newVertex = new Vertex(word);
            graph.add(newVertex);
            graph.visit((vertex) -> {
                if (vertex.close(word)) {
                    vertex.edge(newVertex);
                    newVertex.edge(vertex);
                }
            });
        });

        System.out.println("-=================");
        System.out.println(graph.describe());
        System.out.println("-=================");

        graph.findAll("cat").stream().forEach(start -> {
            final HashSet<Vertex> visited = new HashSet<>();
            System.out.println("START: " + start);
            System.out.println(start.seek("dog", visited));
        });

        return -1;
    }
}
