package com.mozzdev.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day1 extends AocDay2023 {

    private static final String[] INT_STRINGS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final int[] INT_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        new Day1().run();
    }

    public String getInputFilename() {
        return "inputs1.txt";
    }

    public void challenge1() {
        int total = 0;
        for (String current: list) {
            total += getTotal(current);
        }
        System.out.println(total);
    }


    private int getTotal(String current) {
        return Integer.parseInt(getFirstInt(current) + getLastInt(current));
    }

    private String getFirstInt(String current) {
        for (int x=0; x<current.length(); x++) {

            String currentS = current.substring(x, x + 1);
            if (isDigit(currentS)) {
                return currentS;
            }
        }
        return "";
    }

    private String getLastInt(String current) {
        String input = new StringBuilder(current).reverse().toString();
        return getFirstInt(input);
    }

    private boolean isDigit(String s) {
        Character currentChar = s.charAt(0);
        return (currentChar.hashCode() >= 48 && currentChar.hashCode() <=57);
    }

    public void challenge2() {
        int total = 0;
        for (String current: list) {
            List<Integer> ints = getInts(current);
            String result = "" + ints.get(0) + ints.get(ints.size()-1);
            total += Integer.parseInt(result);
        }
        System.out.println(total);
    }

    private List<Integer> getInts(String input) {
        List<Integer> ints = new ArrayList<>();
        for (int x=0; x<input.length(); x++) {
            if (isDigit(input.substring(x, x+1))) {
                ints.add(Integer.parseInt(input.substring(x, x+1)));
            } else {
                int foundValue = getIntegerForString(input.substring(x));
                if (foundValue >=0) {
                    ints.add(foundValue);
                }
            }
        }
        return ints;
    }


    private int getIntegerForString(String input) {
        for (int x=0; x<INT_STRINGS.length; x++) {
            if (input.startsWith(INT_STRINGS[x])) {
                return INT_VALUES[x];
            }
        }
        return -1;
    }
}
