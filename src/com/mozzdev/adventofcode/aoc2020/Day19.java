package com.mozzdev.adventofcode.aoc2020;

import java.util.*;

public class Day19 extends AocDay2020 {

    private Map<Integer, List<Object>> rules;
    private List<String> checked;
    private String input;

    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public String getInputFilename() {
        return "inputs19.txt";
    }

    public void challenge1() {
        parseInputs();
        int matches=0;
        for (String current: checked) {
            input = current;
            if (checkRule(0) && input.isEmpty()) {
                matches++;
            }
        }
        System.out.println("Total rules that match: " + matches);
    }

    /**
     * Return true if the input string passes all the rules

     */
    private boolean checkRule(int rule) {
        List<Object> subRules = rules.get(rule);
        String unmodifiedString = input;
        for (int x=0; x<subRules.size(); x++) {
            List<Object> subSubRules = (List<Object>)subRules.get(x);
            if (allValid(subSubRules)) {
                return true;
            } else {
                input = unmodifiedString;
            }
        }
        return false;
    }

    /**
     * Return true if all rules are valid
     */
    private boolean allValid(List<Object> subRules) {
        try {
            for (Object nextRule : subRules) {
                String nrString = (String) nextRule;
                if (nrString.startsWith("\"")) {
                    if (input.charAt(0) == nrString.replaceAll("\"", "").charAt(0)) {
                        input = input.substring(1);
                        return true;
                    } else {
                        return false;
                    }
                } else if (!checkRule(Integer.parseInt((String) nextRule))) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void parseInputs() {
        rules = new HashMap<Integer, List<Object>>();
        int rulesEnd = list.indexOf("");
        for (int x=0; x<rulesEnd; x++) {
            parseRules(list.get(x));
        }
        checked = new ArrayList<String>();
        for (int x=rulesEnd+1; x<list.size(); x++) {
            checked.add(list.get(x));
        }
    }

    private void parseRules(String input) {
        List<Object> result = new ArrayList<Object>();
        String[] initialSplit = input.split(":");
        Integer ruleNumber = Integer.parseInt(initialSplit[0]);
        String rule = initialSplit[1];
        String[] options = rule.split("\\|");
        for (int x=0; x<options.length; x++) {
            List<Object> options2 = new ArrayList<Object>();
            String[] subRules = options[x].split(" ");
            for (int y=0; y<subRules.length; y++) {
                if (!subRules[y].equals("")) {
                    options2.add(subRules[y]);
                }
            }
            result.add(options2);
        }
        rules.put(ruleNumber, result);
    }
}
