package com.company;

public class Main {
    private static int lnCounter;

    public static void main(String[] args) {
     //   ServoControl speedServo = new ServoControl();
     //   ServoControl steeringServo = new ServoControl(17);

        new ConnectionThread((speed, steering) -> {
            System.out.println("Speed = " + speed + " steering = " + steering + '\t' + lnCounter++ % 10);
      //      speedServo.SetValue(speed);
      //      steeringServo.SetValue(steering);
        }).start();
    }
}
