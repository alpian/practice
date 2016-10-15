package com.ind.tests;

public class LookAndSay78 {
    public static void main(String[] args) {
        final int n = 20;
        System.out.println(lookAndSay(n));
    }

    private static String lookAndSay(int n) {
        String last = "1";
        for (int i=1; i<n; i++) {
            last = describe(last);
        }
        return last;
    }

    private static String describe(String s) {
        System.out.println("describe: " + s);
        final StringBuilder description = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for (int i=1; i<s.length(); i++) {
            if (c != s.charAt(i)) {
                description.append(say(count, c));
                c = s.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        description.append(say(count, c));
        return description.toString();
    }

    private static String say(int count, char c) {
        return String.valueOf(count) + String.valueOf(c);
    }
}
