package com.ind.tests;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ClosestStars114 {
    public static void main(String[] args) {
        final int[] distances = {145, 72, 13, 434, 75, 86, 3447, 68, 89, 10};
        final PriorityQueue<Integer> stars = new PriorityQueue<>((l, r) -> r - l);
        System.out.println(closest(stars, distances));
    }

    private static List<Integer> closest(PriorityQueue<Integer> stars, int[] distances) {
        for(int d : distances) {
            stars.add(d);
            if (stars.size() > 4) {
                stars.remove();
            }
        }
        return stars.stream().collect(Collectors.toList());
    }
}
