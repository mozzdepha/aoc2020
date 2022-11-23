package com.mozzdev.adventofcode.aoc2020;

import java.util.*;

public class Day4 extends AocDay2020 {
    private char[][] map;

    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs4.txt";
    }

    public void challenge1() {
        List<Map> maps = new ArrayList<>();
        Map currentMap = new HashMap();
        int validMaps = 0;
        int i=0;
        String currentRow;
        while(i<list.size()) {
            currentRow = list.get(i);
            if (i==list.size()-1) {
                System.out.println(currentRow);
            }
            if(currentRow.equals("")) {
                if(isValid2(currentMap)) {
                    validMaps++;
                }
                maps.add(currentMap);
                currentMap = new HashMap();
            } else{
                String[] words = currentRow.split("\\s+");
                for(int x=0; x<words.length; x++) {
                    String[] keyValues = words[x].split(":");
                    currentMap.put(keyValues[0], keyValues[1]);
                }
            }
            i++;
        }
        if (isValid2(currentMap)) {
            validMaps++;
        }

        System.out.println("Found valid passports:" + validMaps);
    }

    private boolean isValid(Map map) {
        String[] keys = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        for (int x=0; x<keys.length; x++) {
            if (!map.containsKey(keys[x])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid2(Map map) {
        return isValid(map) && isInRange(map);
    }

    private boolean isInRange(Map<String, String> map) {
        try {
            int byr = Integer.parseInt(map.get("byr"));
            if (byr < 1920 || byr > 2002) {
                return false;
            }
            int iyr = Integer.parseInt(map.get("iyr"));
            if (iyr < 2010 || iyr > 2020) {
                return false;
            }
            int eyr = Integer.parseInt(map.get("eyr"));
            if (eyr < 2020 || eyr > 2030) {
                return false;
            }
            String hgt = map.get("hgt");
            int hgtInt = Integer.parseInt(hgt.substring(0, hgt.length()-2));
            if (hgt.endsWith("cm")) {
                if (hgtInt < 150 || hgtInt > 193) {
                    return false;
                }
            } else if (hgt.endsWith("in")) {
                if (hgtInt < 59 || hgtInt > 76) {
                    return false;
                }
            } else {
                return false;
            }
            if (!map.get("hcl").matches("#[0-9a-f]{6}")) {
                return false;
            }
            if (!map.get("ecl").matches("(amb|blu|brn|gry|hzl|oth|grn)")) {
                return false;
            }
            if (!map.get("pid").matches("\\d{9}")) {
                return false;
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }


}
