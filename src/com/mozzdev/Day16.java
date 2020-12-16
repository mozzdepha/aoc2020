package com.mozzdev;

import java.util.*;

public class Day16 extends AocDay {
    private List<Ticket> tickets;
    private List<Integer[]> nearbyTicketValues;

    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public String getInputFile() {
        return "resources/inputs16.txt";
    }

    public void challenge1() {
        initializeTickets();
        Set<Integer> allValidValues = getAllValidValues();
        int invalidValues = 0;
        for (int x=0; x<nearbyTicketValues.size(); x++) {
            invalidValues += getInvalidValues(nearbyTicketValues.get(x), allValidValues);
        }
        System.out.println(invalidValues);
    }

    private void initializeTickets() {
        int indexOfYourTicket = list.indexOf("your ticket:")+1;
        tickets = new ArrayList<Ticket>();
        for (int x=0; x<indexOfYourTicket-2; x++) {
            tickets.add(new Ticket(list.get(x)));
        }
        int indexOfNearbyTickets = list.indexOf("nearby tickets:");
        nearbyTicketValues = new ArrayList<Integer[]>();
        for (int x=indexOfNearbyTickets+1; x<list.size(); x++) {
            String[] values = list.get(x).split(",");
            Integer[] output = new Integer[values.length];
            for (int y=0; y<values.length; y++) {
                output[y] = Integer.parseInt(values[y]);
            }
            nearbyTicketValues.add(output);
        }
    }

    private int getInvalidValues(Integer[] ticketValues, Set<Integer> validValues) {
        int output = 0;
        for (int x=0; x<ticketValues.length; x++) {
            if (!validValues.contains(ticketValues[x])) {
                output+=ticketValues[x];
            }
        }
        return output;
    }

    private Set<Integer> getAllValidValues() {
        Set<Integer> output = new HashSet<Integer>();
        for (Ticket ticket: tickets) {
            output.addAll(ticket.getValidValues());
        }
        return output;
    }



}