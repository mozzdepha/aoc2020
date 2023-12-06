package com.mozzdev.adventofcode.aoc2023;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Day6 extends AocDay2023 {

    public static void main(String[] args) {
        new Day6().run();
    }


    public String getInputFilename() {
        return "inputs6.txt";
    }

    private List<Double> times, distances;
    private String longTimes, longDistances;

    public void challenge1() {
        init();
        double total = 1;

        for (int x=0; x<times.size(); x++) {
            double waysToWin = waysToWin((double)times.get(x), (double)distances.get(x));
            total = total * waysToWin;
        }
        System.out.println(total);
    }

    private double waysToWin(Double time, Double currentRecord) {
        double count =0;
        for (int x=1; x<time-1; x++) {
            double distanceTravelled = (time-x) * x;
            if (distanceTravelled > currentRecord) {
                count++;
            }
        }
        return count;

    }

    private void init() {
        String[] timeS = list.get(0).split(":")[1].trim().split(" ");
        times = new ArrayList<>();
        StringBuffer sb1 = new StringBuffer();
        for (int x=0; x<timeS.length; x++) {
            if (!timeS[x].isEmpty()) {
                times.add(Double.parseDouble(timeS[x]));
                sb1.append(timeS[x]);
            }
        }
        longTimes = sb1.toString();

        String[] distanceS = list.get(1).split(":")[1].trim().split(" ");
        distances = new ArrayList<>();
        StringBuffer sb2 = new StringBuffer();
        for (int x=0; x<distanceS.length; x++) {
            if (!distanceS[x].isEmpty()) {
                distances.add(Double.parseDouble(distanceS[x]));
                sb2.append(distanceS[x]);
            }
        }
        longDistances = sb2.toString();
    }

    public void challenge2() {
        System.out.println(NumberFormat.getInstance().format(waysToWin(Double.parseDouble(longTimes), Double.parseDouble(longDistances))));
    }
}
