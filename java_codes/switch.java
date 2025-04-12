public class test
{
    public static void main(String args[])
    {
        int day = 4;
        String dayname;
        switch(day)
        {
            case 1:
                dayname  = "monday";
                break;
            case 2:
                dayname  = "tuesday";
                break;
            case 3:
                dayname  = "wednesday";
                break;
            case 4:
                dayname = "thursday";
                break;
            case 5:
                dayname = "friday";
                break;
            default:
                dayname = "Invalid days";
                break;
        }
        System.out.println(dayname);


    }
}