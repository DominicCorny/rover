package com.company;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
//import com.pi4j.wiringpi.Gpio;import com.pi4j.wiringpi.SoftPwm;

public class Main {

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);
        ServoControl speedServo= new ServoControl(17);
        ServoControl steeringServo = new ServoControl();

        while (true) {
            try {
                System.out.println("Try to connect");
                Socket socket = new Socket("192.168.2.110", 4587);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputstream = socket.getOutputStream();
                byte[] buffer = new byte[2];
                boolean running = true;
                outputstream.write("PI Hello".getBytes());
                System.out.println("PI Hello");
                while (running) {
                    if (inputStream.read(buffer) >= 2) {
                        byte speed = buffer[0];
                        byte steering = buffer[1];
                        speedServo.SetValue(speed);
                        steeringServo.SetValue(steering);
                        System.out.println("New Speed: " + speed + " steering: " + steering + "\n");
                        outputstream.write("PI OK".getBytes());
                    }
                }
            }catch (IOException e){
                System.out.println("Reconnecting to Server");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
