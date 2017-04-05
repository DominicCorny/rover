package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectionThread extends Thread {

    private Listener listener;
    private DatagramSocket socket;
    private DatagramPacket packet;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss:SSS");
    private Date date = new Date();

    ConnectionThread(InetSocketAddress address, Listener listener) throws SocketException {
        this.listener = listener;
        socket = new DatagramSocket();
        socket.setSoTimeout(1000);

        byte[] data = new byte[6];
        packet = new DatagramPacket(data, 0, data.length, address);
    }

    @Override
    public void run() {
        println("Try to connect to server");
        while (true) {
            try {
                socket.send(packet);
                socket.receive(packet);
                listener.update(packet.getData()[4], packet.getData()[5]);
            } catch (Exception e) {
                listener.update((byte) 50, (byte) 50);
                println("Connection lost because of " + e.getMessage() + "\nTry to connect to server");
                sleep(25);
            }
        }
    }

    private void println(String s) {
        date.setTime(System.currentTimeMillis());
        System.out.println(dateFormat.format(date) + '\t' + s);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }

    interface Listener {
        void update(byte speed, byte steering);
    }
}
