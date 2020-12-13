package com.mozzdev;

public class Bus implements Comparable<Bus> {
    private int id;
    private int offset;

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", offset=" + offset +
                '}';
    }

    public Bus(int id, int offset) {
        this.id = id;
        this.offset = offset;
    }

    public int getId() {
        return id;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public int compareTo(Bus o) {
        return o.getId() - getId();
    }
}
