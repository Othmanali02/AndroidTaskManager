package com.example.myapplication;

public class Errand extends Errands {
    private String name;
    private String status;
    private int id;
    public Errand(String name, String status, int id) {
        super();
        this.name = name;
        this.status = status;
        this.id = id;
    }

    public String getName(){return name;}
    public String getStatus(){return status;}

    public int getId(){return id;}


    @Override
    public String toString(){return id+ " - " +name + " - " + status;}
}
