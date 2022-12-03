package com.mozzdev.adventofcode.aoc2022;

import java.util.ArrayList;
import java.util.List;

public class Day2 extends AocDay2022 {


    private List<Round> rounds;

    public static void main(String[] args) {
        new Day2().run();
    }

    public String getInputFilename() {
        return "inputs2.txt";
    }

    public void challenge1() {
        init();
        int totalScore = 0;
        int roundCount = 0;
        for (Round round : rounds) {
            totalScore += round.getScore1();
            roundCount++;
        }
        System.out.println(totalScore);
    }

    public void challenge2() {
        init();
        int totalScore = 0;
        int roundCount = 0;
        for (Round round : rounds) {
            totalScore += round.getScore2();
            roundCount++;
        }
        System.out.println(totalScore);
    }

    private void init() {
        rounds = new ArrayList<Round>();
        for (String numString: list) {
            rounds.add(new Round(numString));
        }
    }


    private class Round {

        private int score1 =0;
        private int score2 =0;

        private String source;

        public Round(String source) {
            this.source = source;
            switch (source) {
                case "A X" : this.score1 =4; this.score2 =3; break;
                case "A Y" : this.score1 =8; this.score2 =4; break;
                case "A Z" : this.score1 =3; this.score2 =8; break;
                case "B X" : this.score1 =1; this.score2 =1; break;
                case "B Y" : this.score1 =5; this.score2 =5; break;
                case "B Z" : this.score1 =9; this.score2 =9; break;
                case "C X" : this.score1 =7; this.score2 =2; break;
                case "C Y" : this.score1 =2; this.score2 =6; break;
                case "C Z" : this.score1 =6; this.score2 =7; break;
                default: System.out.println("unknown char");
            }
        }


        public int getScore1() {
            return score1;
        }
        public int getScore2() {
            return score2;
        }
    }
}
