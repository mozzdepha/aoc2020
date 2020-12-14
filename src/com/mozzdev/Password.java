package com.mozzdev;

public class Password {
    int min;
    int max;
    char letter;
    String password;

    public Password(int min, int max, char letter, String password) {
        this.min = min;
        this.max = max;
        this.letter = letter;
        this.password = password;
    }

    public Password(String text) {
        String[] words = text.split("\\s+");
        this.password = words[2];
        String[] minMax = words[0].split("-");
        this.min = Integer.parseInt(minMax[0]);
        this.max = Integer.parseInt(minMax[1]);
        this.letter = words[1].charAt(0);
    }

    public char getLetter() {
        return letter;
    }

    public String getPassword() {
        return password;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Password{" +
                "min=" + min +
                ", max=" + max +
                ", letter=" + letter +
                ", password='" + password + '\'' +
                '}';
    }

    public int getMax() {
        return max;
    }

    public boolean isValid() {
        long count = getPassword().chars().filter(ch -> ch == getLetter()).count();
        return count <= getMax() && count >= getMin();
    }

    public boolean isValid2() {
        try {
            boolean minTest = getPassword().charAt(getMin() - 1) == getLetter();
            boolean maxTest = getPassword().charAt(getMax() - 1) == getLetter();
            return minTest ^ maxTest;

        } catch (Exception e) {
            return false;
        }
    }
}
