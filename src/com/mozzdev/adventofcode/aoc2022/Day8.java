package com.mozzdev.adventofcode.aoc2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8 extends AocDay2022 {

    private int[][] treeMap;

    public static void main(String[] args) {
        new Day8().run();
    }

    public String getInputFilename() {
        return "inputs8.txt";
    }

    public void challenge1() {
        init();
        int visible = 0;
        for (int x=0; x<treeMap.length; x++) {
            for (int y=0; y<treeMap[x].length; y++) {
                if (isVisible(x, y)) {
                    visible++;
                }
            }
        }
        System.out.println(visible);
    }

    private boolean isVisible(int xTarget, int yTarget) {
        // check if on perimeter
        if (xTarget==0 || xTarget==treeMap.length-1 || yTarget==0 || yTarget==treeMap[0].length-1) {
            return true;
        };
        // now check if taller than all other trees in each direction
        int target = treeMap[xTarget][yTarget];

        // check if max in x axis
        List sorted = new ArrayList<Integer>();
        for (int x=0; x<xTarget; x++) {
            sorted.add(treeMap[x][yTarget]);
        }
        Collections.sort(sorted);
        if (target > (int)sorted.get(sorted.size()-1) ) {
            return true;
        }

        sorted = new ArrayList<Integer>();
        for (int x=xTarget+1; x<treeMap.length; x++) {
            sorted.add(treeMap[x][yTarget]);
        }
        Collections.sort(sorted);
        if (target > (int)sorted.get(sorted.size()-1) ) {
            return true;
        }

        // check if max in y axis
        sorted = new ArrayList<Integer>();
        for (int y=0; y<yTarget; y++) {
            sorted.add(treeMap[xTarget][y]);
        }
        Collections.sort(sorted);
        if (target > (int)sorted.get(sorted.size()-1) ) {
            return true;
        }

        sorted = new ArrayList<Integer>();
        for (int y=yTarget+1; y<treeMap[0].length; y++) {
            sorted.add(treeMap[xTarget][y]);
        }
        Collections.sort(sorted);
        if (target > (int)sorted.get(sorted.size()-1) ) {
            return true;
        }

        // all tests ok - this is not visible
        return false;
    }

    public void challenge2() {
        init();
        int maxScenicScore = 0;
        for (int x=0; x<treeMap.length; x++) {
            for (int y=0; y<treeMap[x].length; y++) {
                int scenicScore = getScenicScore(x, y);
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }
        System.out.println(maxScenicScore);
    }

    private int getScenicScore(int xTarget, int yTarget) {
        if (xTarget==0 || xTarget==treeMap.length-1 || yTarget==0 || yTarget==treeMap[0].length-1) {
            // on the border = zero multiplier
            return 0;
        };
        // now check if taller than all other trees in each direction
        int target = treeMap[xTarget][yTarget];

        // check if max in x axis
        List sorted = new ArrayList<Integer>();
        for (int x=0; x<xTarget; x++) {
            sorted.add(treeMap[x][yTarget]);
        }
        Collections.reverse(sorted);
        int scenicLeft=getScenicScore(target, sorted);

        sorted = new ArrayList<Integer>();
        for (int x=xTarget+1; x<treeMap.length; x++) {
            sorted.add(treeMap[x][yTarget]);
        }
        int scenicRight=getScenicScore(target, sorted);

        sorted = new ArrayList<Integer>();
        for (int y=0; y<yTarget; y++) {
            sorted.add(treeMap[xTarget][y]);
        }
        Collections.reverse(sorted);;
        int scenicUp = getScenicScore(target, sorted);

        sorted = new ArrayList<Integer>();
        for (int y=yTarget+1; y<treeMap[0].length; y++) {
            sorted.add(treeMap[xTarget][y]);
        }
        int scenicDown = getScenicScore(target, sorted);

        return scenicLeft * scenicRight * scenicUp * scenicDown;
    }

    private int getScenicScore(int target, List list) {
        for (int x=0; x< list.size(); x++) {
            if ((int)list.get(x) >= target) {
                return x+1;
            }
        }
        return list.size();
    }

    private void init() {
        int x=0;
        treeMap = new int[list.size()][list.get(0).length()];
        for (String inputRow: list) {
            for (int y=0; y<inputRow.length(); y++) {
                treeMap[x][y] = Integer.parseInt(inputRow.substring(y, y+1));
            }
            x++;
        }
    }

}
