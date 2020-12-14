package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day14 {
    private List<String> list;
    private List<MemSwitch> switches;
    private Map<Long, Long> results;
    public static void main(String[] args) {
        new Day14().challenge2();
    }

    public Day14() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<String>();
        File file = new File("inputs14.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(text);
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
        parseSwitches();
        results = new HashMap<Long, Long>();
        for (int x = 0; x < switches.size(); x++) {
            results.put((long)switches.get(x).getPosition(), switches.get(x).evaluate());
        }
        long runningTotal = 0l;
        for (Long sum:results.values()) {
            runningTotal+=sum;
        }
        System.out.println(runningTotal);
    }

    public void challenge2() {
        parseSwitches();
        results = new HashMap<Long, Long>();
        for (int x = 0; x < switches.size(); x++) {
            List<Long> memsToUpdate = switches.get(x).evaluateV2(results);
            for (Long memToUpdate: memsToUpdate) {
                results.put(memToUpdate, switches.get(x).getValue());
            }
        }
        long runningTotal = 0l;
        for (Long sum:results.values()) {
            runningTotal+=sum;
        }
        System.out.println(runningTotal);
    }
    private void parseSwitches() {
        switches = new ArrayList<MemSwitch>();
        String currentMask=parseMask(list.get(0));
        for (int x=1; x<list.size();x++) {
            if (list.get(x).startsWith("mask = ")) {
                currentMask=parseMask(list.get(x));
            } else {
                switches.add(parseSwitch(list.get(x), currentMask));
            }
        }
    }

    private String parseMask(String input) {
        return input.substring(7);
    }

    private MemSwitch parseSwitch(String input, String mask) {
        int position = Integer.parseInt(input.substring(4, input.indexOf("]")));
        long value = Long.parseLong(input.substring(input.indexOf(" ") +3));
        return new MemSwitch(mask, position, value);
    }

}
