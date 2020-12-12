package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day7 {
    private char[][] map;
    private int matches=0;
    private List<String> list;
    private Map<String, Bag> bags;

    public static void main(String[] args) {
        new Day7().challenge2();
//        new Day5().challenge2();
    }

    public Day7() {
        initializeNumbers();
    }

    private void initializeNumbers() {
        bags = new HashMap<String, Bag>();
        list = new ArrayList<String>();
        File file = new File("inputs7.txt");
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
        int x=0;
        while (x<list.size()) {
            parseRule(list.get(x));
            x++;
        }
        System.out.println("Done");
        x=0;
        matches = 0;
        Set keys = bags.keySet();
        Iterator<String> keysIt = keys.iterator();

        Set<String> colourMatches = new HashSet<String>();
        while (keysIt.hasNext()) {
            String key = keysIt.next();
            Bag bag = bags.get(key);
            if (bag.contains("shiny gold")) {
                checkBag(bag, colourMatches);
            }
            x++;
        }
        System.out.println("" + matches + " bags can contain gold");
    }

    public void challenge2() {
        int x=0;
        while (x<list.size()) {
            parseRule(list.get(x));
            x++;
        }
        System.out.println("Done");

        addSelfAndChildren("shiny gold");

        matches--;
        System.out.println("" + matches + " bags can contain gold");
    }

    private void addSelfAndChildren(String bagName) {
        matches++;
        Iterator<String> colours = bags.get(bagName).getContents().iterator();
        while (colours.hasNext()) {
            String color = colours.next();
            addSelfAndChildren(color);
        }
    }

    private void checkBag(Bag bag, Set colourMatches) {
        // add this bag and all parents recursively
        String colour = bag.getColour();
        if (!colourMatches.contains(colour) && !colour.equals("shiny gold")) {
            System.out.println(bag.getColour());
            if (bag.getColour().equals("shiny violet")) {
                System.out.println("aoskjd");
            }
            matches++;
            colourMatches.add(bag.getColour());
            // recurse into all parents
            Iterator<Bag> parentIt = getParents(bag).iterator();
            while (parentIt.hasNext()) {
                checkBag(parentIt.next(), colourMatches);
            }
        }
    }

    private List<Bag> getParents(Bag bag) {
        // return all bags that have bag as child
        List<Bag> parents = new ArrayList<Bag>();
        Iterator<Bag> bagIt = bags.values().iterator();
        while(bagIt.hasNext()) {
            Bag current = bagIt.next();
            if(current.contains(bag.getColour())) {
                parents.add(current);
            }
        }
        return parents;
    }

    private void parseRule(String rule) {
        String colour = rule.substring(0, rule.indexOf("bags contain")).trim();
        String contentString = rule.substring(rule.indexOf("bags contain") +13);
        String[] contents = contentString.split(",");
        Bag bag = new Bag(colour);
        try {
            for (int x = 0; x < contents.length; x++) {
                contents[x] = contents[x].trim();
                if (!contents[x].equals("no other bags.")) {
                    int number = Integer.parseInt(contents[x].substring(0, 1));
                    String containedColour = contents[x].substring(contents[x].indexOf(" ") + 1).trim();
                    containedColour = containedColour.substring(0, containedColour.indexOf(" bag"));
                    for (int y = 0; y < number; y++) {
                        bag.addContents(containedColour);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bags.put(bag.colour, bag);
    }

}
