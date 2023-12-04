package com.mozzdev.adventofcode.aoc2023;

import java.util.*;

public class Day4 extends AocDay2023 {

    public static void main(String[] args) {
        new Day4().run();
    }

    public String getInputFilename() {
        return "inputs4.txt";
    }

    private List<Hand> hands;

    private void initCards() {
        hands = new ArrayList<>();
        int id=1;
        for (String s : list) {
            String postColon = s.split(":")[1];
            String[] ticketsT = postColon.split(" ");
            Hand h = new Hand(id);
            List<Integer> currentTarget= h.held;
            for (int x=0; x<ticketsT.length; x++) {
                if (ticketsT[x].equals("|")) {
                    currentTarget = h.winners;
                } else if(!ticketsT[x].isEmpty()) {
                    currentTarget.add(Integer.parseInt(ticketsT[x]));
                }
            }
            hands.add(h);
            id++;
        }
    }

    private class Hand {
        private List<Integer> held, winners;
        private int id;

        public Hand(int id) {
            this.id = id;
            held = new ArrayList<>();
            winners = new ArrayList<>();
        }
    }

    private List<Integer> toTickets(String ticket) {
        String[] tickets = ticket.split(" ");
        List<Integer> l = new ArrayList<>();
        for (int x=0; x<tickets.length; x++) {
            l.add(Integer.parseInt(tickets[x]));
        }
        return l;
    }

    public void challenge1() {
        int total = 0;
        initCards();
        for (Hand h: hands) {
            // do something
            int matches = 0;
            for (Integer potential : h.held) {
                if (h.winners.contains(potential)) {
                    matches++;
                }
            }
            double score = getScore(matches);
            total += score;
        }
        System.out.println(total);
    }

    private double getScore(int matches) {
        if (matches <=1) {
            return matches;
        } else {
            return Math.pow(2, matches -1);
        }
    }

    Map<Integer, Integer> copyMap = new HashMap<>();

    public void challenge2() {
        initCards();
        initCopyMap();

        for (Hand h: hands) {
            // do something
            int matches = 0;
            for (Integer potential : h.held) {
                if (h.winners.contains(potential)) {
                    matches++;
                }
            }
            updateCopyMap(h.id, matches);
        }
        System.out.println(getNumberOfTickets());
    }

    private void updateCopyMap(int id, int matches) {
        int multiplier = copyMap.get(id);
        for (int i=0; i<multiplier; i++) {
            for (int x = 1; x <= matches; x++) {
                int currentValue = copyMap.get(id + x);
                copyMap.put(id + x, currentValue + 1);
            }
        }
    }

    private void initCopyMap() {
        for (Hand h: hands) {
            copyMap.put(h.id, 1);
        }
    }

    private double getNumberOfTickets() {
        double total = 0d;
        for (Integer i: copyMap.values()) {
            total += i;
        }
        return total;
    }

    /**
     * Once all of the originals and copies have been processed, you end up with
     * 1 instance of card 1,
     * 2 instances of card 2,
     * 4 instances of card 3,
     * 8 instances of card 4,
     * 14 instances of card 5, and
     * 1 instance of card 6. In total, this example pile of scratchcards causes you to ultimately have 30 scratchcards!
     *
     */
}
