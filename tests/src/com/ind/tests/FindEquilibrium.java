package com.ind.tests;

import java.util.ArrayList;
import java.util.HashMap;

public class FindEquilibrium {

    public static void main(String[] args) {
        System.out.println(findAllEquilibrium());
    }

    private static ArrayList<Integer> findAllEquilibrium() {
        final ArrayList<Integer> found = new ArrayList<>();
//        int[] ns = {-1, 3, -4, 5, 1, -6, 2, 1};
        int[] ns = {1, 2, 3, 4, 5, 6, 7, 0, 7, 6, 5, 4, 3, 2, 1};
        int left = 0;
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i< ns.length; i++) {
            map.put(i, left);
            left += ns[i];
        }
        int right = 0;
        for (int i=ns.length-1; i>=0; i--) {
            int computedLeft = map.get(i);
            if (computedLeft == right) {
                found.add(i);
            }
            right += ns[i];
        }
        return found;
    }
}
