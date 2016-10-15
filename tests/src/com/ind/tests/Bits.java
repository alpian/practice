package com.ind.tests;

public class Bits {
    public static byte bits(String bits) {
        byte v = 0;
        for (int i=0; i<bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                v += (int)Math.pow(2, bits.length() - i - 1);
            }
        }
        return v;
    }
}
