package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day10 extends AocDay {
    private long validPerms=1l;
    private List<Integer> listOfIntegers;
    private Map<String, Long> cache;

    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs10.txt";
    }

    public void challenge1() {
        initializeInts();
        Collections.sort(listOfIntegers);
        int x=0;
        int count1s =0;
        int count3s =0;
        int previous =0;
        while (x< listOfIntegers.size()) {
            int current = listOfIntegers.get(x);
            int diff = current - previous;
            if (diff ==1) {
                count1s++;
            }
            if (diff==3) {
                count3s++;
            }
            previous = current;
            x++;
        }
        count3s++;
        System.out.println(count1s * count3s);
    }

    public void challenge2() {
        initializeInts();
        cache = new HashMap<String, Long>();
        Collections.sort(listOfIntegers);
        System.out.println(listOfIntegers);
        listOfIntegers.add(0, 0);
        long result =1; // start with 1 as the full list is a valid permutation
        for (int x = 1; x< listOfIntegers.size()-1; x++) {
            validPerms+=countValidPerms(x, listOfIntegers);
        }
        System.out.println("Complete: " + validPerms);

    }

    private void initializeInts() {
        listOfIntegers = new ArrayList<Integer>();
        for (String input: list) {
            listOfIntegers.add(Integer.parseInt(input));
        }
    }

    private long countValidPerms(int itemToRemove, List<Integer> testList) {
        int current = testList.get(itemToRemove);
        int next = testList.get(itemToRemove+1);
        int previous = testList.get(itemToRemove-1);
        long thisLoop = 0l;

        if (cache.containsKey("" + current + "-" + previous)) {
            thisLoop = cache.get("" + current + "-" + previous);
        } else {

            if (next - previous <= 3) {
                thisLoop++;
                // now need to recurse into later items for this pass
                List<Integer> tester = new ArrayList<Integer>();
                tester.addAll(testList);
                tester.remove(itemToRemove);
                for (int x = itemToRemove; x < tester.size() - 1; x++) {
                    thisLoop+=countValidPerms(x, tester);
                }
                cache.put("" + current + "-" + previous, thisLoop);
            } else {
                cache.put("" + current + "-" + previous, 0l);
            }
        }
        return thisLoop;
    }

}
