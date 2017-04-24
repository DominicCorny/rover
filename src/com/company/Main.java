package com.company;

import java.io.IOException;

import static com.company.Util.println;

public class Main {
    static byte fotoalt = 0;
    public static void main(String[] args) throws Exception {
        //TrackControl mainTrack = new TrackControl(17,22);
        //ServoControl Motor1 = new ServoControl(17);
        //for(int i=0;i<10;i++)
        new ConnectionThread((speed, steering, foto) -> {
            println("speed = " + speed + " steering = " + steering + "foto:" + foto);
            //mainTrack.setTrack(speed,steering);
            //Motor1.setValue(speed);
            if(foto > fotoalt) {
                fotoalt++;
                //cam.start();
                try {
                    VideoStream cam = new VideoStream();
                    cam.start();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("foto cheese");
            }
        }).start();
    }
}