package com.company;

import java.net.*;

import static com.company.Util.println;

public class ConnectionThread extends Thread {

    private Listener listener;
    private DatagramSocket socket;
    private DatagramPacket packet;

    ConnectionThread(Listener listener) throws SocketException, UnknownHostException {
        this.listener = listener;
        socket = new DatagramSocket(8765);
        socket.setSoTimeout(1000);
        packet = new DatagramPacket(new byte[11], 11, new InetSocketAddress("192.168.2.101", 8765));
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
                    listener.update(packet.getData()[8], packet.getData()[9],packet.getData()[10]);
                }
            } catch (Exception e) {
                //listener.update((byte) 50, (byte) 50,(byte) 0);
                println("Connection lost because of " + e.getMessage() + "\nTry to connect to app");
                Util.sleep(25);
            }
        }
    }

    interface Listener {
        void update(byte speed, byte steering,byte foto);
    }
}
