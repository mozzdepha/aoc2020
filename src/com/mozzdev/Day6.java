package com.mozzdev;

import java.io.*;
import java.util.*;

public class Day6 {
    private char[][] map;

    private List<String> list;

    public static void main(String[] args) {
        new Day6().challenge1();
//        new Day5().challenge2();
    }

    public Day6() {
        initializeNumbers();
    }

    private void initializeNumbers() {
        list = new ArrayList<String>();
        File file = new File("inputs6.txt");
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
        int x=0;
        int answerCount=0;
        Map<Character, Integer> yesses = new HashMap<Character, Integer>();
        int sizeOfGroup=0;
        while (x<list.size()) {
            if (list.get(x).equals("")) {
                Iterator<Character> keysIt = yesses.keySet().iterator();
                while(keysIt.hasNext()) {
                    int responseCount=yesses.get(keysIt.next());
                    if (responseCount == sizeOfGroup) {
                        answerCount+=1;
                    }
                }
                yesses = new HashMap<Character, Integer>();
                sizeOfGroup=0;
            } else {
                char[] answers = list.get(x).toCharArray();
                for (int y = 0; y < answers.length; y++) {
                    if(yesses.containsKey(answers[y])) {
                        int currentAnswer = yesses.get(answers[y]);
                        yesses.put(answers[y], currentAnswer +1);

                    } else{
                        yesses.put(answers[y], 1);
                    }
                }
                sizeOfGroup++;
            }
            x++;
        }
        //add last group
        Iterator<Character> keysIt = yesses.keySet().iterator();
        while(keysIt.hasNext()) {
            int responseCount=yesses.get(keysIt.next());
            if (responseCount == sizeOfGroup) {
                answerCount+=1;
            }
        }
        System.out.println("Answers: " + answerCount);

    }

}
