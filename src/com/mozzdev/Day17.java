package com.mozzdev;

import java.util.*;

public class Day17 extends AocDay {

    private static int ORIGIN=50;

    Map<String, Boolean> cMap;
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs17.txt";
    }

    public void challenge1() {
        initializeCoordinateMap();
        for (int x=1; x<7; x++) {
            System.out.println("Starting round " + x);
            checkAndApplyRules(false);
        }
        System.out.println("Active cubes: " + getActiveCubes());
    }

    public void challenge2() {
        initializeCoordinateMap();
        for (int x=1; x<7; x++) {
            System.out.println("Starting round " + x);
            checkAndApplyRules(true);
        }
        System.out.println("Active cubes: " + getActiveCubes());
    }

    private void initializeCoordinateMap() {
        cMap = new HashMap<String, Boolean>();
        for (int x=0; x<list.size();x++) {
            char[] inputChars = list.get(x).toCharArray();
            for (int y=0; y<inputChars.length; y++) {
                cMap.put(getKey((ORIGIN/2)+x, (ORIGIN/2)+y, (ORIGIN/2), (ORIGIN/2)), inputChars[y] == '#');
            }
        }
    }

    private void checkAndApplyRules(boolean includeW) {
        List<String> keysToApplyTrue = new ArrayList<String>();
        List<String> keysToApplyFalse = new ArrayList<String>();
        int wMin = includeW ? (ORIGIN/2)-20 : (ORIGIN/2);
        int wMax = includeW ? (ORIGIN/2)+20 : (ORIGIN/2)+1;
        for (int w=wMin; w<wMax; w++) {
            for (int x=(ORIGIN/2)-20; x< (ORIGIN/2)+20; x++) {
                for (int y=(ORIGIN/2)-20; y<(ORIGIN/2)+20; y++) {
                    for (int z=(ORIGIN/2)-20; z<(ORIGIN/2)+20; z++) {
                        String key = getKey(x, y, z, w);
                        if (cMap.getOrDefault(key, false)) {
                            // currently active
                            if (applyActiveRule(x, y, z, w)) {
                                keysToApplyFalse.add(key);
                            }
                        } else {
                            // currently inactive
                            if (applyInactiveRule(x, y, z, w)) {
                                keysToApplyTrue.add(key);
                            };
                        }
                    }
                }
            }
        }
        for (String key: keysToApplyFalse) {
            cMap.put(key, false);
        }
        for (String key: keysToApplyTrue) {
            cMap.put(key, true);
        }
    }

    private void dumpOutput() {
        StringBuffer sb = new StringBuffer();
        for (int z=(ORIGIN/2)-20; z<(ORIGIN/2)+20; z++) {
            sb.append("z" + z + "\n");
            for (int x=(ORIGIN/2)-20; x< (ORIGIN/2)+20; x++) {
                for (int y=(ORIGIN/2)-20; y<(ORIGIN/2)+20; y++) {
                    String key = getKey(x, y, z);
                    if (cMap.getOrDefault(key, false)) {
                        sb.append("#");
                    } else {
                        sb.append(".");
                    }
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     */
    private boolean applyActiveRule(int originX, int originY, int originZ) {
        return applyActiveRule(originX, originY, originZ, ORIGIN/2);
    }

    private boolean applyActiveRule(int originX, int originY, int originZ, int originW) {
        int activeNeighbours = getActiveNeighbours(originX, originY, originZ, originW);
        // query 3 and 4 as the above logic will also include origin
        return activeNeighbours < 3 || activeNeighbours > 4;
    }


    private int getActiveNeighbours(int originX, int originY, int originZ, int originW) {
        int activeNeighbours = 0;
        for (int x=originX-1; x<originX+2; x++) {
            for (int y=originY-1; y<originY+2; y++) {
                for (int z=originZ-1; z<originZ+2; z++) {
                    for (int w=originW-1; w<originW+2; w++) {
                        boolean result = cMap.getOrDefault(getKey(x, y, z, w), false);
                        if (result) {
                            activeNeighbours++;
                        }
                    }
                }
            }
        }
        return activeNeighbours;
    }

    /**
     * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
     */
    private boolean applyInactiveRule(int originX, int originY, int originZ) {
        return applyInactiveRule(originX, originY, originZ, ORIGIN/2);
    }

    /**
     * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
     */
    private boolean applyInactiveRule(int originX, int originY, int originZ, int originW) {
        int activeNeighbours = getActiveNeighbours(originX, originY, originZ, originW);
        // query 3 and 4 as the above logic will also include origin
        return activeNeighbours ==3;
    }

    private int getActiveCubes() {
        int count = 0;
        for (Boolean bool: cMap.values()) {
            if(bool) {
                count++;
            }
        }
        return count;
    }

    private String getKey(int x, int y, int z) {
        return getKey(x, y, z, (ORIGIN/2));
    }

    private String getKey(int x, int y, int z, int w) {
        return "" + x + "-" + y + "-" + z + "-" + w;
    }
}
