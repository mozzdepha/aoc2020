package com.mozzdev.adventofcode.aoc2022;

import java.util.*;

public class Day10 extends AocDay2022 {
    public static void main(String[] args) {
        new Day10().run();
    }

    private static final String NOOP = "noop";
    private static final String ADDX = "addx";

    private List<Instruction> instructions;
    public String getInputFilename() {
        return "inputs10.txt";
    }

    public void challenge1() {
        init();
        int cycle = 1;
        int x = 1;
        Map strengths = new HashMap();
        for (Instruction ins : instructions) {
            if (ins.instruction.equals(NOOP)) {
                strengths.put(cycle, x);
                cycle++;
            } else {
                strengths.put(cycle, x);
                cycle++;
                strengths.put(cycle, x);
                cycle++;
                x += ins.parameter;
            }
        }
        double strength20  = getStrength(strengths, 20);
        double strength60  = getStrength(strengths, 60);
        double strength100  = getStrength(strengths, 100);
        double strength140  = getStrength(strengths, 140);
        double strength180  = getStrength(strengths, 180);
        double strength220  = getStrength(strengths, 220);
        double totalStrength = strength20 + strength60 + strength100 + strength140 + strength180 + strength220;
        System.out.println(totalStrength);
    }

    private double getStrength(Map strengths, int key) {
        return key * (int)strengths.get(key);
    }

    public void challenge2() {
        init();
        char[] screen = new char[240];
        for (int x=0; x<screen.length; x++) {
            screen[x] = '.';
        }
        int cycle = 1;
        int x = 1;
        for (Instruction ins : instructions) {
            performScreenCycle(screen, cycle, x);
            cycle++;
            if (ins.instruction.equals(ADDX)) {
                performScreenCycle(screen, cycle, x);
                cycle++;
                x += ins.parameter;
            }
        }
        output(screen);
    }

    private void performScreenCycle(char[] screen, int cycle, int x) {
        int pixelCount = (cycle-1)%40;
        if (Math.abs(x-pixelCount)<2) {
            // match
            screen[cycle-1] = '#';
        }
    }

    private void output(char[] screen) {
        int row = 1;
        StringBuffer sb = new StringBuffer();
        for (int x=1; x<=screen.length; x++) {
            sb.append(screen[x-1]);
            if (x%40==0) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private void init() {
        instructions = new ArrayList<Instruction>();
        for (String inputRow: list) {
            instructions.add(new Instruction(inputRow));
        }
    }

    private class Instruction {
        private String instruction;
        private int parameter;

        public Instruction(String input) {
            instruction = input.substring(0, 4);
            if (!instruction.equals(NOOP)) {
                parameter = Integer.parseInt(input.substring(5));
            }
        }
    }
}
