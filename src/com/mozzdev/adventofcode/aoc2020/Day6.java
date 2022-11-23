package com.mozzdev.adventofcode.aoc2020;

import java.util.*;

public class Day6 extends AocDay2020 {
    private char[][] map;

    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs6.txt";
    }

    public void challenge1() {
        int x=0;
        int answerCount=0;
        Map<Character, Integer> yesses = new HashMap<Character, Integer>();
        int sizeOfGroup=0;
        while (x<list.size()) {
            if (list.get(x).equals("")) {
                Iterator<Character> keysIt = yesses.keySet().iterator();
                while(keysIt.hasNext()) {
                    int responseCount=yesses.get(keysIt.next());
                    if (responseCount == sizeOfGroup) {
                        answerCount+=1;
                    }
                }
                yesses = new HashMap<Character, Integer>();
                sizeOfGroup=0;
            } else {
                char[] answers = list.get(x).toCharArray();
                for (int y = 0; y < answers.length; y++) {
                    if(yesses.containsKey(answers[y])) {
                        int currentAnswer = yesses.get(answers[y]);
                        yesses.put(answers[y], currentAnswer +1);

                    } else{
                        yesses.put(answers[y], 1);
                    }
                }
                sizeOfGroup++;
            }
            x++;
        }
        //add last group
        Iterator<Character> keysIt = yesses.keySet().iterator();
        while(keysIt.hasNext()) {
            int responseCount=yesses.get(keysIt.next());
            if (responseCount == sizeOfGroup) {
                answerCount+=1;
            }
        }
        System.out.println("Answers: " + answerCount);

    }

}
