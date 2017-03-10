package com.company;

/**
 * Created by kernd on 09.03.2017.
 */
public class TrackControl {
    int portL;
    int portR;
    ServoControl servoLeft;
    ServoControl servoRight;
    public TrackControl(int portL, int portR) {
        servoLeft = new ServoControl(portL);
        servoRight = new ServoControl(portR);
    }
    public void setTrack(int speed,int steering){
        steering = (steering - 50)/2 * (-1); //range -25 til 25
        speed = speed / 2 + 25; //25-75
        int engineL = speed - steering;
        int engineR = speed + steering;
        engineL = (engineL - 50) * (-1) +50;
        if(engineL != 50) {
            servoLeft.setValue(engineL);
        }else {
            servoLeft.cutSignal();
        }
        if(engineR != 50) {
            servoRight.setValue(engineR);
        }else {
            servoRight.cutSignal();
        }
    }
    public void close(){
        servoLeft.Close();
        servoRight.Close();
    }
}
