package calendar;
import java.util.*;
import java.time.*;

/**
 *   printCalendarOnMonth() method which show all days in this month & current day will be marked "*" symbol
 *   printCalendarOfYear() method which causes printCalendarOnMonth for all months in selected year
 * */

public class Calendar {
    public static void main(String[] args) {
        printCalendarOnMonth(2020,01, LocalDate.now());
        printCalendarOfYear(2020, LocalDate.now());
    }

    public static String[] getNameMonth(){
        return new String[]{"Январь", "Февраль", "Март",
                "Апрель", "Май", "Июнь", "Июль", "Август",
                "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    }

    public static String[] getNameWeeks(){
        return new String[]{ "Пн", "Вт", "Ср", "Чт",
                "Пт", "Сб", "Вск"};
    }

    public static String onCenter(String s, int length, char ch) {
        if (s == null) return "";
        int sLength = s.length();
        if (length <= 0 || sLength == 0) return "";
        if (sLength == length) return s;
        if (sLength > length) return s.substring(0, length);
        int start = (length - sLength) / 2;
        int end = length - start - sLength;
        char[] arrStart = new char[start];
        char[] arrEnd = new char[end];
        Arrays.fill(arrStart, ch);
        Arrays.fill(arrEnd, ch);
        return String.valueOf(arrStart) + s +
                String.valueOf(arrEnd);
    }

    public static String onCenter(String s, int length) {
        return onCenter(s, length, ' ');
    }

    public static void printCalendarOnMonth(int year, int month, LocalDate currentDay){

        if (currentDay == null || month < 1 || month > 12)
            return;

        LocalDate localDate = LocalDate.of(year, month, 1);
        String[] nameOfMonth = getNameMonth();
        System.out.println(onCenter(nameOfMonth[localDate.getMonthValue() - 1], 28));
        String[] nameWeek = getNameWeeks();

        for (int i = 0; i < nameWeek.length; i++){
            System.out.print(onCenter(nameWeek[i], 4));
        }

        int indent = 0;
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        LocalDate localDate2 = localDate.withDayOfMonth(1);
        DayOfWeek currentDayOfWeek = localDate2.getDayOfWeek();

        while (firstDayOfWeek != currentDayOfWeek){
            indent++;
            localDate2 = localDate2.minusDays(1);
            currentDayOfWeek = localDate2.getDayOfWeek();
        }

        if  (indent != 0)
            System.out.println();

        for (int i = 0; i < indent; i++)
            System.out.print("   ");

        for (int i = 1, j = localDate.lengthOfMonth()+1; i < j; i++){
            if (localDate.withDayOfMonth(i).getDayOfWeek() == firstDayOfWeek)
                System.out.println();
            System.out.printf("%3d", i);
            if (localDate.withDayOfMonth(i).equals(currentDay))
                System.out.print("*");
            else
                System.out.print(" ");
        }
        System.out.println();
    }

    public static void printCalendarOfYear(int year, LocalDate currentDate){
        System.out.println(onCenter("Календарь на " + year + " год", 28));
        for (int i = 0; i < 12; i++){
            System.out.println();
            printCalendarOnMonth(year, i, currentDate);
        }

    }
}
