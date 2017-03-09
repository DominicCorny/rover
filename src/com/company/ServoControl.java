package com.company;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class ServoControl {
    private int value;//Wert 0-100
    private int pin;
    private boolean isHardwarePwm = false;

    private static int u = Gpio.wiringPiSetupGpio();

    //Pin 18 = GPIO_1(std PWM) ; Pin 17 = GPIO_0; no Pin = HardwarePWM
    public ServoControl(int pin){
        this.pin = pin;
        SoftPwm.softPwmCreate(pin,0,200);
    }
    //Hardware PWM
    public ServoControl() {
        isHardwarePwm = true;
        pin = 18;
        Gpio.pinMode(pin, Gpio.PWM_OUTPUT);
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetClock(192);
        Gpio.pwmSetRange(2000);
    }

    //den zu übermittelnden wert von 0-100 setzen
    public void setValue(int Value){
        if(Value <= 100 && Value >= 0) {
            this.value = Value;
            if(isHardwarePwm){
                Gpio.pwmWrite(pin,(value*2)+50);
                System.out.println("HardwarePWM Signal gesendet:" + value);
            }else {
                SoftPwm.softPwmWrite(pin,((value*2)/10)+5);
                System.out.println("SoftwarePWM Signal gesendet:" + value);
            }
        }else {
            System.out.print("\n Parameter Fehler in SSE Klasse");
        }
    }
    public void cutSignal() {
        if(isHardwarePwm){
            Gpio.pwmWrite(pin,0);
            System.out.println("HardwarePWM Signal gesendet:" + "No Signal");
        }else {
            SoftPwm.softPwmWrite(pin,0);
            System.out.println("SoftwarePWM Signal gesendet:" + "No Signal");
        }
    }

    public int getValue(){
        return value;
    }
    public void Close(){
        if(isHardwarePwm){
            Gpio.pwmWrite(pin,0);
            // ??? beende Gpio ??
        }else {
            SoftPwm.softPwmStop(pin);
        }
    }
}
