package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static int lnCounter;
    public static void main(String[] args) {
        TrackControl mainTrack = new TrackControl(17,22);
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        new ConnectionThread((speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + time.format(new Date()));
            mainTrack.setTrack(speed,steering);
        }).start();
    }
}
