package com.company;

import java.util.HashMap;

public class Mars {

    //initialize object variables
    private static HashMap<String, Integer> boundary;
    private static String x;
    private static String y;

    //default constructor
    public Mars(){
        boundary = new HashMap<>();
        x = "";
        y = "";
    }

    //overriden constructor initials variables using input from data file
    public Mars(String marsBoundaryData, boolean splitToVertical){

        //initialize variables
        boundary = new HashMap<>();
        x = "";
        y = "";

        //loop to split data accordlingly
        for(int l = 0; l < marsBoundaryData.length(); l ++){

            if(!marsBoundaryData.substring(l, l + 1).equals(" ")){
                if(!splitToVertical)
                    this.x += marsBoundaryData.substring(l, l + 1);
            } else if(marsBoundaryData.substring(l, l + 1).equals(" ")) {
                splitToVertical = true;
            }

            if(splitToVertical && !marsBoundaryData.substring(l, l + 1).equals(" ")){
                this.y += marsBoundaryData.substring(l, l + 1);
            }

        }

        //set boundaries for mars based on manipulation
        this.boundary.put("x", Integer.parseInt(x));
        this.boundary.put("y", Integer.parseInt(y));
    }

    //getter of boundary
    public static HashMap<String, Integer> getBoundary() {
        return boundary;
    }

    //print mars state
    public static void printMarsBoundary(){
        System.out.println("Mars is calculated to have a boundary of x = " + getBoundary().get("x") + " y = " + getBoundary().get("y"));
    }

}
