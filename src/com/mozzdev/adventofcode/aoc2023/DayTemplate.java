package com.mozzdev.adventofcode.aoc2023;

public class DayTemplate extends AocDay2023 {

    public static void main(String[] args) {
        new DayTemplate().run();
    }

    public String getInputFilename() {
        return "inputsXX.txt";
    }

    public void challenge1() {
        int total = 0;
        for (String current: list) {
            // do something
        }
        System.out.println(total);
    }

    public void challenge2() {
        int total = 0;
        for (String current: list) {
            // do something
        }
        System.out.println(total);
    }
}
