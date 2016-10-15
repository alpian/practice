package com.ind.tests;

public class NearestEntries136 {
    private static final String[] words = {"there", "is", "a", "blue", "pen", "on", "a", "blue", "table", "with", "blue", "dots"};

    public static void main(String[] args) {
        System.out.println(minimumDistance());
    }


    public static Pair<String, Integer> minimumDistance() {
        Pair<String, Integer> minimum = null;
        for (int i=0; i<words.length; i++) {
            String candidate = words[i];
            int distanceTo = distanceTo(words, candidate, i+1);
            if (distanceTo >= 0 && (minimum == null || distanceTo < minimum.b())) {
                minimum = Pair.of(words[i], distanceTo);
            }
        }
        return minimum;
    }

    public static int distanceTo(String[] words, String wordToFind, int offset) {
        // a, 3
        for (int i=offset; i<words.length; i++) {
            if (words[i].equals(wordToFind)) {
                return i - offset;
            }
        }
        return -1;
    }
}
