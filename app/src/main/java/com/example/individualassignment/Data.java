package com.example.individualassignment;

public class Data {
    private int  ID;
    private float weight;
    private float height;
    private float BMI;

    public Data(int inputID, float inputWeight, float inputHeight, float inputBMI){
        ID = inputID;
        weight = inputWeight;
        height = inputHeight;
        BMI = inputBMI;
    }

    public int getID() {
        return ID;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public float getBMI() {
        return BMI;
    }
}
