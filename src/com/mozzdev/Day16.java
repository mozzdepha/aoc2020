package com.mozzdev;

import java.util.*;

public class Day16 extends AocDay {
    private List<Ticket> tickets;
    private List<Integer[]> nearbyTicketValues;
    private Integer[] myTicket;

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
            invalidValues += getSumOfInvalidValues(nearbyTicketValues.get(x), allValidValues);
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

        String[] myTicketString = list.get(indexOfYourTicket).split(",");
        myTicket = new Integer[myTicketString.length];
        for (int x=0; x<myTicketString.length; x++) {
            myTicket[x] = Integer.parseInt(myTicketString[x]);
        }

    }

    private int getSumOfInvalidValues(Integer[] ticketValues, Set<Integer> validValues) {
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


    public void challenge2() {
        initializeTickets();
        Set<Integer> allValidValues = getAllValidValues();
        stripInvalidTicketsFromNearby(allValidValues);
        determineFieldOrder();

        List<Integer> positionsToCalculate = new ArrayList<Integer>();
        for (Ticket t: tickets) {
            System.out.println(t.getField() + " - " + t.getPossibleSequences().get(0) + " size: " + t.getPossibleSequences().size());
            if (t.getField().startsWith("departure")) {
                positionsToCalculate.add(myTicket[t.getPossibleSequences().get(0)]);
            }
        }
        long result = 1l;
        for (Integer i : positionsToCalculate) {
            result *= i;
        }
        System.out.println(result);
    }

    private void stripInvalidTicketsFromNearby(Set<Integer> validValues) {
        List<Integer[]> result = new ArrayList<Integer[]>();
        for (int x=0; x< nearbyTicketValues.size(); x++) {
            Integer[] current = nearbyTicketValues.get(x);
            if (validValues.containsAll(Arrays.asList(current))) {
                result.add(current);
            }
        }
        nearbyTicketValues=result;
    }

    private void determineFieldOrder() {
        int numTickets = tickets.size();
        for (Ticket ticket: tickets) {
            ticket.initializePossibleSequences(numTickets);
        }
        Set<Integer> solvedSet = new HashSet<Integer>();
        while (solvedSet.size() < numTickets) {
            for (int x = 0; x < numTickets; x++) {
                Ticket t = tickets.get(x);
                if (!t.isSolved()) {
                    for (int y=0; y< numTickets; y++) {
                        // for each value of y, check for a failure in that position in any nearby ticket
                        for (int z=0; z<nearbyTicketValues.size();z++) {
                            if (!t.isValid(nearbyTicketValues.get(z)[y])) {
                                t.removePossibleSequence(y);
                            }
                        }
                    }
                }
                if (t.isSolved()) {
                    int solvedSequence = t.getPossibleSequences().get(0);
                    solvedSet.add(solvedSequence);
                    // Now removed the value of x from all unsolved tickets
                    for (Ticket t2: tickets) {
                        if (!t2.isSolved() ) {
                            t2.getPossibleSequences().removeAll(solvedSet);
                        }
                    }
                }
            }
       }
    }

}
