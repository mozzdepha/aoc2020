package com.mozzdev.adventofcode.aoc2020;

public class Day3 extends AocDay2020 {
    private char[][] map;


    public static void main(String[] args) {
        new Day3().run();
    }

    public String getInputFilename() {
        return "inputs3.txt";
    }

    private void initializeMap() {
        int depth = list.size();
        int width = list.get(0).length();
        char[][] chars = new char[depth][width];

        for (int x=0; x<depth; x++) {
            String currentRow = list.get(x);
            for (int y=0; y<width; y++) {
                chars[x][y] = currentRow.charAt(y);
            }
        }
        map = chars;
    }


    public void challenge1() {
        initializeMap();
        int treeCount=0;
        int xPosition=0;
        int yPosition=0;
        int widthOfRow = list.get(0).length();
        while(yPosition<list.size()) {
            // for each row in the list
            if (map[yPosition][xPosition] == '#' || map[yPosition][xPosition] == 'X') {
                treeCount++;
            }
            xPosition+=3;
            yPosition++;

            if(xPosition==31) {
                xPosition=0;
            }
            if(xPosition==32) {
                xPosition = 1;
            }
            if (xPosition==33) {
                xPosition =2;
            }

        }
        System.out.println("" + treeCount + " trees encountered");

    }

    public void challenge2() {
        initializeMap();
        long pass1 = treeCount(1, 1);
        long pass2 = treeCount(3, 1);
        long pass3 = treeCount(5, 1);
        long pass4 = treeCount(7, 1);
        long pass5 = treeCount(1, 2);

        System.out.println("Answer: " + pass1*pass2*pass3*pass4*pass5);
    }

    private int treeCount(int rightCount, int downCount) {
        int treeCount=0;
        int xPosition=0;
        int yPosition=0;
        int widthOfRow = list.get(0).length();
        while(yPosition<list.size()) {
            // for each row in the list
            if (map[yPosition][xPosition] == '#' || map[yPosition][xPosition] == 'X') {
                treeCount++;
            }
            xPosition+=rightCount;
            yPosition+=downCount;
            if (xPosition>=31) {
                xPosition=xPosition-31;
            }

        }
        System.out.println("" + treeCount + " trees encountered");
        return treeCount;
    }



}
