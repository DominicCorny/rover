package com.company;

/**
 * Created by kernd on 21.03.2017.
 */

public class CarControl {
    ServoControl motorSpeed;
    ServoControl servoSteering;
    public  CarControl(){
        motorSpeed = new ServoControl();
        servoSteering = new ServoControl(23);
    }
    public void setValue(int speed,int steering){
        speed -= 50;
        speed /= 4;
        speed +=50;
        motorSpeed.setValue(speed);
        steering -= 50;
        steering *= -1;
        steering /= 3;
        steering += 50;
        servoSteering.setValue(steering);
    }
}
