package com.ind.tests;

import static com.ind.tests.Ints.digits;
import static com.ind.tests.Ints.integer;
import static java.lang.Math.pow;
import static java.lang.String.format;

public class Palindrome59 {

    public static void main(String[] args) {
        final int i = integer("1023563201");
        System.out.println(i);
        System.out.println(digits(integer("9")));

        System.out.println(isPalindrome(i, digits(i)));
    }

    private static boolean isPalindrome(int i, int digits) {
        System.out.println(format("i=%d, digits=%d", i, digits));
        if (digits <= 1) {
            return true;
        }
        final int order = (int)pow(10, digits-1);
        final int r = i % 10;
        if (order > i) {
            return r == 0 && isPalindrome(i / 10, digits-2);
        }
        final int l = i / order;
        return l == r && isPalindrome((i % order) / 10, digits-2);
    }
}
