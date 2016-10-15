package com.ind.tests;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class StringPermutations {
    public static void main(String[] args) {
        final Set<String> all  = permutations("abc");
        System.out.println(all);
    }

    private static Set<String> permutations(String s) {
        System.out.println(s);
        if (s.length() == 0) {
            return emptySet();
        }
        if (s.length() == 1) {
            return new HashSet<String>() {{ add(s); }};
        }
        final Set<String> permutations = new HashSet<>();
        for(int i=0; i<s.length(); i++) {
            final String removed = s.substring(i, i+1);
            final Set<String> substrings = permutations(s.substring(0, i) + s.substring(i+1, s.length()));
            permutations.addAll(substrings.stream().map(substring -> removed + substring).collect(toSet()));
        }
        return permutations;
    }
}
