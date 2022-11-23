package com.mozzdev.adventofcode.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    String colour;
    List<String> contents;

    public Bag() {
        contents = new ArrayList<String>();
    }

    public Bag(String colour) {
        this();
        this.colour = colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public List<String> getContents() {
        return contents;
    }

    public Bag(String colour, List<String> contents) {
        this.colour = colour;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "colour='" + colour + '\'' +
                ", contents=" + contents +
                '}';
    }

    public void addContents(String content) {
        getContents().add(content);
    }

    public boolean contains(String content) {
        return getContents().contains(content);
    }

    public boolean containsNested(String content) {
        int x=0;
        while (x<getContents().size()) {

            x++;
        }
        return false;
    }
}
