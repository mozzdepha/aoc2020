package com.mozzdev;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SeatMap {

    int row;
    boolean[] seats;

    public SeatMap(int rowNumber) {
        row = rowNumber;
        seats = new boolean[8];
        Arrays.fill(seats, Boolean.FALSE);
    }
    public void registerSeat(int seat) {
        seats[seat] = true;
    }

    public boolean isFull() {
        return seats[0] && seats[1] && seats[2] && seats[3] && seats[4] && seats[5] && seats[6] && seats[7];
    }

    public String toString() {
        return "Row Number: " + row + ":"+
                (seats[0] ? 1 : 0) +
                (seats[1] ? 1 : 0) +
                (seats[2] ? 1 : 0) +
                (seats[3] ? 1 : 0) +
                (seats[4] ? 1 : 0) +
                (seats[5] ? 1 : 0) +
                (seats[6] ? 1 : 0) +
                (seats[7] ? 1 : 0);

    }


}
