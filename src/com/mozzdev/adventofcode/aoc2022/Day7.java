package com.mozzdev.adventofcode.aoc2022;

import java.text.NumberFormat;
import java.util.*;

public class Day7 extends AocDay2022 {

    private DirectoryEntry root;
    private Map<String, List> directoryStructure;

    public static void main(String[] args) {
        new Day7().run();
    }

    public String getInputFilename() {
        return "inputs7.txt";
    }

    public void challenge1() {
        init();
        List directoriesOfAppropriateSize = new ArrayList();
        root.collectDirectorySizes(directoriesOfAppropriateSize);
        double total = 0.0d;
        for (Object current : directoriesOfAppropriateSize) {
            if ((double)current <= 100000) {
                total += (double) current;
            }
        }
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        System.out.println(format.format(total));
    }

    public void challenge2() {
        init();
        double spaceRequired = 30000000d;
        double currentSize = root.getSize();
        double currentFreeSpace = 70000000d - currentSize;
        double minRecoverySize = spaceRequired - currentFreeSpace;
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        System.out.println("currentSize: " + format.format(currentSize));

        System.out.println("minRecoverySize: " + format.format(minRecoverySize));
        List directorySizes = new ArrayList();
        root.collectDirectorySizes(directorySizes);
        Collections.sort(directorySizes);
        for (Object current : directorySizes) {
            if ((double)current >= minRecoverySize) {
                System.out.println(format.format((double) current));
                return;
            }
        }
        System.out.println("No directory found that fits");
    }

    private void init() {
        DirectoryEntry current = null;
        for (String inputRow: list) {
            if(inputRow.startsWith("dir")) {
                // create a new directory entry
                current.addDirectory(inputRow.split("\\s")[1]);
            } else if (inputRow.startsWith("$ cd ..")) {
                // pop back up
                current = current.getParent();
            } else if (inputRow.startsWith("$ cd /")) {
                current = new DirectoryEntry("/", null);
                root = current;
            } else if (inputRow.startsWith("$ cd")) {
                // navigate into a directory
                String dirName = inputRow.split("\\s")[2];
                if (current == null) {
                    System.out.println("No Current directory");
                }
                current = current.getDirectory(dirName);

            } else if (inputRow.startsWith("$ ls")) {
                // do nothing - we are about to read the contents of a file
            } else {
                // add a file to the current directory
                current.addFile(inputRow);
            }
        }
    }

    private class DirectoryEntry {
        private String name;
        private Map fileEntries;
        private Map directories;
        private DirectoryEntry parent;

        public DirectoryEntry(String name, DirectoryEntry parent) {
            directories = new HashMap();
            fileEntries = new HashMap();
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public Map getFileEntries() {
            return fileEntries;
        }

        public DirectoryEntry getParent() {
            return parent;
        }

        public double getSizeOfDirectoriesSmallerThanMaxSize() {
            double size = 0.0d;
            for (Object dir : directories.values()) {
                size += ((DirectoryEntry)dir).getSizeOfDirectoriesSmallerThanMaxSize();
            }
            for (Object fe : getFileEntries().values()) {
                size += ((FileEntry)fe).getSize();
            }
            return size;
        }

        public void addDirectory(String name) {
            directories.put(name, new DirectoryEntry(name, this));
        }

        public void addFile(String inputRow) {
            String[] parsed = inputRow.split("\\s");
            double size = Double.parseDouble(parsed[0]);
            String name = parsed[1];
            getFileEntries().put(name, new FileEntry(size, name));
        }

        public DirectoryEntry getDirectory(String name) {
            return (DirectoryEntry) directories.get(name);
        }

        public void collectDirectorySizes(List dirs) {

            // collect children
            for (Object child : directories.values()) {
                ((DirectoryEntry)child).collectDirectorySizes(dirs);
            }
            dirs.add(getSize());
        }

        public double getSize() {
            // return the size of my files and those of my children
            double size = 0.0d;
            for (Object child : directories.values()) {
                size+=((DirectoryEntry)child).getSize();
            }
            for (Object file : fileEntries.values()) {
                size += ((FileEntry)file).getSize();
            }
            return size;
        }

        @Override
        public String toString() {
            return "DirectoryEntry{" +
                    "name='" + name + '\'' +
                    ", size=" + getSizeOfDirectoriesSmallerThanMaxSize()+
                    '}';
        }
    }


    private class FileEntry {
        private String name;
        private double size;

        public FileEntry(double size, String name) {


            this.size = size;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "FileEntry{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }
    }

}
