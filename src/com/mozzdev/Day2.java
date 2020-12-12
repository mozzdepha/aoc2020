package com.mozzdev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    private List<Password> passwords;

    public static void main(String[] args) {
//        new Day2().challenge1();
        new Day2().challenge2();
    }

    public Day2() {
        initializeNumbers();
    }

    private void initializeNumbers() {
        List<Password> list = new ArrayList<Password>();
        File file = new File("inputs2.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(new Password(text));
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
        passwords = list;
    }

    public void challenge1() {
        int validPasswords = 0;
        for (Password pw: passwords) {
            if (pw.isValid()) {
                System.out.println("Found valid password: " + pw);
                validPasswords++;
            }
        }
        System.out.println("Total number of valid passwords:" + validPasswords);
    }

    public void challenge2() {
        int validPasswords = 0;
        for (Password pw: passwords) {
            if (pw.isValid2()) {
                System.out.println("Found valid password: " + pw);
                validPasswords++;
            }
        }
        System.out.println("Total number of valid passwords:" + validPasswords);
    }

//    private void checkMatchFor2020(int num) {
//        int target = 2020 - num;
////        System.out.println("Target: " + num + "; looking for: " + target);
//        for (Integer candidate : numbers) {
//            if (target == candidate.intValue()) {
//                System.out.println("Found match! " + num + " matches with " + candidate.intValue());
//                System.out.println("Challenge 1: " + (num*candidate.intValue()));
//            }
//        }
//    }
//    private void checkMatchFor20202(int num) {
//        for (Integer candidate : numbers) {
//            if (num+candidate < 2020) {
//                for (Integer candidate2 : numbers) {
//                    if (num+candidate+candidate2 == 2020) {
//                        System.out.println("Found match! " + num + " - " + candidate + " - " + candidate2 + "; answer = " + num*candidate*candidate2);
//                        break;
//                    } else {
////                        System.out.println("No match:" + num + "+" + candidate + "+" + candidate2 + "=" + (num + candidate + candidate2));
//                    }
//                }
//            }
//
//
//        }
//    }



}
