package com.company;

import java.net.*;

import static com.company.Main.println;

public class ConnectionThread extends Thread {

    private Listener listener;
    private DatagramSocket socket;
    private DatagramPacket packet;

    ConnectionThread(Listener listener) throws SocketException, UnknownHostException {
        this.listener = listener;
        socket = new DatagramSocket();
        socket.setSoTimeout(1000);
        packet = new DatagramPacket(new byte[6], 6, new InetSocketAddress("192.168.13.38", 5004));
    }

    @Override
    public void run() {
        println("Try to connect to app");
        while (true) {
            try {
                socket.send(packet);
                socket.receive(packet);
                listener.update(packet.getData()[4], packet.getData()[5]);
            } catch (Exception e) {
                listener.update((byte) 50, (byte) 50);
                println("Connection lost because of " + e.getMessage() + "\nTry to connect to app");
                sleep(25);
            }
        }
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
