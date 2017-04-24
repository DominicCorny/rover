package com.company;

import java.io.IOException;

import static com.company.Util.println;

public class Main {
    static byte fotoalt = 0;
    public static void main(String[] args) throws Exception {
        VideoStream cam = new VideoStream();
        //TrackControl mainTrack = new TrackControl(17,22);
        //ServoControl Motor1 = new ServoControl(17);
        new ConnectionThread((speed, steering, foto) -> {
            println("speed = " + speed + " steering = " + steering + "foto:" + foto);
            //mainTrack.setTrack(speed,steering);
            //Motor1.setValue(speed);
            if(foto > fotoalt) {
                fotoalt++;
                try {
                    cam.tackePicture();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}