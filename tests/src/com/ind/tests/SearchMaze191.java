package com.ind.tests;

import java.util.Stack;

import static com.ind.tests.SearchMaze191.Coords.coords;

public class SearchMaze191 {
    public static final String mazeAsText =
            "#_____##_£"
        +   "__#_______"
        +   "#_#__##_##"
        +   "___###__#_"
        +   "_##_______"
        +   "_##__#_##_"
        +   "____#_____"
        +   "#_#_#_#___"
        +   "#_##___###"
        +   "$______##_"
            ;

    public static class Maze {
        private final String[] grid;

        private Maze(String[] grid) {
            this.grid = grid;
        }

        public static Maze make(String maze, int columns) {
            final String[] box = new String[columns];
            for (int i=0; i<columns; i++) {
                final int beginIndex = i * columns;
                box[i] = maze.substring(beginIndex, beginIndex + columns);
            }
            return new Maze(box);
        }

        public void print() {
            System.out.println("--------------------");
            for (String line : grid) {
                System.out.println(line);
            }
            System.out.println("--------------------");
        }

        public Coords find(char c) {
            for (int i=0; i<grid.length; i++) {
                final int x = grid[i].indexOf(c);
                if (x > -1) {
                    return coords(x, i);
                }
            }
            return null;
        }

        public char charAt(Coords position) {
            if (outOfBounds(position.x) || outOfBounds(position.y)) {
                return '\0';
            }
            return grid[position.y].charAt(position.x);
        }

        public boolean outOfBounds(int i) {
            return i < 0 || i >= grid.length;
        }

        public Maze directions(Maze maze, Coords position) {
            char current = maze.charAt(position);
            char north = maze.northOf(position);
            char west = maze.westOf(position);
            char south = maze.southOf(position);
            char east = maze.eastOf(position);
            return canWalk('N', current, north)
                    ? maze.direction(position, 'N')
                    : canWalk('W', current, west)
                        ? maze.direction(position, 'W')
                        : canWalk('S', current, south)
                            ? maze.direction(position, 'S')
                            : maze.canWalk('E', current, east)
                                ? maze.direction(position, 'E')
                                : maze.direction(position, 'X');
        }

        public Maze set(Coords position, char c) {
            return direction(position, c);
        }

        private Maze direction(Coords position, char d) {
            final String[] copy = copy(grid);
            final char[] row = copy[position.y].toCharArray();
            row[position.x] = d;
            copy[position.y] = new String(row);
            return new Maze(copy);
        }

        private String[] copy(String[] grid) {
            final String[] copy = new String[grid.length];
            System.arraycopy(grid, 0, copy, 0, copy.length);
            return copy;
        }

        private boolean canWalk(char symbol, char current, char c) {
            return (symbol != current) && (c == '_' || c == '£');
        }

        private char eastOf(Coords position) {
            return charAt(position.east());
        }

        private char southOf(Coords position) {
            return charAt(position.south());
        }

        private char westOf(Coords position) {
            return charAt(position.west());
        }

        private char northOf(Coords position) {
            return charAt(position.north());
        }

        public Coords walk(Coords position) {
            final char direction = charAt(position);
            switch (direction) {
                case 'N': return position.north();
                case 'W': return position.west();
                case 'S': return position.south();
                case 'E': return position.east();
                default: throw new IllegalStateException("Impossible maze");
            }
        }
    }

    public static class Coords {
        public final int x;
        public final int y;

        private Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coords coords(int x, int y) {
            return new Coords(x, y);
        }

        public Coords north() {
            return coords(x, y-1);
        }

        public Coords south() {
            return coords(x, y+1);
        }

        public Coords east() {
            return coords(x+1, y);
        }

        public Coords west() {
            return coords(x-1, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static void main(String[] args) {
        Maze maze = Maze.make(mazeAsText, 10);
        maze.print();
        Coords position = maze.find('$');
        maze = maze.set(position, '_');
        System.out.println(position.toString());
        final Stack<Coords> stack = new Stack<>();
        while ('£' != maze.charAt(position)) {
            maze = maze.directions(maze, position);
            while (maze.charAt(position) == 'X') {
                // need to step back
                System.out.println("going back at=" + position);
                position = stack.pop();
                maze.print();
                maze = maze.directions(maze, position);
            }
            stack.push(position);
            position = maze.walk(position);
        }
        maze.print();
        System.out.println("Found it!");
    }
}
