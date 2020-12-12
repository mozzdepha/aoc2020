package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day11 {
    private int depth;
    private long validPerms=1l;
    private List<String> list;

    public static void main(String[] args) {
        new Day11().challenge2();
    }

    public Day11() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<String>();
        File file = new File("inputs11.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }


    public void challenge1() {
        char[][] inSeatMap = convertToArrays(list);
        String previous = dump(inSeatMap);
        System.out.println(previous);
        String current = previous;
        while (true) {
            inSeatMap = applyRules(inSeatMap);
            String newMap = dump(inSeatMap);
            System.out.println(newMap);
            if (newMap.equals(previous)) {
                // no more movements - end
                System.out.println("Last map equals this map. Total seats: " + (newMap.length() - newMap.replace("#", "").length()));
                System.exit(0);
            } else {
                // next iteration
                previous = newMap;
            }
        }
    }

    public void challenge2() {
        char[][] inSeatMap = convertToArrays(list);
        String previous = dump(inSeatMap);
        System.out.println(previous);
        String current = previous;
        int round=1;
        while (true) {
            inSeatMap = applyRules2(inSeatMap);
            String newMap = dump(inSeatMap);
            System.out.println(newMap);
            if (newMap.equals(previous)) {
                // no more movements - end
                System.out.println("Last map equals this map. Total seats: " + (newMap.length() - newMap.replace("#", "").length()));
                System.exit(0);
            } else {
                // next iteration
                previous = newMap;
            }
            round++;
        }
    }

    private char[][] applyRules(char[][] inSeatMap) {
        char[][] outSeatMap = new char[list.size()][list.get(0).length()];
        for (int x=0; x<inSeatMap.length; x++) {
            for (int y=0; y<inSeatMap[x].length; y++) {
                char currentPosition= inSeatMap[x][y];
                if (currentPosition == '#' && shouldBeVacated(inSeatMap, x, y)) {
                    // seat is filled.  Check if it should be vacated
                    outSeatMap[x][y] = 'L';
                } else if (currentPosition == 'L' && canBeFilled(inSeatMap, x, y)) {
                    outSeatMap[x][y] = '#';
                } else {
                    // not a seat - do nothing
                    outSeatMap[x][y] = inSeatMap[x][y];
                }
            }
        }
        return outSeatMap;
    }

    private char[][] applyRules2(char[][] inSeatMap) {
        char[][] outSeatMap = new char[list.size()][list.get(0).length()];
        for (int x=0; x<inSeatMap.length; x++) {
            for (int y=0; y<inSeatMap[x].length; y++) {
                char currentPosition= inSeatMap[x][y];
                if (currentPosition == '#' && shouldBeVacated2(inSeatMap, x, y)) {
                    // seat is filled.  Check if it should be vacated
                    outSeatMap[x][y] = 'L';
                } else if (currentPosition == 'L' && canBeFilled2(inSeatMap, x, y)) {
                    outSeatMap[x][y] = '#';
                } else {
                    // not a seat - do nothing
                    outSeatMap[x][y] = inSeatMap[x][y];
                }
            }
        }
        return outSeatMap;
    }
    private boolean canBeFilled(char[][] seatMap, int myX, int myY) {
        int xStart = Math.max(0, myX-1);
        int xEnd = xStart + ((myX == 0 || myX == seatMap.length-1) ? 2 : 3);
        int yStart = Math.max(0,myY-1);
        int yEnd = yStart + ((myY == 0 || myY == seatMap[0].length-1) ? 2 :3);

        for (int x=xStart; x<xEnd; x++) {
            for (int y=yStart; y<yEnd; y++) {
                if (seatMap[x][y] == '#') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean shouldBeVacated(char[][] seatMap, int myX, int myY) {
        // return true if there are at least 4 adjacent seats filled
        int filledCount=0;
        int xStart = Math.max(0, myX-1);
        int xEnd = xStart + ((myX == 0 || myX == seatMap.length-1) ? 2 : 3);
        int yStart = Math.max(0,myY-1);
        int yEnd = yStart + ((myY == 0 || myY == seatMap[0].length-1) ? 2 :3);

        for (int x=xStart; x<xEnd; x++) {
            for (int y=yStart; y<yEnd; y++) {
                if (seatMap[x][y] == '#') {
                    filledCount++;
                }
            }
        }
        return filledCount>=5;
    }

    private boolean canBeFilled2(char[][] seatMap, int myX, int myY) {
        return getNumberOfVisiblyOccupiedSeats(seatMap, myX, myY) == 0;
    }

    private boolean shouldBeVacated2(char[][] seatMap, int myX, int myY) {
        return getNumberOfVisiblyOccupiedSeats(seatMap, myX, myY) >= 5;
    }

    private int getNumberOfVisiblyOccupiedSeats(char[][] seatMap, int myX, int myY) {
        int seats = 0;
        // check up
        if (myX>0) {
            for (int x=myX-1; x>=0; x--) {
                if (seatMap[x][myY] == '#') {
                    seats++;
                    break;
                } else if (seatMap[x][myY] == 'L') {
                    break;
                }
            }
        }
        // check down
        if (myX<seatMap.length) {
            for (int x=myX+1; x<seatMap.length; x++) {
                if (seatMap[x][myY] == '#') {
                    seats++;
                    break;
                } else if (seatMap[x][myY] == 'L') {
                    break;
                }
            }
        }
        // check left
        if (myY>0) {
            for (int y=myY-1; y>=0; y--) {
                if (seatMap[myX][y] == '#') {
                    seats++;
                    break;
                } else if (seatMap[myX][y] == 'L') {
                    break;
                }
            }
        }
        // check right
        if (myY<seatMap[0].length) {
            for (int y=myY+1; y<seatMap[0].length; y++) {
                if (seatMap[myX][y] == '#') {
                    seats++;
                    break;
                } else if (seatMap[myX][y] == 'L') {
                    break;
                }
            }
        }

        // check diagonals
        seats += getSeatCount(getDiagonal(seatMap, myX, myY, -1, 1));
        seats += getSeatCount(getDiagonal(seatMap, myX, myY, 1, 1));
        seats += getSeatCount(getDiagonal(seatMap, myX, myY, -1, -1));
        seats += getSeatCount(getDiagonal(seatMap, myX, myY, 1, -1));

        return seats;
    }

    private int getSeatCount(List<Character> input) {
        for (int x=0; x<input.size(); x++) {
            if (input.get(x) == '#') {
                return 1;
            } else if (input.get(x) == 'L') {
                return 0;
            }
        }
        return 0;
    }

    private List<Character> getDiagonal(char[][] seatMap, int myX, int myY, int xIncrement, int yIncrement) {
        List<Character> result = new ArrayList<Character>();
        int xPointer = myX;
        int yPointer = myY;
        while (true) {
            try {
                xPointer += xIncrement;
                yPointer += yIncrement;
                result.add(seatMap[xPointer][yPointer]);
            } catch(Exception e) {
                break;
            }
        }
        return result;
    }

    private char[][] convertToArrays(List<String> input) {
        char[][] output = new char[input.size()][input.get(0).length()];
        for (int x=0; x<input.size(); x++) {
            for (int y=0; y<input.get(x).length();y++) {
                output[x][y] = input.get(x).charAt(y);
            }
        }
        return output;
    }

    private String dump(char[][] seatMap) {
        StringBuffer sb = new StringBuffer();
        for (int x=0; x<seatMap.length; x++) {
            for (int y=0; y<seatMap[x].length;y++) {
                sb.append(seatMap[x][y]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

}
