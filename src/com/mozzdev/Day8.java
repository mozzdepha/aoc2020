package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day8 {
    private List<String> list;
    private int accumulator;
    Set<Integer> processed;

    public static void main(String[] args) {
        new Day8().challenge2();
    }

    public Day8() {
        initialize();
    }

    private void initialize() {
        list = new ArrayList<String>();
        File file = new File("inputs8.txt");
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
        processed = new HashSet<Integer>();
        int x=0;
        int lastInstructionPointer = list.size()-1;
        while (true) {
            if (processed.contains(x)) {
                System.out.println("Loop reached. Accumulator=" + accumulator);
                System.exit(0);
            } else {
                processed.add(x);
            }
            String[] currentInstruction = list.get(x).split(" ");
            String op = currentInstruction[0];
            int param = Integer.parseInt(currentInstruction[1]);


            switch(op) {
                case "acc" :
                    accumulator+=param;
                    x++;
                    break;
                case "jmp" :
                    x+=param;
                    break;
                case "nop" :
                    x++;
                    break;
                default:
                    System.out.println("No match for instruction:" + op);
            }

        }

    }

    public void challenge2() {


        // for each row, attempt to correct the code by making the switch
        for (int x = 0; x < list.size(); x++) {
            attemptBugFix(x);
        }
    }

    private void attemptBugFix(int row) {
        processed = new HashSet<Integer>();
        accumulator = 0;
        int lastInstructionPointer = list.size() - 1;
        int x=0;
        while (x!=lastInstructionPointer) {
            if (processed.contains(x)) {
                System.out.println("Loop reached. Bugfix failed for row" + row + ". acc value: " + accumulator);
                return;
            } else {
                processed.add(x);
            }
            String[] currentInstruction = list.get(x).split(" ");
            String op = currentInstruction[0];
            int param = Integer.parseInt(currentInstruction[1]);

            // attempt bugfix
            if (x==row) {
                if (op.equals("jmp")) {
                    op = "nop";
                } else if (op.equals("nop")) {
                    op = "jmp";
                }
            }

            switch(op) {
                case "acc" :
                    accumulator+=param;
                    x++;
                    break;
                case "jmp" :
                    x+=param;
                    break;
                case "nop" :
                    x++;
                    break;
                default:
                    System.out.println("No match for instruction:" + op);
            }

        }
        System.out.println("End of program reached! Acc=" + accumulator);
        System.exit(0);

    }

}
