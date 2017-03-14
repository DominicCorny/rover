package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static int lnCounter;
    public static void main(String[] args) {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        ServoControl testSpeed = new ServoControl();
        ServoControl testSteering = new ServoControl(23);
        new ConnectionThread((speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + time.format(new Date()));
            speed -= 50;
            speed /= 4;
            speed +=50;
            testSpeed.setValue(speed);
            steering -= 50;
            steering *= -1;
            steering /= 3;
            steering += 50;
            testSteering.setValue(steering);
        }).start();
    }
}
