package com.mozzdev.adventofcode.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day2 extends AocDay2020 {
    private List<Password> passwords;

    public static void main(String[] args) {
        new Day2().run();
    }

    public String getInputFilename() {
        return "inputs2.txt";
    }

    public void challenge1() {
        initializePasswords();
        int validPasswords = 0;
        for (Password pw : passwords) {
            if (pw.isValid()) {
                validPasswords++;
            }
        }
        System.out.println("Total number of valid passwords:" + validPasswords);
    }

    public void challenge2() {
        initializePasswords();
        int validPasswords = 0;
        for (Password pw : passwords) {
            if (pw.isValid2()) {
                validPasswords++;
            }
        }
        System.out.println("Total number of valid passwords:" + validPasswords);
    }

    private void initializePasswords() {
        passwords = new ArrayList<Password>();
        for (String password : list) {
            passwords.add(new Password(password));
        }
    }
}
