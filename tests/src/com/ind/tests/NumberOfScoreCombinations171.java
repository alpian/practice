package com.ind.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class NumberOfScoreCombinations171 {
    public static void main(String[] args) {
        System.out.println(combinations(22, asList(11, 2, 5, 3, 7).stream().collect(toSet())));
    }

    public static class Permutation {
        private final Set<Integer> values;

        public Permutation(Set<Integer> values) {
            this.values = values;
        }

        public Permutation(Integer value) {
            final Set<Integer> values = new HashSet<>();
            values.add(value);
            this.values = values;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || !(o == null || getClass() != o.getClass()) && values.equals(((Permutation) o).values);

        }

        @Override
        public int hashCode() {
            return values.hashCode();
        }

        @Override
        public String toString() {
            return "{"  + values + "}";
        }

        public Set<Permutation> all() {
            final Set<Permutation> all = new HashSet<>();
            allRecurse(all);
            return all;
        }

        private void allRecurse(Set<Permutation> all) {
            if (this.values.isEmpty()) {
                return;
            }
            all.add(new Permutation(this.values));
            for (Integer i : values) {
                all.add(new Permutation(i));
                new Permutation(except(i, values)).allRecurse(all);
            }
        }

        private static Set<Integer> except(Integer i, Set<Integer> values) {
            return values.stream().filter(v -> !v.equals(i)).collect(toSet());
        }

        public List<Integer> ordered() {
            return values.stream().sorted((a, b) -> b - a).collect(toList());
        }
    }

    private static int combinations(int score, Set<Integer> points) {
        final Set<Permutation> permutations = new Permutation(points).all();
        System.out.println(permutations);

        int totals = 0;
        for (Permutation p : permutations) {
            System.out.println("---> p=" + p);
            final List<Integer> ordered = p.ordered();
            totals += recurse(score, ordered);
        }
        return totals;
    }

    private static int recurse(int score, List<Integer> values) {
        System.out.println("recurse: " + score + ", values: " + values);
        if (values.isEmpty()) {
            return 0;
        }
        final int points = values.get(0);
        if (values.size() == 1) {
            if (score >= points && ((score % points) == 0)) {
//                return score / values;
                System.out.println("MATCH!");
                return 1;
            }
            return 0;
        }
        int combinations = 0;
        int multiplier = 1;
        while ((multiplier * points) < score) {
            combinations += recurse(score - (multiplier * points), values.subList(1, values.size()));
            multiplier++;
        }
        return combinations;
    }
}
