package com.example;
import processing.core.PApplet;
public class PMain extends PApplet{
 
 public void settings() {
 size(200, 200);
 }
 public void setup() {}
 public void draw() {
 background(255);
 circle(frameCount%width, height/2, 50);
 }
 public static void main(String args[]) {
 PApplet.main(PMain.class.getName());
 }
}