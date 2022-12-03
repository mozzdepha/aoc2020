package com.mozzdev.adventofcode.aoc2022;

import java.util.*;

public class Day3 extends AocDay2022 {


    private List<Rucksack> sacks;


    public static void main(String[] args) {
        new Day3().run();
    }

    public String getInputFilename() {
        return "inputs3.txt";
    }

    public void challenge1() {
        init();
        int totalPriorities = 0;
        for (Day3.Rucksack sack : sacks) {
            totalPriorities+= sack.getPriority();
        }
        System.out.println(totalPriorities);
    }

    public void challenge2() {
        init();
        int priorities = 0;
        Rucksack sack1, sack2, sack3;
        for (int x = 0; x < sacks.size(); x = x + 3) {
            sack1 = sacks.get(x);
            sack2 = sacks.get(x + 1);
            sack3 = sacks.get(x + 2);
            priorities+=sack1.getPriority(getCommonCharacter(sack1, sack2, sack3));
        }
        System.out.println(priorities);
    }

    private char getCommonCharacter(Rucksack sack1, Rucksack sack2, Rucksack sack3) {
        Map<Character, Integer> charCount = new HashMap<>();
        addChars(sack1, charCount);
        addChars(sack2, charCount);
        addChars(sack3, charCount);

        for (Object ch : Arrays.asList(charCount.keySet().toArray())) {
            if (charCount.get(ch) == 3) {
                return (char)ch;
            }
        }
        return '0';
    }

    private void addChars(Rucksack sack1, Map<Character, Integer> map) {
        Set uniqueChars = new HashSet<Character>();
        for (int x=0; x<sack1.getInput().length(); x++) {
            uniqueChars.add(sack1.getInput().charAt(x));
        }

        for (Object current : uniqueChars) {
            if (map.containsKey(current)) {
                map.put((Character)current, map.get(current)+1);
            } else {
                map.put((Character)current, 1);
            }
        }
     }

    private void init() {
        sacks = new ArrayList<Rucksack>();
        for (String numString: list) {
            sacks.add(new Rucksack(numString));
        }
    }

    private class Rucksack {
        private static String PRIORITIES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        private String input;
        private String first;
        private String second;
        public Rucksack(String numString) {
            int half = numString.length() % 2 == 0 ? numString.length()/2 : numString.length()/2 + 1;
            this.input = numString;
            this.first = numString.substring(0, half);
            this.second = numString.substring(half);

        }

        public String getInput() {
            return input;
        }
        public int getPriority() {
            // first each item in second, first its location in the first.
            return getPriority(findCommonItem());
        }

        public int getPriority(char character) {
            return PRIORITIES.indexOf(character)+1;
        }

        private char findCommonItem() {
            for (int x=0; x<second.length(); x++) {
                char candidate = second.charAt(x);
                if (first.indexOf(candidate) != -1) {
                    return candidate;
                }
            }
            return '0';
        }
    }


}
