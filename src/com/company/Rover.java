package com.company;

import java.util.HashMap;

public class Rover {

    //initialize variables
    private String directionFaced;
    private HashMap<String, Integer> coordinates;

    //default constructor
    public Rover(){
        directionFaced = "";
        this.coordinates = new HashMap<>();
    }

    //overriden constructor initialises variables by spliting the data loaded from the txt file
    public Rover(String initialRoverData, int splitAfterWhiteSpace){

        //initialize variables
        directionFaced = "";
        this.coordinates = new HashMap<>();

        //loop used to split data accordlingly
        for(int l = 0; l < initialRoverData.length(); l ++){

            if(!initialRoverData.substring(l, l + 1).equals(" ")){

                if(splitAfterWhiteSpace == 0)
                    this.coordinates.put("x", Integer.parseInt(initialRoverData.substring(l, l + 1)));
                else if(splitAfterWhiteSpace == 1)
                    this.coordinates.put("y", Integer.parseInt(initialRoverData.substring(l, l + 1)));

            } else if(initialRoverData.substring(l, l + 1).equals(" ")) {
                splitAfterWhiteSpace ++;
            }

            if(splitAfterWhiteSpace == 2 && !initialRoverData.substring(l, l + 1).equals(" ")){
                    this.directionFaced += initialRoverData.substring(l, l + 1);
            }

        }
    }

    //getter for rover coordinates
    public HashMap<String, Integer> getCoordinates() {
        return coordinates;
    }

    //method for movements of the rover
    public void movement(String instuctions){

        //l is left r is right
        for (int x = 0; x < instuctions.length(); x++) {

            String singleDirection = instuctions.substring(x, x + 1);

            if (singleDirection.equalsIgnoreCase("l")) {
                   rotation("l");
            } else if (singleDirection.equalsIgnoreCase("r")) {
                   rotation("r");
            } else if (singleDirection.equalsIgnoreCase("m")) {
                   moveforward();
            } else {
                   instructionFailure();
            }

        }
    }

    //method for the rotations
    public void rotation(String rotationDirection){

        //if left was loaded or right was loaded use enum to compare and set which direction the rover is facing
        if(rotationDirection.equalsIgnoreCase("l")){
            System.out.println("Rover is rotating left...");
            if(Direction.NORTH_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.WEST_FACED.getDescription();
                System.out.println("Rover is now facing west");
            } else if(Direction.WEST_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.SOUTH_FACED.getDescription();
                System.out.println("Rover is now facing south");
            } else if(Direction.SOUTH_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.EAST_FACED.getDescription();
                System.out.println("Rover is now facing east");
            } else if(Direction.EAST_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.NORTH_FACED.getDescription();
                System.out.println("Rover is now facing north");
            } else{
                this.instructionFailure();
            }
        } else if(rotationDirection.equalsIgnoreCase("r")){
            System.out.println("Rover is rotating right...");
            if(Direction.NORTH_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.EAST_FACED.getDescription();
                System.out.println("Rover is now facing east");
            } else if(Direction.EAST_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.SOUTH_FACED.getDescription();
                System.out.println("Rover is now facing south");
            } else if(Direction.SOUTH_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.WEST_FACED.getDescription();
                System.out.println("Rover is now facing west");
            } else if(Direction.WEST_FACED.getDescription().equalsIgnoreCase(this.directionFaced)){
                this.directionFaced = Direction.NORTH_FACED.getDescription();
                System.out.println("Rover is now facing north");
            } else{
                this.instructionFailure();
            }
        } else{
            this.instructionFailure();
        }

    }

    //this is to move the rover coordinates forward
    public void moveforward(){

        System.out.println("Rover is now moving forward...");

        //get current coordinates
        int y = this.coordinates.get("y");
        int x = this.coordinates.get("x");

        //rover moves forward  depending on what direction it is facing
        if (this.directionFaced.equalsIgnoreCase(Direction.NORTH_FACED.getDescription())) {
            y = --y;
            this.coordinates.put("y", y);
        } else if (this.directionFaced.equalsIgnoreCase(Direction.SOUTH_FACED.getDescription())) {
            y = ++y;
            this.coordinates.put("y", y);
        } else if (this.directionFaced.equalsIgnoreCase(Direction.EAST_FACED.getDescription())) {
            x = ++x;
            this.coordinates.put("x", x);
        } else if (this.directionFaced.equalsIgnoreCase(Direction.WEST_FACED.getDescription())) {
            x = ++x;
            this.coordinates.put("x", x);
        }

        System.out.println("Rover after forward move is currently at coordinates" + " x = " + this.getCoordinates().get("x") + " column = " + this.getCoordinates().get("y"));

    }

    //print successful movement of rover
    public void printSuccessExection(){
        System.out.println("\n++++++++++++++++Instruction successfully executed+++++++++++++++++++\n");
        System.out.println("Rover has stopped on mars in the following state:" + "\nDirection faced = " + this.directionFaced + "\nx = " + this.getCoordinates().get("x") + "\ny = " + this.getCoordinates().get("y"));
    }

    //print initial position of rover
    public void printInitialPosition(){
        System.out.println("Rover has an initial position of " + "x = " + this.getCoordinates().get("x") + " y = " + this.getCoordinates().get("y") + " direction = " + this.directionFaced.toUpperCase());
    }

    //print error for when rover falls of mars
    public void printErrorExection(String axis){
        System.out.println("\n++++++++++++++++Fatal error rover has fallen off mars " + axis + " axis+++++++++++++++++++\n");
        System.out.println("Rover's last recorded state before signal loss on mars:" + "\nDirection faced = " + this.directionFaced + "\nx = " + this.getCoordinates().get("x") + "\ny = " + this.getCoordinates().get("y"));
        System.out.println("Exiting terminal...");
        System.exit(1);
    }

    //print instruction error for when wrong instructions are provided
    public void instructionFailure(){
        System.out.println("\n++++++++++++++++Invalid instruction provided+++++++++++++++++++\n");
        System.out.println("Exiting terminal...");
        System.exit(1);
    }
}
