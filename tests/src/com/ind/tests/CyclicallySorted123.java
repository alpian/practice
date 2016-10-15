package com.ind.tests;

import java.util.Arrays;
import java.util.List;

public class CyclicallySorted123 {
    public static void main(String[] args) {
        final List<Integer> cycle = Arrays.asList(378, 478, 550, 631, 103, 203, 220, 234, 279, 368);
        final int lowestIndex = lowestIndex(cycle);
        System.out.println(lowestIndex);
        System.out.println(cycle.get(lowestIndex));
    }

    private static int lowestIndex(List<Integer> cycle) {
        int left = 0;
        int right = cycle.size() - 1;
        while (left < right) {
            int pivot = left + ((right - left) / 2);
            System.out.println("l="+left+", p="+pivot+", r="+right);
            if (cycle.get(left) > cycle.get(pivot)) {
                right = pivot;
            } else {
                left = pivot + 1;
            }
        }
        return left;
    }

    private static boolean isLowest(List<Integer> cycle, int i) {
        int v = cycle.get(i);
        int left, right;
        if (i == 0) {
            left = cycle.get(cycle.size() - 1);
            right = cycle.get(i + 1);
        } else if (i == cycle.size() - 1) {
            left = cycle.get(i - 1);
            right = cycle.get(0);
        } else {
            left = cycle.get(i - 1);
            right = cycle.get(i + 1);
        }
        return left < v && right > v;
    }
}
