package com.ind.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static com.ind.tests.RenderCalendar144.Interval.interval;

public class RenderCalendar144 {
    public static class Interval {
        public final int start;
        public final int end;

        private Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public static Interval interval(int start, int end) {
            return new Interval(start, end);
        }
    }

    public static void main(String[] args) {
        final List<Interval> events = Arrays.asList(
                interval(1, 5),
                interval(6, 10),
                interval(11, 13),
                interval(14, 15),
                interval(2, 7),
                interval(8, 9),
                interval(12, 15),
                interval(4, 5),
                interval(9, 17)
        );

        final Stack<List<Interval>> stripes = new Stack<>();
        for (Interval interval : events) {

        }
    }
}
