package com.company;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by kernd on 04.04.2017.
 */
public class VideoStream {
    RPiCamera piCamera;

    public VideoStream() throws IOException, InterruptedException {
        try {
            piCamera = new RPiCamera("/home/pi/Pictures");
        } catch (FailedToRunRaspistillException e) {
            System.out.println("fatal problem with PiCamBib");
            e.printStackTrace();
        }
        /*for(int i=0;i<10;i++){
            System.out.println("take Pic");
            piCamera.takeStill("An Awesome Pic.jpg");
            System.out.println("Pic ready");
        }*/
            //piCamera.setTimeout(1);
        BufferedImage image;
        piCamera.setFullPreviewOff();
        piCamera.setShutter(1);
        piCamera.setQuality(100);
        piCamera.setTimeout(1);
        //piCamera.setTimeout(3000);
        //piCamera.timelapse(true,"image.png",300);
        piCamera.enableBurst();
        for(int i=0;i<10;i++) {
            System.out.println("take Pic buffer");
            image = piCamera.takeBufferedStill(50,50);
            System.out.println("Pic ready");
        }
    }
}