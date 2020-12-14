package com.mozzdev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day1 extends AocDay {


    private List<Integer> numbers;

    public static void main(String[] args) {
        new Day1().run();
    }

    public String getInputFile() {
        return "resources/inputs.txt";
    }

    public void challenge1() {
        initNumbers();
        for (Integer num: numbers) {
            checkMatchFor2020(num);
        }
    }

    public void challenge2() {
        initNumbers();
        for (Integer num: numbers) {
            checkMatchFor20202(num);
        }
    }

    private void initNumbers() {
        numbers = new ArrayList<Integer>();
        for (String numString: list) {
            numbers.add(Integer.parseInt(numString));
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
