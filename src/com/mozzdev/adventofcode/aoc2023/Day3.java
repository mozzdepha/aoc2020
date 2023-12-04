package com.mozzdev.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day3 extends AocDay2023 {

    public static void main(String[] args) {
        new Day3().run();
    }

    public String getInputFilename() {
        return "inputs3.txt";
    }

    public void challenge1() {
        double total = 0;
        initalisePositions(symbols, numbers);
        for (Numbers n: numbers) {
            if (hasAdjacentSymbol(n, symbols)) {
                total += n.getValue();
            }
        }
        System.out.println(total);
    }

    private boolean hasAdjacentSymbol(Numbers n, List<Position> symbols) {
        List<Position> possiblePositions = collectPositions(n);
        return findMatch(possiblePositions, symbols);
    }

    private boolean findMatch(List<Position> potentials, List<Position> symbols) {
        for (Position p: potentials) {
            if (symbols.contains(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find all potential locations for an adjacent symbol
     * @param n
     * @return
     */
    private List<Position> collectPositions(Numbers n) {
        List<Position> positions = new ArrayList<>();
        // could be directly to the left
        if (n.getY()>0) {
            positions.add(new Position(n.getX(), n.getY()-1));
        }
        // could be directly to the right
        if (n.getY() + n.getLength() < list.get(0).length()-1) {
            positions.add(new Position(n.getX(), n.getY()+n.getLength()));
        }

        // could be directly above
        if (n.getX() >0) {
            for (int x=0; x<n.getLength(); x++) {
                positions.add(new Position(n.getX()-1, n.getY()+x));
            }
            // add the upper diagonals
            if (n.getY()>0) {
                positions.add(new Position(n.getX()-1, n.getY()-1));
            }
            if (n.getY() + n.getLength() < list.get(0).length()-1) {
                positions.add(new Position(n.getX()-1, n.getY()+n.getLength()));
            }
        }

        // could be directly below
        if (n.getX() < list.size()-1) {
            for (int x=0; x<n.getLength(); x++) {
                positions.add(new Position(n.getX()+1, n.getY()+x));
            }
            // add the lower diagonals
            if (n.getY()>0) {
                positions.add(new Position(n.getX()+1, n.getY()-1));
            }
            if (n.getY() + n.getLength() < list.get(0).length()-1) {
                positions.add(new Position(n.getX()+1, n.getY()+n.getLength()));
            }
        }

        return positions;
    }

    private String trimSymbols(String numString) {
        StringBuffer sb = new StringBuffer();
        for (int x=0; x<numString.length(); x++) {
            if (isSymbol(numString.charAt(x))) {
                return sb.toString();
            } else {
                sb.append(numString.charAt(x));
            }
        }
        return sb.toString();
    }

    private class Numbers {
        private int value;
        private int x;
        private int y;
        private int length;

        public Numbers(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Numbers{" +
                    "value=" + value +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
            this.length = String.valueOf(value).length();
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    /**
     * If the current digit is a symbol or a dot, move to the next
     * If it is a numeric, create an object that stores both the position and length
     * @param s
     * @return
     */

    private class Position {
        int row, column;
        char symbol;

        @Override
        public String toString() {
            return "Position{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return getRow() == position.getRow() && getColumn() == position.getColumn();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRow(), getColumn());
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public Position (int x, int y) {
            this.row = x;
            this.column = y;
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        public void setSymbol(char symbol) {
            this.symbol = symbol;
        }
    }


    private final static String SYMBOLS = "*$+#=/%-@&";
    private boolean isSymbol(char tokenChar) {
        return SYMBOLS.contains(String.valueOf( tokenChar));
    }

    private List<Position> symbols = new ArrayList<>();
    private List<Numbers> numbers = new ArrayList<>();

    public void challenge2() {
        int total = 0;

        // for each token that is '*' obtain the gearing ratio
        for (Position symbol : symbols) {
            if (symbol.getSymbol() == '*') {
                total += getGearingRatio(symbol);
            }
        }
        System.out.println(total);

    }

    private double getGearingRatio(Position symbol) {
        List<Integer> foundValue = new ArrayList<>();
        // check if there is a number to the left
        if(symbol.getColumn()>0) {
            int found = findNumbersToLeft(symbol);
            if (found>0) {
                foundValue.add(found);
            }
        }
        // check if there is a number to the right
        if (symbol.getColumn()<list.get(0).length()-1) {
            int found = findNumbersToRight(symbol);
            if (found>0) {
                foundValue.add(found);
            }
        }

        // check above - careful, there might be > 1
        if (symbol.getRow() >0) {
            for (Numbers n : numbers) {
                if (n.getX() == symbol.getRow()-1 && inColumnRange(n, symbol)) {
                    foundValue.add(n.getValue());
                }
            }
        }

        // check below - careful, there might be >1
        if (symbol.getRow() < list.size()-1) {
            for (Numbers n : numbers) {
                if (n.getX() == symbol.getRow()+1 && inColumnRange(n, symbol)) {
                    foundValue.add(n.getValue());
                }
            }
        }

        // multiple together if there are EXACTLY TWO matches
        if (foundValue.size() == 2) {
            return foundValue.get(0) * foundValue.get(1);
        } else {
            return 0;
        }
    }

    private boolean inColumnRange(Numbers n, Position symbol) {
        int minEnd = symbol.getColumn() -1;
        int maxStart = symbol.getColumn() +1;
        boolean result = (n.getY() <= maxStart && n.getY()+n.getLength()-1>=minEnd);
        return result;
    }

    private int findNumbersToRight(Position symbol) {
        // if there is a number immediately after this symbol, return it
        for (Numbers n : numbers) {
            if (n.getX() == symbol.getRow() && n.getY() == symbol.getColumn()+1) {
                return n.getValue();
            }
        }
        return 0;
    }

    private int findNumbersToLeft(Position symbol) {
        // if there is a number immediately before this symbol, return it
        for (Numbers n : numbers) {
            if (n.getX() == symbol.getRow() && n.getY()+n.getLength()-1 == symbol.getColumn()-1) {
                return n.getValue();
            }
        }
        return 0;
    }

    private void initalisePositions(List<Position> symbols, List<Numbers> numbers) {
        for (int x = 0; x < list.size(); x++) {
            String current = list.get(x);
            for (int y = 0; y < current.length(); y++) {
                if (isSymbol(current.charAt(y))) {
                    Position pos = new Position(x, y);
                    pos.setSymbol(current.charAt(y));
                    symbols.add(pos);
                } else if (current.charAt(y) == '.') {
                    // do nothing
                } else {
                    // we have found a number.  Need to store the number and jump head to the next non-number
                    Numbers n = new Numbers(x, y);
                    int nextDot = current.indexOf(".", y);
                    String numString = current.substring(y, nextDot == -1 ? current.length() : nextDot);
                    numString = trimSymbols(numString);
                    n.setValue(Integer.parseInt(numString));
                    y += n.getLength() - 1;
                    numbers.add(n);
                }
            }
        }
    }

}
