package com.company;

public enum Direction {

    //enum for different variations of directions
    NORTH_FACED(0, "N"), EAST_FACED(1, "E"), SOUTH_FACED(2, "S"), WEST_FACED(3, "W");

    //initialization of variables
    private int id;
    private String desc;

    //constructor
    private Direction(int id,  String description) {
        this.id = id;
        this.desc = description;
    }

    //getter
    public int getId() {
        return( this.id );
    }

    //setter
    public String getDescription() {
        return( this.desc );
    }
}
