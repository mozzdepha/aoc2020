package com.mozzdev;

public class MemSwitch {
    private String mask;
    private int position;
    private long value;

    @Override
    public String toString() {
        return "MemSwitch{" +
                "mask='" + mask + '\'' +
                ", position=" + position +
                ", value=" + value +
                '}';
    }

    public String getMask() {
        return mask;
    }

    public int getPosition() {
        return position;
    }

    public long getValue() {
        return value;
    }

    public MemSwitch(String mask, int position, long value) {
        this.mask = mask;
        this.position = position;
        this.value = value;
    }

    public long evaluate() {
        char[] bits = ("0000" + Long.toBinaryString( getValue() | 0x100000000L ).substring(1)).toCharArray();
        for (int x=0; x<getMask().length(); x++) {
            switch (getMask().charAt(x)) {
                case 'X':
                    // do nothing
                    break;
                case '0':
                    bits[x] = '0';
                    break;
                case '1':
                    bits[x] = '1';
                    break;
            }
        }
        long result = Long.parseLong(new String(bits), 2);
        return result;
    }


}
