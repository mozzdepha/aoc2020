package com.mozzdev;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 {
    private List<String> list;
    public static void main(String[] args) {
        new Day13().challenge1();
    }

    public Day13() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<String>();
        File file = new File("inputs13.txt");
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


    private int target;
    public void challenge1() {
        target = Integer.parseInt(list.get(0));
        int minWait = 10000000;
        int targetBus = 0;
        List<Integer> busses = parseBusses(list.get(1));
        Map<Integer, Integer> remainders = new HashMap<Integer, Integer>();
        for (Integer bus : busses) {
//            remainders.put(bus, target % bus);
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
}
