package application;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class testTinh2Ngay {
    public static void main(String[] args) {
        // cách 1
        // Calendar c1 = Calendar.getInstance();
        // Calendar c2 = Calendar.getInstance();
        // Date ngay1 = Date.valueOf("2021-04-15");
        // Date ngay2 = Date.valueOf("2021-05-16");
        // c1.setTime(ngay1);
        // c2.setTime(ngay2);
        // long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        // System.out.println(noDay);

        // cách 2
        Timestamp ts1 = Timestamp.valueOf("2021-05-15 00:00:00");
        Timestamp ts2 = Timestamp.valueOf("2021-05-15 03:00:00");
        System.out.println(ts1.getTime() - ts2.getTime());
    }
}
