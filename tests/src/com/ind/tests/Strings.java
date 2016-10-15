package com.ind.tests;

public class Strings {
    public static String repeat(char c, int size) {
        final char[] f = new char[size];
        for (int i=0;i<size; i++) {
            f[i] =  c;
        }
        return new String(f);
    }

    public static int differences(String a, String b) {
        int count = 0;
        for (int i=0; i<a.length(); i++) {
            if (b.length() <= i) {
                count++;
            }
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
