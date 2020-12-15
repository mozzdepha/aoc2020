package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day15 extends AocDay {
    private List<Long> longs;
    private Map<Long, Long> pointerMap;
    private long last;
    private long oneBeforeLast;
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs15.txt";
    }

    private void initializeLongs() {
        pointerMap = new HashMap<Long, Long>();
        longs = new ArrayList<Long>();
        String[] numbers = list.get(0).split(",");
        for (int x=0; x<numbers.length-1; x++) {
            long current = Long.parseLong(numbers[x]);
            longs.add(current);
            pointerMap.put(current, (long)x);
        }
        last = Long.parseLong(numbers[numbers.length-1]);
    }

    public void challenge1() {
        runChallenge(2020l);
    }

    public void challenge2() {
        runChallenge(30000000l);
    }

    private void runChallenge(long iterations) {
        initializeLongs();
        for (long x=longs.size(); x<iterations; x++) {
            Long lastOccurence = pointerMap.get(last);
            pointerMap.put(last, x);
            oneBeforeLast = last;
            last = (lastOccurence == null) ? 0 : x-lastOccurence;
        }
        System.out.println(oneBeforeLast);
    }

}
