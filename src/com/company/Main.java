package com.company;
import java.io.IOException;
import java.util.Scanner;
//import com.pi4j.wiringpi.Gpio;import com.pi4j.wiringpi.SoftPwm;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);


        //Hardware PWM
        ServoControl Servo1 = new ServoControl(17);
        boolean active = true;
        while (active) {
            System.out.print("\n Gib einen Wert ein(Hardware PWM) ((int)0-100)");
            int Eingabe = scanner.nextInt();
            if (Eingabe != 0) {
                Servo1.SetValue(Eingabe);
            } else {
                Servo1.Close();
                Servo1 = null;
                active = false;
            }
        }

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        Socket socket = new Socket("192.168.2.110", 4572);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputstream = socket.getOutputStream();

        byte[] buffer = new byte[2];
        boolean running = true;
        outputstream.write("PI Hello".getBytes());
        System.out.println("PI Hello");
        while (running) {
            try {
                if (inputStream.read(buffer) >= 2) {
                    byte speed = buffer[0];
                    byte steering = buffer[1];
                    System.out.println("New Speed: " + speed + " steering: " + steering);

                    Gpio.pinMode(3, com.pi4j.wiringpi.Gpio.PWM_OUTPUT);
                    Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
                    Gpio.pwmSetClock(384);
                    Gpio.pwmSetRange(1000);
                    Gpio.pwmWrite(3, 75);

                    outputstream.write("PI OK".getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
