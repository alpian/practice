package com.ind.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AlmostSorted113 {
    public static class Sorted implements Iterator<Integer> {
        private final int k;
        private final List<Integer> source;
        private final List<Integer> buffer;

        public Sorted(int k, List<Integer> source) {
            this.k = k;
            this.source = source;
            this.buffer = new ArrayList<>();
        }

        @Override
        public boolean hasNext() {
            fillBufferSorted();
            return !buffer.isEmpty();
        }

        @Override
        public Integer next() {
            return buffer.remove(0);
        }

        private void fillBufferSorted() {
            while (buffer.size() < k && !source.isEmpty()) {
                bufferSorted(source.remove(0));
            }
        }

        private void bufferSorted(Integer v) {
            for (int i=0; i<buffer.size(); i++) {
                if (v < buffer.get(i)) {
                    buffer.add(i, v);
                    return;
                }
            }
            buffer.add(v);
        }
    }

    public static void main(String[] args) {
        final Sorted sorted = new Sorted(2, new ArrayList<>(Arrays.asList(1, 3, 2, 4, 5, 7, 6, 8, 10, 9)));
        while (sorted.hasNext()) {
            System.out.println(sorted.next());
        }
    }
}
