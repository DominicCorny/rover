package com.company;

import java.net.*;

import static com.company.Util.println;

public class ConnectionThread extends Thread {

    private Listener listener;
    private DatagramSocket socket;
    private DatagramPacket packet;

    ConnectionThread(Listener listener) throws SocketException, UnknownHostException {
        this.listener = listener;
        socket = new DatagramSocket(53735);
        socket.setSoTimeout(1000);
        packet = new DatagramPacket(new byte[10], 10, new InetSocketAddress("192.168.2.101", 53735));
    }

    @Override
    public void run() {
        println("Try to connect to app");
        int lastSequenceReceived = -1;
        while (true) {
            try {
                socket.send(packet);
                socket.receive(packet);

                int sequenceNr = Util.readInt(packet.getData());
                if (sequenceNr > lastSequenceReceived) {
                    lastSequenceReceived = sequenceNr;
                    listener.update(packet.getData()[8], packet.getData()[9]);
                }
            } catch (Exception e) {
                listener.update((byte) 50, (byte) 50);
                println("Connection lost because of " + e.getMessage() + "\nTry to connect to app");
                Util.sleep(25);
            }
        }
    }

    interface Listener {
        void update(byte speed, byte steering);
    }
}
