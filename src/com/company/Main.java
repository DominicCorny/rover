package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static int lnCounter;
    public static void main(String[] args) {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        CarControl auto1 = new CarControl();
        new ConnectionThread((speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + time.format(new Date()));
            auto1.setValue(speed,steering);
        }).start();
    }
}
