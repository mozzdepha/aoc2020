package com.mozzdev.adventofcode.aoc2023;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 extends AocDay2023 {

    public static void main(String[] args) {
        new Day5().run();
    }

    public String getInputFilename() {
        return "inputs5.txt";
    }

    public void challenge1() {
        initData();
        double lowestLocation = 100000000000d;
        for (Double seed: seeds) {
            // do something
            double location = getLocation(seed);
            lowestLocation=Math.min(lowestLocation, location);
        }
        System.out.println(NumberFormat.getInstance().format(lowestLocation));
    }

    private double getLocation(Double seed) {
        Mapping m = mappings.get("seed");
        double input=seed;
        while (!m.destination.equals("location")) {
            input = m.getNextDestination(input);
            String nextDest = m.destination;
            m=mappings.get(m.destination);
            if (m==null) {
                System.out.println("top");
            }
        }
        return m.getNextDestination(input);
    }


    public void challenge2() {
        double lowestLocation = 100000000000d;
        for (int x=0; x<seeds.size()-1; x=x+2) {
            Double seed = seeds.get(x);
            Double range = seeds.get(x+1);
            for (double y=0; y<range; y++) {
                // do something
                double location = getLocation(seed+y);
                lowestLocation = Math.min(lowestLocation, location);
            }
        }
        System.out.println(NumberFormat.getInstance().format(lowestLocation));
    }

    private List<Double> seeds;
    private Map<String, Mapping> mappings;

    private class Mapping {
        private String source, destination;


        private class MapEntry {
            double destinationRange, sourceRange, rangeLength;

            public MapEntry(double destinationRange, double sourceRange, double rangeLength) {
                this.destinationRange = destinationRange;
                this.sourceRange = sourceRange;
                this.rangeLength = rangeLength;
            }
        }

        private List<MapEntry> mapEntries;

        public Mapping() {
            mapEntries = new ArrayList<>();
        }

        public void add(double destinationRange, double sourceRange, double rangeLength) {
            mapEntries.add(new MapEntry(destinationRange, sourceRange, rangeLength));
        }

        public double getNextDestination(double source) {
            for (MapEntry me: mapEntries) {
                if (inRange(source, me)) {
                    return me.destinationRange + (source - me.sourceRange);
                }
            }
            // no explit mapping - return source
            return source;
        }

        private boolean inRange(double source, MapEntry me) {
            return source >= me.sourceRange && source <= (me.sourceRange + me.rangeLength);
        }
    }

    private Mapping current;
    private void initData() {
        mappings = new HashMap<>();
        for (String s : list) {
            if (s.startsWith("seeds:")) {
                initSeeds(s);
            } else if (s.contains("-to-")) {
                String source = s.substring(0, s.indexOf("-to-"));
                String destination = s.substring(s.indexOf("-to-") + 4, s.indexOf(" map"));
                current = new Mapping();
                current.source = source;
                current.destination = destination;
            } else if (s.isEmpty()) {
                // do nothing - next line should be a new Mapping
                if (current != null) {
                    mappings.put(current.source, current);
                }
            } else {
                String[] tokens = s.split(" ");
                current.add(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
            }
        }
    }

    private void initSeeds(String s) {
        seeds = new ArrayList<>();
        String[] tokens = s.split(":")[1].trim().split(" ");
        for (int x=0; x<tokens.length; x++) {
            seeds.add(Double.parseDouble(tokens[x]));
        }
    }
}
