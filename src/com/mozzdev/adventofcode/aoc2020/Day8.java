package com.mozzdev.adventofcode.aoc2020;

import java.util.*;

public class Day8 extends AocDay2020 {
    private int accumulator;
    Set<Integer> processed;

    public static void main(String[] args) {

        new Day8().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs8.txt";
    }

    public void challenge1() {
        processed = new HashSet<Integer>();
        int x=0;
        boolean stillSearching = true;
        while (stillSearching) {
            if (processed.contains(x)) {
                System.out.println("Loop reached. Accumulator=" + accumulator);
                stillSearching = false;
            } else {
                processed.add(x);

                String[] currentInstruction = list.get(x).split(" ");
                String op = currentInstruction[0];
                int param = Integer.parseInt(currentInstruction[1]);


                switch (op) {
                    case "acc":
                        accumulator += param;
                        x++;
                        break;
                    case "jmp":
                        x += param;
                        break;
                    case "nop":
                        x++;
                        break;
                    default:
                        System.out.println("No match for instruction:" + op);
                }
            }
        }

    }

    public void challenge2() {
        // for each row, attempt to correct the code by making the switch
        boolean stillSearching = true;
        int x=0;
        while (stillSearching) {
            stillSearching = attemptBugFix(x);
            x++;
        }
    }

    private boolean attemptBugFix(int row) {
        processed = new HashSet<Integer>();
        accumulator = 0;
        int lastInstructionPointer = list.size() - 1;
        int x=0;
        while (x!=lastInstructionPointer) {
            if (processed.contains(x)) {
                return true;
            } else {
                processed.add(x);
            }
            String[] currentInstruction = list.get(x).split(" ");
            String op = currentInstruction[0];
            int param = Integer.parseInt(currentInstruction[1]);

            // attempt bugfix
            if (x==row) {
                if (op.equals("jmp")) {
                    op = "nop";
                } else if (op.equals("nop")) {
                    op = "jmp";
                }
            }

            switch(op) {
                case "acc" :
                    accumulator+=param;
                    x++;
                    break;
                case "jmp" :
                    x+=param;
                    break;
                case "nop" :
                    x++;
                    break;
                default:
                    System.out.println("No match for instruction:" + op);
            }

        }
        System.out.println("End of program reached! Acc=" + accumulator);
        return false;

    }

}
