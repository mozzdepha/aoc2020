package com.mozzdev.adventofcode.aoc2022;

import com.mozzdev.adventofcode.aoc2020.AocDay2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends AocDay2022 {


    private List<Integer> numbers;

    public static void main(String[] args) {
        new Day1().run();
    }

    public String getInputFilename() {
        return "inputs1.txt";
    }

    public void challenge1() {
        initNumbers();
        Collections.sort(numbers);
        System.out.println(numbers.get(numbers.size()-1));
    }

    public void challenge2() {
        initNumbers();
        Collections.sort(numbers);
        int totalCalories = numbers.get(numbers.size()-1);
        totalCalories += numbers.get(numbers.size()-2);
        totalCalories += numbers.get(numbers.size()-3);
        System.out.println(totalCalories);
    }

    private void initNumbers() {
        numbers = new ArrayList<Integer>();
        int currentTotal = 0;
        for (String numString: list) {
            if (numString.equals("")) {
                numbers.add(currentTotal);
                currentTotal = 0;
            } else {
                currentTotal += Integer.parseInt(numString);
            }
        }
    }

    private void checkMatchFor2020(int num) {
        int target = 2020 - num;
//        System.out.println("Target: " + num + "; looking for: " + target);
        for (Integer candidate : numbers) {
            if (target == candidate.intValue()) {
                System.out.println("Found match! " + num + " matches with " + candidate.intValue());
                System.out.println("Result: " + (num*candidate.intValue()));
            }
        }
    }
    private void checkMatchFor20202(int num) {
        for (Integer candidate : numbers) {
            if (num+candidate < 2020) {
                for (Integer candidate2 : numbers) {
                    if (num+candidate+candidate2 == 2020) {
                        System.out.println("Found match! " + num + " - " + candidate + " - " + candidate2 + "; answer = " + num*candidate*candidate2);
                        break;
                    } else {
//                        System.out.println("No match:" + num + "+" + candidate + "+" + candidate2 + "=" + (num + candidate + candidate2));
                    }
                }
            }


        }
    }



}
