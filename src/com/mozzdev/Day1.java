package com.mozzdev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    private List<Integer> numbers;

    public static void main(String[] args) {
//        new Day1().challenge1();
        new Day1().challenge2();
    }

    public Day1() {
        initializeNumbers();
    }

    private void initializeNumbers() {
        List<Integer> list = new ArrayList<Integer>();
        File file = new File("inputs.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Integer.parseInt(text));
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
        numbers = list;
    }

    public void challenge1() {
        for (Integer num: numbers) {
            checkMatchFor2020(num);
        }
    }

    public void challenge2() {
        for (Integer num: numbers) {
            checkMatchFor20202(num);
        }
    }

    private void checkMatchFor2020(int num) {
        int target = 2020 - num;
//        System.out.println("Target: " + num + "; looking for: " + target);
        for (Integer candidate : numbers) {
            if (target == candidate.intValue()) {
                System.out.println("Found match! " + num + " matches with " + candidate.intValue());
                System.out.println("Challenge 1: " + (num*candidate.intValue()));
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
