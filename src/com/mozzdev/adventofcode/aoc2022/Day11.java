package com.mozzdev.adventofcode.aoc2022;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;

public class Day11 extends AocDay2022 {

    public static void main(String[] args) {
        new Day11().run();
    }

    private List<Monkey> monkeys;
    public String getInputFilename() {
        return "inputs11.txt";
    }

    public void challenge1() {
        init();
        for (int x=0; x<20; x++) {
            for (Monkey monkey : monkeys) {
                for (int y=0; y<monkey.items.size(); y++) {
                    Item item = monkey.items.get(y);
                    monkey.inspect(item);
                    monkey.applyRelief(item);
                    monkey.passOntoNext(item);
                }
                // monkey now has nothing as all have been tossed.
                monkey.items = new ArrayList<Item>();
            }
        }

        Collections.sort(monkeys);
        double monkeyBusiness = monkeys.get(0).getCount() * monkeys.get(1).getCount();
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        System.out.println(format.format(monkeyBusiness));

    }

    public void challenge2() {
        init();
        for (int x=0; x<10000; x++) {
            for (Monkey monkey : monkeys) {
                for (int y=0; y<monkey.items.size(); y++) {
                    Item item = monkey.items.get(y);
                    monkey.inspect(item);
//                    monkey.applyRelief(item);
                    monkey.passOntoNext(item);
                }
                // monkey now has nothing as all have been tossed.
                monkey.items = new ArrayList<Item>();
            }
            if (x==0 || x==19 || x==999 || x==1999 || x==2999 || x==3999 || x==4999 || x==5999 || x==6999 || x==7999 || x==8999 || x==9999) {
                System.out.println("After round " + (x + 1));
                for (Monkey m : monkeys) {
                    System.out.println(m.getCount());

                }
            }
        }
        Collections.sort(monkeys);
        long monkeyBusiness = monkeys.get(0).getCount() * monkeys.get(1).getCount();
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        System.out.println(format.format(monkeyBusiness));

    }

    private void init() {
        monkeys = new ArrayList<Monkey>();
        List<String> tmpInputs = new ArrayList<String>();
        for (String inputRow: list) {
            tmpInputs.add(inputRow);
            if (tmpInputs.size()==7) {
                monkeys.add(new Monkey(tmpInputs));
                tmpInputs = new ArrayList<String>();
            }
        }
        // add the last one
        monkeys.add(new Monkey(tmpInputs));
    }

    private class Monkey implements Comparable {
        private List<Item> items;

        private String operation;
        private long test;
        private int destinationTrue;
        private int destinationFalse;

        private long count=0;

        public Monkey(List<String> inputs) {
            String tmp = inputs.get(1).substring(18);
            String[] tmpItems = tmp.split(",\\s");
            items = new ArrayList<Item>();
            for (int x=0; x<tmpItems.length; x++) {
                items.add(new Item(Integer.parseInt(tmpItems[x])));
            }
            operation = inputs.get(2).substring(23);
            test = Integer.parseInt(inputs.get(3).substring(21));
            destinationTrue = Integer.parseInt(inputs.get(4).substring(29));
            destinationFalse= Integer.parseInt(inputs.get(5).substring(30));
        }

        public void inspect(Item item) {
            count++;
            char op = operation.charAt(0);
            String paramString = operation.substring(2);
            long param;
            if (paramString.equals("old")) {
                param = item.level;
            } else {
                param = Long.parseLong(paramString);
            }
            if (op == '*') {
                item.level *= param;
            } else {
                item.level += param;
            }
        }

        public void applyRelief(Item item) {
            item.level = item.level/3;
        }

        public void passOntoNext(Item item) {
            if (performTest(item)) {
                passOnto(item, destinationTrue);
            } else {
                passOnto(item, destinationFalse);
            }
        }

        private boolean performTest(Item item) {
            return item.level%this.test==0L;
        }

        private void passOnto(Item item, int destination) {
            monkeys.get(destination).addItem(item);
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public long getCount() {
            return count;
        }

        @Override
        public int compareTo(Object o) {
            long comp = ((Monkey)o).getCount() - getCount();
            if (comp ==0l) {
                return 0;
            } else if (comp<0l) {
                return -1;
            } else {
                return 1;
            }
        }

        public String toString() {
            return items.toString();
        }
    }

    private class Item {
        private long level;

        public Item(long worryLevel) {
            level = worryLevel;
        }

        public long getLevel() {
            return level;
        }

        public String toString() {
            return "" + level;
        }

    }
}
