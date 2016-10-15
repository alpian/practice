package com.ind.tests;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DutchFlag {
    public static final int SIZE = 20;

    public static void main(String[] args) {
        final int[] as = IntStream.generate(DutchFlag::random).limit(SIZE).toArray();
        print(as);
        final int pivot = as[random()];
        System.out.println("Pivot: " + pivot);
        pivot(as, pivot);
        print(as);
    }

    private static void print(int[] as) {
        System.out.println("[" + IntStream.of(as).mapToObj(String::valueOf).collect(Collectors.joining(",")) + "]");
    }

    private static void pivot(int[] as, int pivot) {
        for (int l=0, r=as.length-1; l<r; l++) {
            if (as[l] >= pivot) {
                while (r > l) {
                    if (as[r] < pivot) {
                        swap(as, l, r);
                        break;
                    }
                    r--;
                }
            }
        }
    }

    private static void swap(int[] as, int l, int r) {
        int temp = as[l];
        as[l] = as[r];
        as[r] = temp;
    }

    private static int random() {
        return new Random().nextInt(SIZE);
    }
}
