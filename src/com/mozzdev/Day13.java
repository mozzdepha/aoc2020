package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day13 extends AocDay {
    private int target;

    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs13.txt";
    }

    public void challenge1() {
        target = Integer.parseInt(list.get(0));
        int minWait = 10000000;
        int targetBus = 0;
        List<Integer> busses = parseBusses(list.get(1));
        Map<Integer, Integer> remainders = new HashMap<Integer, Integer>();
        for (Integer bus : busses) {
            int wait = findWait(target, bus);
            System.out.println("Bus: " + bus + "; remainder: " + wait);
            if (wait < minWait) {
                minWait = wait;
                targetBus = bus;
            }
        }
        System.out.println("Bus: " + targetBus + "; remainder: " + minWait + "; Result: " + (targetBus * minWait));

    }

    private int findWait(int target, int bus) {
        int x=1;
        int goal=0;
        while (true) {
            goal+=bus;
            if (goal > target) {
                return goal-target;
            }
            x++;
        }
    }

    private List<Integer> parseBusses(String buss) {
        String[] chars = buss.split(",");
        List<Integer> output = new ArrayList<Integer>();
        for (int x=0; x< chars.length; x++) {
            if (!chars[x].equals("x")) {
                output.add(Integer.parseInt(chars[x]));
            }
        }
        return output;
    }


    public void challenge2() {
        List<Integer> busNumbers = generateBusNumbers();
        System.out.println(busNumbers);
        int countUnits = Collections.max(busNumbers);
        int indexOfMax = busNumbers.indexOf(countUnits);
        long pass=1l;
        boolean stillSearching = true;
        while (stillSearching) {
            // if all busses are divisible by their relative position, we have a winner
            stillSearching = isIncompatible(pass*countUnits, busNumbers, indexOfMax);
            pass++;
        }
        System.out.println("Done");

    }

    private boolean isIncompatible(long baseline, List<Integer> busNumbers, int indexOfMax) {
        for (int x=0; x<busNumbers.size(); x++) {
            if (busNumbers.get(x) != -1) {
                int offset = x - indexOfMax;
                long targetPosition = baseline + offset;
                if (targetPosition % busNumbers.get(x) != 0) {
                    return true;
                }
            }
        }
        System.out.println(baseline - indexOfMax);
        return false;
    }

    private List<Integer> generateBusNumbers() {
        List<Integer> output = new ArrayList<Integer>();
        String[] parsed = list.get(1).split(",");
        for (int x=0; x<parsed.length;x++) {
            output.add(parsed[x].equals("x") ? -1 : Integer.parseInt(parsed[x]));
        }
        return output;
    }
}
