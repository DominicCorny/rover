package com.company;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

/**
 * Created by kernd on 04.04.2017.
 */
public class VideoStream extends Thread{
    RPiCamera piCamera;

    public VideoStream()  throws IOException, InterruptedException {
        try {
            piCamera = new RPiCamera("/home/pi/Pictures");
        } catch (FailedToRunRaspistillException e) {
            System.out.println("fatal problem with PiCamBib");
            e.printStackTrace();
        }
            piCamera.turnOffPreview();
    }
    public void run(){
        System.out.println("foto Thread started");
        BufferedImage image = null;
        try {
            image = piCamera.takeBufferedStill(1920,1080);

        System.out.println("Pic ready");
        //Path path = file1.toPath();//Paths.get("path/to/file");
        //byte[] imgBytes = Files.readAllBytes(path);
        //byte[] imgBytes = ((DataBufferByte) image.getData().getDataBuffer()).getData();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "BMP", baos);
        baos.flush();
        byte[] imgBytes = baos.toByteArray();
        baos.close();

        /*speicher bild auf Pi
        File file2 = new File("/home/pi/Desktop/file.png");
        FileOutputStream fos = new FileOutputStream(file2);
        fos.write(imgBytes);
        fos.flush();
        fos.close();*/

        System.out.println("länge bild" + imgBytes.length);
        //byte[] imgBytes = {4,5,4,5,4};
        if (imgBytes.length != 0) {
            Socket socket = new Socket("192.168.43.001", 9788);
            // Using DataOutputStream for simplicity
            OutputStream out = socket.getOutputStream();
            DataOutputStream data = new DataOutputStream(out);
            data.writeInt(imgBytes.length);
            data.write(imgBytes);
            data.flush();
            data.close();
            out.close();

        }else {
            System.out.println("kein bild!!!-----------------kein bild");
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fotothread stopped");
    }
}
/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos );
        baos.flush();

        byte[] imageInByte = baos.toByteArray();
        baos.close();

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageInByte));
        File outputfile = new File("image456.jpg");
        ImageIO.write(img,"jpg",outputfile);*/