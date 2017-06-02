/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

/**
 *
 * @author Shaula Balqis Z
 */
public class timer1 {

private long startTime = 0;
private long stopTime = 0;
private boolean running = false;


public void start() {
    this.startTime = System.currentTimeMillis();
    this.running = true;
}


public void stop() {
    this.stopTime = System.currentTimeMillis();
    this.running = false;
}


//elaspsed time in milliseconds
public long getElapsedTime() {
    long elapsed;
    if (running) {
         elapsed = (System.currentTimeMillis() - startTime);
    }
    else {
        elapsed = (stopTime - startTime);
    }
    return elapsed;
}


//elaspsed time in seconds
public double getElapsedTimeSecs() {
    double elapsed;
    if (running) {
        elapsed = ((System.currentTimeMillis() - startTime) / 1000.000);
    }
    else {
        elapsed = ((stopTime - startTime) / 1000.000);
    }
    return elapsed;
}




//sample usage
/*public static void main(String[] args) {
    timer1 s = new timer1();
    s.start();
    //code you want to time goes here
    s.stop();
    System.out.println("elapsed time in milliseconds: " + s.getElapsedTime());
}*/
}   
