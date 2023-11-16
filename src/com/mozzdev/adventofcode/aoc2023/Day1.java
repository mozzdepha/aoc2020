package com.mozzdev.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends AocDay2023 {


    private List<Integer> numbers;

    public static void main(String[] args) {
        new Day1().run();
    }

    public String getInputFilename() {
        return "inputs1.txt";
    }

    public void challenge1() {
        initNumbers();
        System.out.println(numbers.get(numbers.size()-1));
    }

    public void challenge2() {
        initNumbers();
        int totalCalories = numbers.get(numbers.size()-1);
        totalCalories += numbers.get(numbers.size()-2);
        totalCalories += numbers.get(numbers.size()-3);
        System.out.println(totalCalories);
    }

    private void initNumbers() {
        numbers = new ArrayList<>();
        int currentTotal = 0;
        for (String numString: list) {
            if (numString.equals("")) {
                numbers.add(currentTotal);
                currentTotal = 0;
            } else {
                currentTotal += Integer.parseInt(numString);
            }
        }
        Collections.sort(numbers);
    }


}
