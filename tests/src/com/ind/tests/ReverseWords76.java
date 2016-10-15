package com.ind.tests;

public class ReverseWords76 {
    public static void main(String[] args) {
        final String sentence = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum";

        reverseWords(sentence.toCharArray());

//        final Stack<String> stack = new Stack<>();
//        Stream.of(sentence.split(" ")).forEach(stack::push);
////        System.out.println(stack.stream().collect(Collectors.joining(" ")));
//        while(!stack.isEmpty()) {
//            System.out.print(stack.pop() + " ");
//        }
    }

    private static void reverseWords(char[] sentence) {
        swapChars(sentence, 0,sentence.length-1);
        for (int w=0; w<sentence.length; ) {
            int blankIndex = find(sentence, w, ' ');
            swapChars(sentence, w, blankIndex-1);
            w=blankIndex+1;
        }
        System.out.println(sentence);
    }

    private static int find(char[] sentence, int offset, char c) {
        for (int i=offset; i<sentence.length; i++) {
            if (sentence[i] == c) {
                return i;
            }
        }
        return sentence.length-1;
    }

    private static void swapChars(char[] sentence, int i, int j) {
        for (; i<j; i++, j--) {
            swap(sentence, i, j);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
