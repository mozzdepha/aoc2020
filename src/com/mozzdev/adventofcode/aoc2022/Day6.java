package com.mozzdev.adventofcode.aoc2022;

import java.util.*;

public class Day6 extends AocDay2022 {

    public static void main(String[] args) {
        new Day6().run();
    }

    public String getInputFilename() {
        return "inputs6.txt";
    }

    public void challenge1() {
        for (String inputRow : list) {
            findSequence1(inputRow);
        }
    }

    public void challenge2() {
        for (String inputRow : list) {
            findSequence2(inputRow);
        }
    }

    private void findSequence1(String inputRow) {
        char[] sequence = inputRow.toCharArray();
        for (int x=0; x<sequence.length; x++) {
            // add the next 4 characters and test if the unique set length is 4
            Set test = new HashSet();
            test.add(sequence[x]);
            test.add(sequence[x+1]);
            test.add(sequence[x+2]);
            test.add(sequence[x+3]);
            if (test.size()==4) {
                // unique sequence found
                System.out.println(x+4);
                return;
            }
        }
        System.out.println("no unique sequence found");
    }

    private void findSequence2(String inputRow) {
        char[] sequence = inputRow.toCharArray();
        for (int x=0; x<sequence.length; x++) {
            // add the next 4 characters and test if the unique set length is 4
            Set test = new HashSet();
            test.add(sequence[x]);
            test.add(sequence[x+1]);
            test.add(sequence[x+2]);
            test.add(sequence[x+3]);
            test.add(sequence[x+4]);
            test.add(sequence[x+5]);
            test.add(sequence[x+6]);
            test.add(sequence[x+7]);
            test.add(sequence[x+8]);
            test.add(sequence[x+9]);
            test.add(sequence[x+10]);
            test.add(sequence[x+11]);
            test.add(sequence[x+12]);
            test.add(sequence[x+13]);
            if (test.size()==14) {
                // unique sequence found
                System.out.println(x+14);
                return;
            }
        }
        System.out.println("no unique sequence found");
    }


}
