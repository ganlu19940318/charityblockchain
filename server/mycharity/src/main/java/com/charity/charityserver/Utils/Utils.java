package com.charity.charityserver.Utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class Utils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

    public static String getChar() {
        int length = 16;
        char[] ss = new char[length];
        int i=0;

        while (i < 8) {
            ss[i] = (char) ('A'+Math.random()*26);
            i++;
        }

        while (i < 12){
            ss[i] = (char) ('a'+Math.random()*26);
            i++;
        }
        while (i < 16){
            ss[i] = (char) ('0'+Math.random()*10);
            i++;
        }
        String str=new String(ss);
        return str;
    }
}
