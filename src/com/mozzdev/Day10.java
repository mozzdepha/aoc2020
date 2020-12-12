package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day10 {
    private int depth;
    private long validPerms=1l;
    private List<Integer> list;
    private List<Integer> cypher;
    private int accumulator;
    private Map<String, Long> cache;
    Set<Integer> processed;

    public static void main(String[] args) {
        new Day10().challenge2();
    }

    public Day10() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<Integer>();
        File file = new File("inputs10.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Integer.parseInt(text));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }


    }


    public void challenge1() {
        Collections.sort(list);
        int x=0;
        int count1s =0;
        int count3s =0;
        int previous =0;
        while (x<list.size()) {
            int current = list.get(x);
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
        cache = new HashMap<String, Long>();
        Collections.sort(list);
        System.out.println(list);
        list.add(0, 0);
        long result =1; // start with 1 as the full list is a valid permutation
        for (int x=1; x<list.size()-1; x++) {
            validPerms+=countValidPerms(x, list);
        }
        System.out.println("Complete: " + validPerms);

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
