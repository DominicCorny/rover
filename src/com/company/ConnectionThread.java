package com.company;

import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread {

    private Listener listener;

    ConnectionThread(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[2];

        while (true) {
            System.out.println("Try to connect to server");
            try (Socket socket = new Socket("192.168.2.110", 3841)) {
                socket.setTcpNoDelay(true);
                socket.setSoTimeout(1000);
                //test connection
                if (socket.getInputStream().read() != 1) throw new IOException("read error");
                socket.getOutputStream().write(1);
                System.out.println("connected");

                while (true) {
                    if (socket.getInputStream().read(buffer) < 2) throw new IOException("read error");
                    listener.update(buffer[0], buffer[1]);
                    //respond
                    socket.getOutputStream().write(1);
                }
            } catch (Exception e) {
                System.out.println("Connection lost because of " + e.getMessage());
                listener.update((byte) 50, (byte) 50);
            }
            sleep(200);
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    interface Listener {
        void update(byte speed, byte steering);
    }
}
