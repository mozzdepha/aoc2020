package com.mozzdev.adventofcode.aoc2022;

import java.text.NumberFormat;
import java.util.*;

public class Day13 extends AocDay2022 {

    private List<Packet> packets;

    public static void main(String[] args) {
        new Day13().run();
    }

    public String getInputFilename() {
        return "inputs13.txt";
    }

    public void challenge1() {
        init();
        int result = 0;
        for (int x=1; x<=packets.size(); x++) {
            if (packets.get(x-1).isInRightOrder()) {
//                System.out.println("Packet " + x + " is in order");
                result+=x;
            }
        }
        System.out.println(result);
    }

    public void challenge2() {
        init();


    }

    private void init() {
        packets = new ArrayList<Packet>();
        for (int x=0; x<list.size(); x++) {
            packets.add(new Packet(list.get(x), list.get(x+1)));
            x++;
            x++;
        }
    }

    private class Packet {
        private List left;
        private List right;

        public Packet(String leftInput, String rightInput) {
            left = parse(leftInput);
            right = parse(rightInput);
        }

        private List parse(String input) {
            List response = new ArrayList();
            List current = response;
            int currentLevel = 0;
            String[] tokens = input.split(",");
            Stack stack = new Stack();
            stack.add(current);
            for (int x=0; x<tokens.length; x++) {
                int openCount = getCharCount(tokens[x], '[');
                for (int y=0; y<openCount; y++) {
                    List tmp = new ArrayList();
                    current.add(tmp);
                    current = tmp;
                    currentLevel++;
                    stack.push(current);
                }
                int start = tokens[x].startsWith("[") ? tokens[x].lastIndexOf("[") + 1 : 0;
                int end = tokens[x].endsWith("]") ? tokens[x].indexOf("]") : tokens[x].length();
                String numeric = tokens[x].substring(start, end);
                if (numeric.length()>0) {
                    current.add(Integer.parseInt(numeric));
                }

                int closeCount = getCharCount(tokens[x], ']');
                for (int y=0; y<closeCount; y++) {
                    List tmp = new ArrayList();

                    stack.pop();
                    current = (List)stack.peek();
                }
            }
            return response;
        }

        private int getCharCount(String input, char character) {
            int found =0;
            for (int x=0; x<input.length(); x++) {
                if (input.charAt(x)== character) {
                    found++;
                }
            }
            return found;
        }

        public boolean isInRightOrder() {
            if (right.size()<left.size()) {
                return false;
            }
            Object lCurrent;
            Object rCurrent;
            for (int x=0; x<left.size(); x++) {
                lCurrent=left.get(x);
                rCurrent=right.get(x);
                if (!isInOrder(lCurrent, rCurrent)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isInOrder(Object lCurrent, Object rCurrent) {
            List lWorking;
            List rWorking;

            // if both are integers, simply compare them
            if (lCurrent instanceof Integer && rCurrent instanceof Integer) {
                return ((Integer)lCurrent) <=((Integer)rCurrent);
            }

            if (lCurrent instanceof Integer) {
                lWorking = new ArrayList();
                lWorking.add(lCurrent);
            } else {
                lWorking = (List)lCurrent;
            }

            boolean conversionSpecialCase = false;
            if (rCurrent instanceof Integer) {
                rWorking = new ArrayList();
                rWorking.add(rCurrent);
                conversionSpecialCase = true;

            } else {
                rWorking = (List)rCurrent;
            }

            if (conversionSpecialCase) {
                for (int x = 0; x < lWorking.size(); x++) {
                    if (!isInOrder(lWorking.get(x), rWorking.get(0))) {
                        return false;
                    }
                }
            } else {
                if (rWorking.size() < lWorking.size()) {
                    return false;
                }
                for (int x = 0; x < lWorking.size(); x++) {
                    if (!isInOrder(lWorking.get(x), rWorking.get(x))) {
                        return false;
                    }
                }
            }
            return true;

        }
    }

}
