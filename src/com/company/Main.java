package com.company;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        /*try {
            VideoStream cam = new VideoStream();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("fatal problem in Main");
            e.printStackTrace();
        }*/
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("roverpi.cf"), 3841);

        TrackControl mainTrack = new TrackControl(17,22);

        new ConnectionThread(address, (speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + time.format(new Date()));
            mainTrack.setTrack(speed,steering);
        }).start();
    }
}
