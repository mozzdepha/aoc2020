package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day9 {
    private List<Long> list;
    private List<Long> cypher;
    private int accumulator;
    Set<Integer> processed;

    public static void main(String[] args) {
        new Day9().challenge2();
    }

    public Day9() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<Long>();
        File file = new File("inputs9.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Long.parseLong(text));
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
        cypher = new ArrayList<Long>();
        cypher.addAll(list.subList(0, 25));
        System.out.println(cypher.size());
        int x=25;
        while (x<list.size()) {
            checkMatch(list.get(x), cypher);
            cypher.add(list.get(x));
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
        Long target = 14360655l;
        long startIndex = 0;
        int x=0;
        while (x<list.size()) {
            checkWeakness(x, target);
            x++;
        }
    }

    private void checkWeakness(int startIndex, long target) {
        long total=0l;
        for (int x=startIndex; x<list.size(); x++) {
            total += list.get(x);
            if (total == target) {
                System.out.println("Found match! start:" + startIndex + "; end: " + x);
                System.out.println(list.get(startIndex) + list.get(x));
                long min = Collections.min(list.subList(startIndex, x));
                long max = Collections.max(list.subList(startIndex, x));
                System.out.println(min + max);
                System.exit(0);
            } else if (total > target) {
                // failed - return
                return;
            }
        }
    }

}
