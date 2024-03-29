package com.mozzdev.adventofcode.aoc2020;

import java.util.HashMap;
import java.util.Map;

public class Day5 extends AocDay2020 {
    private char[][] map;

    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs5.txt";
    }

    public void challenge1() {
        int x=0;
        long maxSeatId=0;
        Map<Integer, SeatMap> seats = new HashMap<Integer, SeatMap>();
        while (x<list.size()) {
            int minRow =0;
            int maxRow = 127;
            char[] chars = list.get(x).toCharArray();
            char[] binaryChars = new char[7];

            for(int y=0; y<7; y++) {
                if (chars[y]=='F') {
                    maxRow = minRow + (maxRow-minRow)/2;
                    binaryChars[y]='0';
                } else if (chars[y]=='B') {
                    minRow = ((maxRow-minRow)/2) +1;
                    binaryChars[y]='1';
                }
            }
//            System.out.println("" + list.get(x) + " minRow:" + minRow + " maxRow: " + maxRow);

            int rowNumber =Integer.parseInt(String.valueOf(binaryChars), 2);
//            System.out.println("Old school:" + minRow + "-" + maxRow + " binary: " + rowNumber);

            char[] seatChars = new char[3];
            for (int z=0; z<3; z++) {
                if (chars[7+z]=='L') {
                    seatChars[z] = '0';
                } else {
                    seatChars[z] = '1';
                }
            }
            int seatNumber = Integer.parseInt(String.valueOf(seatChars), 2);
            long seatValue = (rowNumber*8) + seatNumber;
            if (seatValue > maxSeatId) {
                maxSeatId = seatValue;
            }

            Integer rowInteger = rowNumber;
            if (!seats.containsKey(rowInteger)) {
                seats.put(rowInteger, new SeatMap(rowNumber));
            }
            ((SeatMap)seats.get(rowInteger)).registerSeat(seatNumber);

            x++;

        }
        System.out.println("Max Seat Id:" + maxSeatId);

        int a=0;
        while (a<seats.size()) {
            SeatMap m = seats.get(a);
            if (m == null) {
                System.out.println("No data for row " + a);
            } else if (!seats.get(a).isFull()) {
                System.out.println("Row " + a + " is not full: " + seats.get(a));
                System.out.println((8*85)+5);
            }
            a++;
        }

    }

}
