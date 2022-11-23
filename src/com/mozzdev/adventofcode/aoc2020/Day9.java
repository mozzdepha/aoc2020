package com.mozzdev.adventofcode.aoc2020;

import java.util.*;

public class Day9 extends AocDay2020 {
    private List<Long> cypher;
    private int accumulator;
    Set<Integer> processed;

    public static void main(String[] args) {
        new Day9().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs9.txt";
    }

    public void challenge1() {
        List<Long> longs = new ArrayList<Long>();
        for (String input: list) {
            longs.add(Long.parseLong(input));
        }
        cypher = new ArrayList<Long>();
        cypher.addAll(longs.subList(0, 25));
        System.out.println(cypher.size());
        int x=25;
        while (x<list.size()) {
            checkMatch(longs.get(x), cypher);
            cypher.add(longs.get(x));
            cypher.remove(0);
            x++;
        }
        System.out.println("none found");
    }

    private void checkMatch(Long l, List<Long> cypher) {
        // can we find 2 numbers that sum to l within the cypher
        for (int x=0; x<cypher.size(); x++) {
            Long target = l - cypher.get(x);
            if (!target.equals(cypher.get(x)) && cypher.contains(target)) {
                System.out.println("found match in cypher for  " + l + ": " + cypher.get(x) + " + " + target);
                return;
            }
        }
        System.out.println("No match in cypher for " + l);
        System.exit(0);
    }

    public void challenge2() {
        List<Long> longs = new ArrayList<Long>();
        for (String input: list) {
            longs.add(Long.parseLong(input));
        }
        Long target = 14360655l;
        long startIndex = 0;
        int x=0;
        while (x<list.size()) {
            checkWeakness(x, target, longs);
            x++;
        }
    }

    private void checkWeakness(int startIndex, long target, List<Long> longs) {
        long total=0l;
        for (int x=startIndex; x<longs.size(); x++) {
            total += longs.get(x);
            if (total == target) {
                System.out.println("Found match! start:" + startIndex + "; end: " + x);
                System.out.println(longs.get(startIndex) + list.get(x));
                long min = Collections.min(longs.subList(startIndex, x));
                long max = Collections.max(longs.subList(startIndex, x));
                System.out.println(min + max);
                System.exit(0);
            } else if (total > target) {
                // failed - return
                return;
            }
        }
    }

}
