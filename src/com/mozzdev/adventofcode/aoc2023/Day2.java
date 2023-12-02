package com.mozzdev.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day2 extends AocDay2023 {


    public static void main(String[] args) {
        new Day2().run();
    }

    public String getInputFilename() {
        return "inputs2.txt";
    }

    private List<Game> games;

    public void challenge1() {
        initGames();
        int total = 0;
        for (Game game : games) {
            if (game.isValid()) {
                total += game.getGameId();
            }
        }
        System.out.println(total);
    }

    public void challenge2() {
        initGames();
        double total = 0;
        for (Game game : games) {
            total += game.power();
        }
        System.out.println(total);
    }

    private void initGames() {
        games = new ArrayList<Game>();
        for (String gameString : list) {
            int gameId = Integer.parseInt(gameString.substring(5, gameString.indexOf(':')));
            Game game = new Game(gameId);
            String gameSetString = gameString.substring(gameString.indexOf(':')+1);
            String[] sets = gameSetString.split(";");
            for (int x=0; x<sets.length; x++) {
                GameSet newSet = new GameSet();
                newSet.apply(sets[x]);
                game.addGameSet(newSet);
            }
            games.add(game);
        }
    }

    private class Game {
        private int gameId;
        private List<GameSet> sets;

        public Game(int gameId) {
            sets = new ArrayList<>();
            this.gameId = gameId;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public List<GameSet> getSets() {
            return sets;
        }

        public void setSets(List<GameSet> sets) {
            this.sets = sets;
        }

        public void addGameSet(GameSet newSet) {
            sets.add(newSet);
        }

        public boolean isValid() {
            for(GameSet set: sets) {
                if(!set.isValid()) {
                    return false;
                }
            }
            return true;
        }

        public double power() {
            return getHighestRed() * getHighestBlue() * getHighestGreen();
        }

        private int getHighestRed() {
            int currentHigh = 0;
            for (GameSet set : sets) {
                if (set.highestRed > currentHigh) {
                    currentHigh = set.highestRed;
                }
            }
            return currentHigh;
        }

        private int getHighestGreen() {
            int currentHigh = 0;
            for (GameSet set : sets) {
                if (set.highestGreen > currentHigh) {
                    currentHigh = set.highestGreen;
                }
            }
            return currentHigh;
        }

        private int getHighestBlue() {
            int currentHigh = 0;
            for (GameSet set : sets) {
                if (set.highestBlue > currentHigh) {
                    currentHigh = set.highestBlue;
                }
            }
            return currentHigh;
        }

    }

    private class GameSet {
        private int blue, green, red;
        private int highestBlue, highestGreen, highestRed;

        public void apply(String input) {
            String[] balls = input.trim().split(",");
            for (int x=0; x<balls.length; x++) {
                String[] ballString = balls[x].trim().split(" ");
                if (ballString[1].equals("blue")) {
                    int newValue = Integer.parseInt(ballString[0]);
                    blue += newValue;
                    if (highestBlue<newValue) {
                        highestBlue = newValue;
                    }
                } else if (ballString[1].equals("green")) {
                    int newValue = Integer.parseInt(ballString[0]);
                    green  += newValue;
                    if (highestGreen<newValue) {
                        highestGreen = newValue;
                    }
                } else if (ballString[1].equals("red")) {
                    int newValue = Integer.parseInt(ballString[0]);
                    red += newValue;
                    if (highestRed<newValue) {
                        highestRed = newValue;
                    }
                }
            }
        }


        public int getBlue() {
            return blue;
        }

        public int getGreen() {
            return green;
        }

        public int getRed() {
            return red;
        }

        public void addBlue(int addition) {
            blue+=addition;
        }

        public void addGreen(int addition) {
            green+=addition;
        }

        public void addRed(int addition) {
            red+=addition;
        }

        public boolean isValid() {
            // only 12 red cubes, 13 green cubes, and 14 blue cubes
            return red<=12 && green<=13 && blue<=14;
        }
    }
}
