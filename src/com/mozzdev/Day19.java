package com.mozzdev;

import java.util.*;

public class Day19 extends AocDay {

    private Map<Integer, List<Object>> rules;
    private List<String> checked;

    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs19.txt";
    }

    public void challenge1() {
        parseInputs();
        int matches=0;
        for (String input: checked) {
            if (checkRule(input, 0)) {
                matches++;
            }
        }
        System.out.println("Total rules that match: " + matches);
    }

    /**
     * Return true if the input string passes all the rules
     * @param input
     * @param startRule
     * @return
     */
    private boolean checkRule(String input, int startRule) {
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
