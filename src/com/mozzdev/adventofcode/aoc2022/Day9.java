package com.mozzdev.adventofcode.aoc2022;

import java.util.*;

public class Day9 extends AocDay2022 {
    private List<Step> steps;
    public static void main(String[] args) {
        new Day9().run();
    }

    private static final char RIGHT = 'R';
    private static final char LEFT = 'L';
    private static final char UP = 'U';
    private static final char DOWN = 'D';

    public String getInputFilename() {
        return "inputs9.txt";
    }

    public void challenge1() {
        init();
        int headX = 0;
        int headY = 0;
        Knot tail = new Knot();
        for (Step step : steps) {
            if (RIGHT == step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    headX++;
                    tail.followHead(headX, headY);
                }
            }
            if (LEFT == step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    headX--;
                    tail.followHead(headX, headY);
                }
            }
            if (UP==step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    headY++;
                    tail.followHead(headX, headY);
                }
            }
            if (DOWN==step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    headY--;
                    tail.followHead(headX, headY);
                }
            }
        }
        System.out.println(tail.getUniqueLocations().size());
    }

    public void challenge2() {
        init();
        int headX = 0;
        int headY = 0;
        Knot head = new Knot();
        List<Knot> knots = new ArrayList<Knot>();
        knots.add(head);
        for (int x=0; x<9; x++) {
            knots.add(new Knot());
        }
        for (Step step : steps) {
            if (RIGHT == step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    head.currentX++;
                    followHead(knots);
                }
            }
            if (LEFT == step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    head.currentX--;
                    followHead(knots);
                }
            }
            if (UP==step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    head.currentY++;
                    followHead(knots);
                }
            }
            if (DOWN==step.getDirection()) {
                for (int x=0; x<step.getDistance(); x++) {
                    head.currentY--;
                    followHead(knots);
                }
            }
        }
        System.out.println(knots.get(knots.size()-1).getUniqueLocations().size());
    }

    private void followHead(List<Knot> knots) {
        for(int i = 1; i< knots.size(); i++) {
            knots.get(i).followHead(knots.get(i-1));
        }
    }

    private void init() {
        steps = new ArrayList<Step>();
        for (String inputRow: list) {
            steps.add(new Step(inputRow));
        }
    }

    private class Step {
        private char direction;
        private int distance;

        public Step(String input) {
            direction = input.charAt(0);
            distance = Integer.parseInt(input.substring(2));
        }

        public char getDirection() {
            return direction;
        }

        public int getDistance() {
            return distance;
        }
    }

    private class Knot {
        private List visited;
        private int currentX;
        private int currentY;

        public Knot() {
            visited = new ArrayList();
            currentX=0;
            currentY=0;
            logLocation();
        }
        public void logLocation() {
            visited.add("" + currentX + "," + currentY);
        }

        public Set getUniqueLocations() {
            Set unique = new HashSet();
            unique.addAll(visited);
            return unique;
        }

        public void followHead(Knot knot) {
            followHead(knot.currentX, knot.currentY);
        }

        public void followHead(int x, int y) {
            if (!isWtihinOneStep(x, y)) {
                // need to move the tail
                if (x!=currentX) {
                    // move horizontally
                    if (x>currentX) {
                        currentX++;
                    } else {
                        currentX--;
                    }
                }
                if (y!=currentY) {
                    // move vertically
                    if (y>currentY) {
                        currentY++;
                    } else {
                        currentY--;
                    }
                }
                logLocation();
            }
        }

        private boolean isWithinOneStep(Knot knot) {
            return isWtihinOneStep(knot.currentX, knot.currentY);
        }

        private boolean isWtihinOneStep(int headX, int headY) {
            return Math.abs(headX-currentX) <=1 && Math.abs(headY-currentY) <=1;
        }

    }
}
