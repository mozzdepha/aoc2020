package com.mozzdev.adventofcode.aoc2022;

import java.util.*;

public class Day5 extends AocDay2022 {

    Map<Integer, List> stacks;
    List<Move> moves;

    public static void main(String[] args) {
        new Day5().run();
    }

    public String getInputFilename() {
        return "inputs5.txt";
    }

    public void challenge1() {
        init();
        processMoves1();
        StringBuffer sb = new StringBuffer();
        for (int x=0; x<stacks.size(); x++) {
            List currentList = stacks.get(x+1);
            sb.append(currentList.get(currentList.size()-1));
        }
        System.out.println(sb.toString());
    }

    public void challenge2() {
        init();
        processMoves2();
        StringBuffer sb = new StringBuffer();
        for (int x=0; x<stacks.size(); x++) {
            List currentList = stacks.get(x+1);
            sb.append(currentList.get(currentList.size()-1));
        }
        System.out.println(sb.toString());
    }

    private void processMoves1() {
        for (Move move : moves) {
            List source = stacks.get(move.getSource());
            List destination = stacks.get(move.getDestination());
            for (int x=0; x<move.getQuantity(); x++) {
                // remove the last from source and add to destination
                Object last = source.remove(source.size() - 1);
                destination.add(last);
            }
        }
    }

    private void processMoves2() {
        for (Move move : moves) {
            List source = stacks.get(move.getSource());
            List destination = stacks.get(move.getDestination());
            List objectsToMove = new ArrayList();
            for (int x=0; x<move.getQuantity(); x++) {
                // remove the last from source and add to destination
                objectsToMove.add(0, source.remove(source.size() - 1));

            }
            destination.addAll(objectsToMove);
        }
    }

    private void init() {
        moves = new ArrayList<Move>();
        initStacks();
        for (String inputRow: list) {
            if(inputRow.startsWith("move")) {
                moves.add(new Move(inputRow));
            }
        }
    }

    private void initStacks() {
        stacks = new HashMap<Integer, List>();
        addToStacks(1, "HBVWNMLP");
        addToStacks(2, "MQH");
        addToStacks(3, "NDBGFQMP");
        addToStacks(4, "ZTFQMWG");
        addToStacks(5, "MTHP");
        addToStacks(6, "CBMJDHGT");
        addToStacks(7, "MNBFVR");
        addToStacks(8, "PLHMRGS");
        addToStacks(9, "PDBCN");
    }

    private void addToStacks(int position, String data) {
        List stackSource = new ArrayList();
        for (int x=0; x<data.length(); x++) {
            stackSource.add(data.toCharArray()[x]);
        }
        stacks.put(position, stackSource);
    }

    private class Move {
        public int getQuantity() {
            return quantity;
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        private int quantity, source, destination;

        public Move(String input) {
            String[] result = input.split("\\s");
            this.quantity = Integer.parseInt(result[1]);
            this.source = Integer.parseInt(result[3]);
            this.destination = Integer.parseInt(result[5]);
        }

        @Override
        public String toString() {
            return "Move{" +
                    "quantity=" + quantity +
                    ", source=" + source +
                    ", destination=" + destination +
                    '}';
        }
    }

}
