package com.company;

import static com.company.Util.println;

public class Main {

    public static void main(String[] args) throws Exception {
        //TrackControl mainTrack = new TrackControl(17,22);
        new ConnectionThread((speed, steering) -> {
            println("speed = " + speed + " steering = " + steering);
            //mainTrack.setTrack(speed,steering);
        }).start();
    }
}
