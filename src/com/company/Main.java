package com.company;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("192.168.13.38"), 5004);

        //TrackControl mainTrack = new TrackControl(17,22);

        new ConnectionThread(address, (speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + time.format(new Date()));
            //mainTrack.setTrack(speed,steering);

        }).start();
    }
}
