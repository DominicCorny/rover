package com.company;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
            piCamera.turnOffPreview();
            System.out.println("take Pic buffer");
            BufferedImage image = piCamera.takeBufferedStill();
            System.out.println("Pic ready");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos );
        baos.flush();

        byte[] imageInByte = baos.toByteArray();
        baos.close();

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageInByte));
        File outputfile = new File("image456.jpg");
        ImageIO.write(img,"jpg",outputfile);
    }
}