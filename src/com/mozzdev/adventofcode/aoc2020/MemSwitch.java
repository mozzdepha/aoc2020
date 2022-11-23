package com.mozzdev.adventofcode.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemSwitch {
    private String mask;
    private int position;
    private long value;

    @Override
    public String toString() {
        return "MemSwitch{" +
                "mask='" + mask + '\'' +
                ", position=" + position +
                ", value=" + value +
                '}';
    }

    public String getMask() {
        return mask;
    }

    public int getPosition() {
        return position;
    }

    public long getValue() {
        return value;
    }

    public MemSwitch(String mask, int position, long value) {
        this.mask = mask;
        this.position = position;
        this.value = value;
    }

    public long evaluate() {
        char[] bits = ("0000" + Long.toBinaryString( getValue() | 0x100000000L ).substring(1)).toCharArray();
        for (int x=0; x<getMask().length(); x++) {
            switch (getMask().charAt(x)) {
                case 'X':
                    // do nothing
                    break;
                case '0':
                    bits[x] = '0';
                    break;
                case '1':
                    bits[x] = '1';
                    break;
            }
        }
        long result = Long.parseLong(new String(bits), 2);
        return result;
    }


    public List<Long> evaluateV2(Map<Long, Long> results) {
        char[] bits = ("0000" + Long.toBinaryString( (long)getPosition() | 0x100000000L ).substring(1)).toCharArray();
        for (int x=0; x<getMask().length(); x++) {
            switch (getMask().charAt(x)) {
                case 'X':
                    bits[x] = 'X';
                    break;
                case '0':
                    // do nothing
                    break;
                case '1':
                    bits[x] = '1';
                    break;
            }
            System.out.println("Done " + x + " of " + getMask().length());
        }

        List<String> locations = new ArrayList<String>();
        locations.add(new String(bits));
        for (int x=0; x<bits.length;x++) {
            // for each 'X', replace each entry in locations with 2 option, a 1 and a 0 replacing the X
            if (bits[x] == 'X') {
                List<String> tmpLocations = new ArrayList<String>();
                for (String current : locations) {
                    char[] swapper = current.toCharArray();
                    swapper[x] ='0';
                    tmpLocations.add(new String(swapper));
                    swapper[x] ='1';
                    tmpLocations.add(new String(swapper));
                    locations = tmpLocations;
                }
            }
        }
        List<Long> locationLongs = new ArrayList<Long>();
        for (String locationString: locations) {
            locationLongs.add(Long.parseLong(new String(locationString), 2));
        }
        return locationLongs;
    }

}
