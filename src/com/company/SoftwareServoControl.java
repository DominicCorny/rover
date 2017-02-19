package com.company;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class SoftwareServoControl {
    private int value;//Wert 0-100
    private int pin;

    //Pin 18 = GPIO_1 (std PWM); Pin 17 = GPIO_0;
    SoftwareServoControl(int pin){
        Gpio.wiringPiSetupGpio();
        this.pin = pin;
        SoftPwm.softPwmCreate(pin,0,200);
    }

    //den zu übermittelnden wert von 0-100 seten
    public void SetValue(int Value){
        if(Value <= 100 && Value > 0) {
            this.value = Value;
            SoftPwm.softPwmWrite(pin,((value*2)/10)+5);
        }else {
            System.out.print("\n Parameter Fehler in SSE Klasse");
        }
    }
    public int GetValue(){
        return value;
    }
    public void Close(){
        SoftPwm.softPwmStop(pin);
    }
}
