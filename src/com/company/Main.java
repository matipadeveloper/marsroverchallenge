package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Mars mars = new Mars();
        Rover rover = new Rover();


        try {

            System.out.println("\n++++++++++++++++Welcome to the Mars rover instruction termial+++++++++++++++++++\n");
            System.out.println("Rover currently loading instructions...\n");

            //load data into single array
            String[] instructs = loadInstructionData();

            //split data into different variables
            String marsBoundaryData = instructs[0];
            String initalCoordinates = instructs[1];
            String movementsData = instructs[2];

            //initialize the mars object
            mars = new Mars(marsBoundaryData, false);

            //print the mars object state
            Mars.printMarsBoundary();

            //initialize the rover object
            rover = new Rover(initalCoordinates, 0);
            rover.printInitialPosition();
            rover.movement(movementsData);

            //validate wether rover is still on mars
            roverOutOfBounds(mars, rover);

        } catch (Exception ex){

            //handle any exception that may occur
            rover.instructionFailure();

        }

        rover.printSuccessExection();

    }

    //checks whether rover is still on mars
    private static void roverOutOfBounds(Mars mars, Rover rover){

        if(rover.getCoordinates().get("x") > mars.getBoundary().get("x")){
            rover.printErrorExection("x");
        } else if(rover.getCoordinates().get("y") > mars.getBoundary().get("y")){
            rover.printErrorExection("y");
        }

    }

    //loads data from local roverinstruction.txt file
    private static String[] loadInstructionData(){
        File file = new File("roverinstruction.txt");
        String[] lines = new String[10];
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            int x = 0;
            String str = "";

            while((str=buffReader.readLine()) !=null) {
                System.out.println(str);
                lines[x] = str;
                x++;
            }

            if(x > 3){
                throw new Exception();
            }

            System.out.println("\n");
        }
        catch(Exception e){
            System.out.println("\n++++++++++++++++Fatal error failed to read instructions+++++++++++++++++++\n");
            System.out.println("Exiting terminal...");
            System.exit(1);
        }

        return lines;

    }



}
