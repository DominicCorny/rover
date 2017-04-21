package com.company;

import static com.company.Util.println;

public class Main {

    public static void main(String[] args) throws Exception {
        VideoStream cam = new VideoStream();
        //TrackControl mainTrack = new TrackControl(17,22);
        //ServoControl Motor1 = new ServoControl(17);
        new ConnectionThread((speed, steering) -> {
            println("speed = " + speed + " steering = " + steering);
            //mainTrack.setTrack(speed,steering);
            //Motor1.setValue(speed);
        }).start();
    }
}