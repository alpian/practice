package com.ind.tests;

import static java.lang.Math.log10;

public class Ints {
    public static int integer(String s) {
        return Integer.valueOf(s);
    }

    public static int digits(int i) {
        return (int) log10(i) + 1;
    }

    public static int msd(int i) {
        final int digits = digits(i);
        if (digits == 1) {
            return i;
        }
        return i / (digits - 1);
    }
}
