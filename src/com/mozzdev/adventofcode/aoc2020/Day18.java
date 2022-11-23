package com.mozzdev.adventofcode.aoc2020;

import com.mozzdev.adventofcode.common.Calculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day18 extends AocDay2020 {

    private List<Calculation> calculations;
    private Stack<Calculation> stack;

    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs18.txt";
    }

    public void challenge1() {
        parseTuples(false);
    }

    public void challenge2() {
        parseTuples(true);
    }

    private void parseTuples(boolean advanced) {
        calculations = new ArrayList<Calculation>();
        stack = new Stack<Calculation>();
        long result = 0;
        for (String row: list) {
            Calculation calculation = parseTuple(row, advanced);
             calculations.add(calculation);
             result += calculation.evaluate(advanced);
        }
        System.out.println("Grand total:" + result);
    }

    private Calculation parseTuple(String input, boolean advanced) {
        String words [] = input.split(" ");
        stack.push(new Calculation());
        for (int x=0; x< words.length; x++) {
            String word = words[x];
            String wordWithoutOpenBrackets = word.replaceAll("\\(", "");;
            String wordWithoutCloseBrackets = word.replaceAll("\\)", "");
            String wordWithNoBrackets = wordWithoutOpenBrackets.replaceAll("\\)", "");

            // process open brackets by pushing new tuples onto the stack
            for (int y=0; y<word.length()-wordWithoutOpenBrackets.length(); y++) {
                stack.push(new Calculation());
            }
            // process the number/operator
            stack.peek().add(wordWithNoBrackets);
            // process end brackets
            for (int y=0; y<word.length() - wordWithoutCloseBrackets.length(); y++) {
                long result = stack.pop().evaluate(advanced);
                stack.peek().add(result);
            }
        }
        return stack.pop();
    }
}
