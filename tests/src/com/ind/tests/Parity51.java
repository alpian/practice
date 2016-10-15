package com.ind.tests;

public class Parity51 {

    public static void main(String[] args) {
        final byte b = Bits.bits("10000000");
        System.out.println(b);

        int set = 0;
        for (int i=0; i<8; i++) {
            set += ((b >>> i) & 0x1);
        }
        System.out.println("odd? " + (set % 2 == 1));
    }
}
