package com.company;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 3841);

        //TrackControl mainTrack = new TrackControl(17,22);

        new ConnectionThread(address, (speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + time.format(new Date()));
            //mainTrack.setTrack(speed,steering);

        }).start();
    }
}
