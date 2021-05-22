package application;

import java.sql.*;
// import java.text.SimpleDateFormat;
// import java.util.Calendar;

public class testTinh2Ngay {
    public static void main(String[] args) {
        // cách 1
        // Calendar c1 = Calendar.getInstance();
        // Calendar c2 = Calendar.getInstance();
        // Date ngay1 = Date.valueOf("2021-04-15");
        // Date ngay2 = Date.valueOf("2021-05-17");
        // c1.setTime(ngay1);
        // c2.setTime(ngay2);
        // long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 *
        // 1000);
        // long gio = (c2.getTime().getTime() - c1.getTime().getTime()) / (3600 * 1000);
        // long gioCon = noDay * 24 - gio;
        // System.out.println(noDay);
        // System.out.println(gio);
        // System.out.println(gioCon);

        // cách 2
        // Timestamp ts1 = Timestamp.valueOf("2021-05-15 00:00:00");
        // Timestamp ts2 = Timestamp.valueOf("2021-05-15 23:00:00");
        // Date ngay1 = new Date(ts1.getTime());
        // java.util.Date ngay2 = new java.util.Date(ts2.getTime());
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        // System.out.println(sdf.format(ngay1));
        // System.out.println(sdf.format(ngay2));

        // Calendar c1 = Calendar.getInstance();
        // Calendar c2 = Calendar.getInstance();
        // Timestamp ts1 = Timestamp.valueOf("2021-05-14 03:00:00");
        // Timestamp ts2 = Timestamp.valueOf("2021-05-15 23:00:00");
        // c1.setTime(ts1);
        // c2.setTime(ts2);
        // long day = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 *
        // 1000);
        // long gio = (c2.getTime().getTime() - c1.getTime().getTime()) / (3600 * 1000);
        // long gioCon = gio - day * 24;
        // System.out.println(day);
        // System.out.println(gio);
        // System.out.println(gioCon);

        long ml = System.currentTimeMillis();
        Date now = new Date(ml);
        System.out.println(now);
        
        ml = ml / 86400000 * 86400000;
        Date now2 = new Date(ml);
        System.out.println(now2);
    }
}
