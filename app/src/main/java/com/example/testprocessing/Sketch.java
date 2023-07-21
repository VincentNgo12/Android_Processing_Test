package com.example.testprocessing;

import android.util.Log;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {
    private final int screenWidth = 800;
    private final int screenHeight = 1000;
    private ArrayList<PVector> drawing = new ArrayList<PVector>();
    private String state = "Draw";


    public void mousePressed(){
        drawing = new ArrayList<PVector>();
        state = "Draw";
    }

    public void mouseReleased(){
        state = "Done";
    }


    public void settings() {
        size(screenWidth, screenHeight);
    }

    public void setup() {

    }

    public void draw() {
        background(0);
        if (state.equals("Draw")) {
            PVector point = new PVector(mouseX-screenWidth/2,mouseY-screenHeight/2);
            drawing.add(point);
            noFill();
            stroke(255);
            strokeWeight(16);
            beginShape();
            for(PVector vector : drawing){
                vertex(vector.x+screenWidth/2,vector.y+screenHeight/2);
            }
            endShape(CLOSE);
        }else{
            Log.d("Drawing ArrayList", drawing.toString());
            //fill(255);
            beginShape();
            for(PVector vector : drawing){
                vertex(vector.x+screenWidth/2,vector.y+screenHeight/2);
            }
            endShape(CLOSE);
        }
    }

    public ArrayList<PVector> getDrawing() {
        return drawing;
    }
}
