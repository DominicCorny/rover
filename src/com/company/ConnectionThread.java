package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectionThread extends Thread {

    private Listener listener;
    private DatagramSocket socket;
    private DatagramPacket packet;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss:SSS");
    private Date date = new Date();

    ConnectionThread(InetSocketAddress address, Listener listener) {
        this.listener = listener;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(1000);
            packet = new DatagramPacket(new byte[6], 0, 6, address);
        } catch (IOException e) {
            println("WTF? Should not have happened.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        println("Try to connect to server");
        while (true) {
            try {
                socket.send(packet);
                socket.receive(packet);
                listener.update(packet.getData()[0], packet.getData()[1]);
            } catch (Exception e) {
                listener.update((byte) 50, (byte) 50);
                println("Connection lost because of " + e.getMessage() + "\nTry to connect to server");
            }
        }
    }

    private void println(String s) {
        date.setTime(System.currentTimeMillis());
        System.out.println(dateFormat.format(date) + '\t' + s);
    }

    interface Listener {
        void update(byte speed, byte steering);
    }
}
