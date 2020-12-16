package com.mozzdev;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private String field;
    private int startRange1, endRange1, startRange2, endRange2;
    private List<Integer> validValues;

    public Ticket(String input) {
        int indexOfColon = input.indexOf(":");
        this.field = input.substring(0, indexOfColon);
        String[] ranges = input.substring(indexOfColon+2).split(" ");
        String[] range1 = ranges[0].split("-");
        String[] range2 = ranges[2].split("-");
        this.startRange1 = Integer.parseInt(range1[0]);
        this.endRange1 = Integer.parseInt(range1[1]);
        this.startRange2 = Integer.parseInt(range2[0]);
        this.endRange2 = Integer.parseInt(range2[1]);
        initializeValidValues();
    }

    public Ticket(String field, int startRange1, int endRange1, int startRange2, int endRange2) {
        this.field = field;
        this.startRange1 = startRange1;
        this.endRange1 = endRange1;
        this.startRange2 = startRange2;
        this.endRange2 = endRange2;
        initializeValidValues();
    }

    private void initializeValidValues() {
        validValues = new ArrayList<>();
        for (int x=startRange1; x<=endRange1; x++) {
            validValues.add(x);
        }
        for (int x=startRange2; x<=endRange2; x++) {
            validValues.add(x);
        }
    }

    public String getField() {
        return field;
    }

    public int getStartRange1() {
        return startRange1;
    }

    public int getEndRange1() {
        return endRange1;
    }

    public int getStartRange2() {
        return startRange2;
    }

    public int getEndRange2() {
        return endRange2;
    }

    public List<Integer> getValidValues() {
        return validValues;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "field='" + field + '\'' +
                ", startRange1=" + startRange1 +
                ", endRange1=" + endRange1 +
                ", startRange2=" + startRange2 +
                ", endRange2=" + endRange2 +
                '}';
    }
}
