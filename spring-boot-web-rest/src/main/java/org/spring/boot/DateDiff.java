package org.spring.boot;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateDiff {

	public static void main(String[] args) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.clear();
		startCalendar.set(1997, 6, 1);
		Date start = startCalendar.getTime();

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.clear();
		endCalendar.set(2015, 10, 1);
		Date end = endCalendar.getTime();

		System.out.println(start.getTime() + " " + end.getTime());

		long diff = end.getTime() - start.getTime();

		long days = TimeUnit.MILLISECONDS.toDays(diff);
		long hours = TimeUnit.MILLISECONDS.toHours(diff) % TimeUnit.DAYS.toHours(1);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % TimeUnit.HOURS.toMinutes(1);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % TimeUnit.MINUTES.toSeconds(1);

		System.out.println(days + " " + hours + " " + minutes + " " + seconds);

		long years = days % 365;
		System.out.println("Years " + years);
	}
}
