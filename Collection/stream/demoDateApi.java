package DemoDateformate;

//Java 8 new feature Date API and  time

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class demoDateApi
{
    public static void main(String [] args)
    {
        //Local Date() without zone
        LocalDate current_date = LocalDate.now();
        System.out.println("Todays Date : "+current_date);

        //Local time without time zone
        LocalTime current_time = LocalTime.now();
        System.out.println("Current Time : "+current_time);

        System.out.println("Today's date and time : "+current_date+" "+current_time);

        //local date time without Zone
        LocalDateTime today_date_now_time = LocalDateTime.now();
        System.out.println("today's date and now time is : "+today_date_now_time);


        //Date, time Zone based 
        ZonedDateTime zone_date_time = ZonedDateTime.now();
        System.out.println("Zone based current Date time "+zone_date_time);
    }
}
