package com.mozzdev.adventofcode.aoc2022;

import java.util.*;

public class Day4 extends AocDay2022 {


    private List<Row> rows;


    public static void main(String[] args) {
        new Day4().run();
    }

    public String getInputFilename() {
        return "inputs4.txt";
    }

    public void challenge1() {
        init();
        int totalOverlaps = 0;
        for (Day4.Row row : rows) {
            totalOverlaps+= row.getCompleteOverlapCount();
        }
        System.out.println(totalOverlaps);
    }

    public void challenge2() {
        init();
        int totalOverlaps = 0;
        for (Day4.Row row : rows) {
            totalOverlaps+= row.getPartialOverlapCount();
        }
        System.out.println(totalOverlaps);
    }



    private void init() {
        rows = new ArrayList<Row>();
        for (String numString: list) {
            rows.add(new Row(numString));
        }
    }

    private class Row {
        Pair first;
        Pair second;

        public Row (String input) {
            int split = input.indexOf(',');
            first = new Pair(input.substring(0, split));
            second = new Pair(input.substring(split+1));
        }

        public int getCompleteOverlapCount() {
            // return 1 if one pair overlaps the other
            if (first.isInside(second) || second.isInside(first)) {
                return 1;
            } else {
                return 0;
            }
        }

        public int getPartialOverlapCount() {
            // return 1 if one pair partially overlaps the other
            if (first.isOverlappedWith(second) || second.isOverlappedWith(first)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    private class Pair {
        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        int from;
        int to;

        public Pair(String input) {
            int split = input.indexOf('-');
            this.from = Integer.parseInt(input.substring(0, split));
            this.to = Integer.parseInt(input.substring(split+1));
        }

        public boolean isInside(Pair another) {
            return getFrom()>=another.getFrom() && getTo()<=another.getTo();
        }

        public boolean isOverlappedWith(Pair another) {
            return getFrom()<=another.getFrom() && another.getFrom()<=getTo();
        }
    }


}
