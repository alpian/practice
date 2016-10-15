package com.ind.tests;

public class Regex2526 {
    public static void main(String[] args) {
        System.out.println(matches("a.W.*9", "aHW1239"));
    }

    private static boolean matches(String regex, String candidate) {
        final Expression ex = Expression.from(regex);
        while(!ex.matches(candidate)) {
            if (candidate.length() == 0) {
                return false;
            }
            candidate = candidate.substring(1);
        }
        return true;
    }

    public static class Expression {
        public final Token token;
        public final Expression next;

        private Expression(Token token, Expression next) {
            this.token = token;
            this.next = next;
        }

        public boolean matches(String candidate) {
            System.out.println(candidate);
            if (next == null) {
                return candidate.length() > 0 && token.consume(candidate).matches;
            }
            final TokenMatch tokenMatch = token.consume(candidate);
            return candidate.length() > 1
                    && tokenMatch.matches
                    && next.matches(tokenMatch.remaining);
        }

        public static Expression from(String regex) {
            final Token token = Token.from(regex.charAt(0));
            if (regex.length() == 1) {
                return new Expression(token, null);
            }
            if (regex.length() > 1 && regex.charAt(1) == '*') {
                return new Expression(new StarToken(Token.from(regex.charAt(0))), from(regex.substring(2)));
            }
            return new Expression(token, from(regex.substring(1)));
        }
    }

    interface Token {
        TokenMatch consume(String input);

        static Token from(char c) {
            switch (c) {
                case '.': return new DotCharacter();
                default: return new SingleCharacter(c);
            }
        }
    }

    public static class TokenMatch {
        public final boolean matches;
        public final String remaining;

        public TokenMatch(boolean matches, String remaining) {
            this.matches = matches;
            this.remaining = remaining;
        }
    }

    public static class StarToken implements Token {
        private final Token expected;

        public StarToken(Token expected) {
            this.expected = expected;
        }

        @Override
        public TokenMatch consume(String input) {
            System.out.println("STAR:" + input);
            if (input.length() == 0) {
                return new TokenMatch(true, "");
            }
            String last = input;
            TokenMatch tokenMatch = expected.consume(last);
            while (tokenMatch.matches) {
                System.out.println("STAR-last:" + last);
                last = tokenMatch.remaining;
                tokenMatch = expected.consume(last);
            }
            return new TokenMatch(true, last);
        }
    }

    public static class DotCharacter implements Token {
        @Override
        public TokenMatch consume(String input) {
            if (input.length() == 0) {
                return new TokenMatch(false, "");
            }
            return new TokenMatch(true, input.substring(1));
        }
    }

    public static class SingleCharacter implements Token {
        private final char expected;

        public SingleCharacter(char expected) {
            this.expected = expected;
        }

        @Override
        public TokenMatch consume(String input) {
            if (input.length() == 0) {
                return new TokenMatch(false, "");
            }
            return new TokenMatch(input.charAt(0) == expected, input.substring(1));
        }
    }
}
