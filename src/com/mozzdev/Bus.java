package com.mozzdev;

public class Bus implements Comparable<Bus> {
    private int id;
    private int offset;
    private int offsetRelativeToHighest;

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", offset=" + offset +
                ", offsetRelativeToHighest=" + offsetRelativeToHighest +
                '}';
    }

    public int getOffsetRelativeToHighest() {
        return offsetRelativeToHighest;
    }

    public void setOffsetRelativeToHighest(int offsetRelativeToHighest) {
        this.offsetRelativeToHighest = offsetRelativeToHighest;
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

    public void addRelativeOffset(int newOffset) {
        offsetRelativeToHighest+=newOffset;
    }

}
