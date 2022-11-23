package com.mozzdev.adventofcode.common;

import java.util.ArrayList;
import java.util.List;

public class Calculation {
    @Override
    public String toString() {
        return "Calculation{" +
                "sequence=" + sequence +
                '}';
    }

    private List<String> sequence;

    public Calculation() {
        sequence = new ArrayList<>();
    }

    public long evaluate(boolean advanced) {
        if (advanced) {
            // pre-process the sequence to complete additions
            return evaluateAdvanced();
        } else {
            return evaluateStandard();
        }
    }

    private long evaluateAdvanced() {
        List<String> intsToMultiply = null;

        while (sequence.contains("+")) {
            intsToMultiply = new ArrayList<String>();
            for (int x = 0; x < sequence.size(); x++) {
                String current = sequence.get(x);
                if (current.equals("*")) {
                    intsToMultiply.add(sequence.get(x - 1));
                } else if (current.equals("+")) {
                    String previous = sequence.get(x-1);
                    String next = x == sequence.size() - 1 ? "0" : sequence.get(x + 1);
                    //                intsToMultiply.remove(intsToMultiply.size()-1);
                    intsToMultiply.add("" + (Long.parseLong(previous) + Long.parseLong(next)));
                    if (x<sequence.size()-2) {
                        intsToMultiply.addAll(sequence.subList(x+2, sequence.size()));
                    }
                    break;
                } else {
                    try {
                        String next = sequence.get(x+1);
                        if (!next.equals("*") && !next.equals("+")) {
                            intsToMultiply.add(current);
                        }
                    } catch (Exception e) {}
                }
            }
            sequence = intsToMultiply;
        }
        long result = 1l;
        for (String number : sequence) {
            if (!number.equals("*")) {
                result *= Long.parseLong(number);
            }
        }
        return result;


    }

    private long evaluateStandard() {
        long result = 0l;
        for (int x=0; x<sequence.size(); x=x+2) {
            int operand = Integer.parseInt(sequence.get(x));
            char operator = (x==0) ? '+' : sequence.get(x-1).charAt(0);
            if (operator == '+') {
                result += operand;
            } else if (operator == '*') {
                result *= operand;
            } else {
                System.out.println("Unexpected operator: " + operator);
            }
        }
        return result;
    }

    public void add(String inputString) {
        sequence.add(inputString);
    }

    public void add(long result) {
        // do we need to remove the 'l'?
        sequence.add("" + result);
    }

}
