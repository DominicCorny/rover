package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

class Util {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss:SSS");
    private static Date date = new Date();

    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }

    static int readInt(byte[] value) {
        return value[0] << 24 |
                (value[1] & 255) << 16 |
                (value[2] & 255) << 8 |
                value[3] & 255;
    }

    static void println(String s) {
        date.setTime(System.currentTimeMillis());
        System.out.println(dateFormat.format(date) + '\t' + s);
    }
}
