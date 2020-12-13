package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day13 {
    private List<String> list;
    public static void main(String[] args) {
        new Day13().challenge2();
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

    public void challenge2() {
        List<Bus> busses = generateBusses(list.get(1));
        Collections.sort(busses);
        System.out.println(busses);
        Bus baselineBus = busses.get(0);
        int x=1;
        while (true) {
            long baseline = baselineBus.getId() * x;
            // now check if the other busses align
            for (int y=1; y<busses.size(): y++) {
                Bus nextBus = busses.get(y);
            }
            x++;
        }
    }

    private List<Bus> generateBusses(String busString) {
        String[] chars = busString.split(",");
        List<Bus> output = new ArrayList<Bus>();
        for (int x=0; x< chars.length; x++) {
            if (!chars[x].equals("x")) {
                output.add(new Bus (Integer.parseInt(chars[x]), x));
            }
        }
        return output;
    }

}
