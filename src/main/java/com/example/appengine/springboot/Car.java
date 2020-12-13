package com.example.appengine.springboot;

public class Car {

    private String mark;
    private String model;
    private Long id;


    public Car(Long id, String mark, String model) {
        this.id = id;
        this.mark = mark;
        this.model = model;
    }
    public Long getId(){ return id;}
    public void setId(Long id) { this.id = id;}

    public String getMark(){
        return mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }





}
