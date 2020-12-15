package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day15 extends AocDay {
    private List<Long> longs;
    private Map<Long, Long> freqMap;
    private long last;
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs15.txt";
    }

    private void initializeLongs() {
        freqMap = new HashMap<Long, Long>();
        longs = new ArrayList<Long>();
        String[] numbers = list.get(0).split(",");
        for (int x=0; x<numbers.length; x++) {
            long current = Long.parseLong(numbers[x]);
            longs.add(current);
            freqMap.put(current, (long)x);
            last = current;
        }
//        Collections.reverse(longs);

    }

//    public void challenge1() {
//        initializeLongs();
//        for (int x=0; x<2017; x++) {
//            long firstElement = longs.get(0);
//            int frequency = Collections.frequency(longs, firstElement);
//            if (frequency == 1) {
//                longs.add(0, 0l);
//            } else {
//                List<Long> longsWithoutFirst = longs.subList(1, longs.size());
//                int indexOfSecond = longsWithoutFirst.indexOf(firstElement);
//                longs.add(0, (long)indexOfSecond+1);
//            }
//        }
//        Collections.reverse(longs);
//        System.out.println(longs.get(2019));
//    }

    public void challenge1() {
        initializeLongs();
        for (long x=longs.size(); x<2020; x++) {
            Long lastOccurence = freqMap.get(last);
            if (lastOccurence == x-1) {
                // last iteration was the first occurence of that number
                freqMap.put(0l, x);
                last =0l;
            } else {
                last = x-lastOccurence;
                freqMap.put(last, x);
            }
        }

//        Collections.reverse(longs);
        System.out.println(longs.get(2019));
    }

//    public void challenge2() {
//        initializeLongs();
//        Map<Long,Long> freqMap = new HashMap<Long, Long>();
//
//        for (long x=0; x<30000000; x++) {
//            long firstElement = longs.get(0);
//            long frequency = freqMap.get(firstElement) == null ? 0l : freqMap.get(firstElement);
//            if (frequency == 0l) {
//                longs.add(0, 0l);
//                freqMap.put(0l, frequency+1);
//            } else {
//                List<Long> longsWithoutFirst = longs.subList(1, longs.size());
//                int indexOfSecond = longsWithoutFirst.indexOf(firstElement);
//                long elementToAdd = (long)indexOfSecond+1;
//                longs.add(0, elementToAdd);
//                long currentFrequency = freqMap.get(elementToAdd) == null ? 0l : freqMap.get(elementToAdd);
//                freqMap.put(elementToAdd, currentFrequency+1);
//            }
//        }
//        Collections.reverse(longs);
//        System.out.println(longs.get(30000000-1));
//    }
}
