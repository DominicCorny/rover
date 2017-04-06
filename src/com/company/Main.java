package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss:SSS");
    private static Date date = new Date();

    public static void main(String[] args) throws Exception {
        //TrackControl mainTrack = new TrackControl(17,22);
        new ConnectionThread((speed, steering) -> {
            println("speed = " + speed + " steering = " + steering);
            //mainTrack.setTrack(speed,steering);
        }).start();
    }

    static void println(String s) {
        date.setTime(System.currentTimeMillis());
        System.out.println(dateFormat.format(date) + '\t' + s);
    }
}
