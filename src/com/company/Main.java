package com.company;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);

        /*Gpio.wiringPiSetupGpio();
        int pin = 12;
        int pin2 = 35;
        pin = pin2;

        Gpio.pinMode(pin, Gpio.PWM_OUTPUT);
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetClock(192);
        Gpio.pwmSetRange(2000);

        Gpio.pinMode(pin2, Gpio.PWM_OUTPUT);
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetClock(192);
        Gpio.pwmSetRange(2000);*/


        while (true) {
            System.out.print("\n Gib einen Wert ein");
            int Eingabe = scanner.nextInt();
            if(Eingabe != 0){
                pin.pulse(1, true);
            }else{
                gpio.shutdown();
                return;
            }

            //Gpio.pwmWrite(pin, scanner.nextInt());
            //System.out.print("\n Gib einen zweiten Wert ein");
            //Gpio.pwmWrite(pin2, scanner.nextInt());

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
