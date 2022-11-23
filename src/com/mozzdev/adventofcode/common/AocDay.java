package com.mozzdev.adventofcode.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AocDay {
    protected List<String> list;

    public AocDay() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<String>();
        File file = new File(getInputFile());
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

    public String getInputFile() {
        return "resources/" + getEventYear() + "/" + getInputFilename();
    }

    public abstract String getEventYear();

    public abstract String getInputFilename();

    public void run() {
        long startTime = System.currentTimeMillis();
        System.out.println("Starting Challenge1...");
        challenge1();
        long endTime1 = System.currentTimeMillis();
        System.out.println("... completed Challenge1 in " + (endTime1 - startTime) + "ms");
        System.out.println("Starting Challenge2...");
        challenge2();
        long endTime2 = System.currentTimeMillis();
        System.out.println("... completed Challenge2 in " + (endTime2 - endTime1) + " ms");
    }

    public void challenge1() {
        System.out.println("Override me!");
    }

    public void challenge2() {
        System.out.println("Override me!");
    }
}
