package com.mozzdev.adventofcode.aoc2020;

public class Day12 extends AocDay2020 {
    private int wpN;
    private int wpE;
    private char heading;
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs12.txt";
    }

    public void challenge1() {
        int x=0;
        long scoreN = 0l;
        long scoreE = 0l;
        heading = 'E';

        while (x<list.size()) {
            char direction = list.get(x).charAt(0);
            int units = Integer.parseInt(list.get(x).substring(1, list.get(x).length()));
            switch (direction) {
                case 'N' :
                    scoreN+=units;
                    break;
                case 'S' :
                    scoreN-=units;
                    break;
                case 'E' :
                    scoreE+=units;
                    break;
                case 'W' :
                    scoreE-=units;
                    break;
                case 'L' :
                    setNewHeading(direction, units);
                    break;
                case 'R' :
                    setNewHeading(direction, units);
                    break;
                case 'F' :
                    if (heading == 'E') {
                        scoreE+=units;
                    } else if (heading == 'W') {
                        scoreE-=units;
                    } else if (heading == 'N') {
                        scoreN+=units;
                    } else if (heading =='S') {
                        scoreN-=units;
                    }
                    break;
                case 'X' :
                    System.out.println("bug in system: X detected");
                    System.exit(0);
                    break;
            }
            x++;
        }
        System.out.println("N " + scoreN);
        System.out.println("E " + scoreE);
        System.out.println(Math.abs(scoreN) + Math.abs(scoreE));
    }

    private void setNewHeading(char direction, int units) {
        int currentDegrees = getDegreesFromHeading(heading);
        currentDegrees+= direction=='L' ? units*-1 : units;
        heading = getHeadingFromDegrees(currentDegrees);
    }

    private int getDegreesFromHeading(char heading) {
        switch (heading) {
            case 'N' :
                return 0;
            case 'S' :
                return 180;
            case 'E' :
                return 90;
            case 'W' :
                return 270;
        }
        return 0;
    }

    private char getHeadingFromDegrees(int degrees) {
        int d = degrees>=360 ? degrees-360 : (degrees < 0 ? degrees + 360 : degrees);
        switch (d) {
            case 0 :
                return 'N';
            case 90 :
                return 'E';
            case 180 :
                return 'S';
            case 270 :
                return 'W';
        }
        return 'X';
    }

    public void challenge2() {
        int x=0;
        wpN = 1;
        wpE = 10;
        int scoreN = 0;
        int scoreE = 0;
        heading = 'E';

        while (x<list.size()) {
            char direction = list.get(x).charAt(0);
            int units = Integer.parseInt(list.get(x).substring(1, list.get(x).length()));
            switch (direction) {
                case 'N' :
                    wpN+=units;
                    break;
                case 'S' :
                    wpN-=units;
                    break;
                case 'E' :
                    wpE+=units;
                    break;
                case 'W' :
                    wpE-=units;
                    break;
                case 'L' :
                    rotateWaypoint(direction, units);
                    break;
                case 'R' :
                    rotateWaypoint(direction, units);
                    break;
                case 'F' :
                    scoreN += wpN * units;
                    scoreE += wpE * units;
                    break;
                case 'X' :
                    System.out.println("bug in system: X detected");
                    System.exit(0);
                    break;
            }
            x++;
        }
        System.out.println("N " + scoreN);
        System.out.println("E " + scoreE);
        System.out.println(Math.abs(scoreN) + Math.abs(scoreE));
    }

    private void rotateWaypoint(char direction, int units) {
        int turns = units/90;
        for (int x=0; x< turns; x++) {
            int tempE = wpE;
            wpE = wpN;
            wpN = tempE * -1;
            if (direction == 'L') {
                //flip
                wpE = wpE * -1;
                wpN = wpN * -1;
            }
        }
    }
}
