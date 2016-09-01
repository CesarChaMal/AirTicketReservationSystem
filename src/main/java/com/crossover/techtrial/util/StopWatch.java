package com.crossover.techtrial.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class StopWatch {

	public static void stopWatch(){
		//Google Stopwatch
		final Stopwatch stopwatch = Stopwatch.createStarted();

		//Sleep for few random milliseconds.
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (final InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}

		stopwatch.stop(); //optional

		System.out.println("Elapsed time ==> " + stopwatch);
		System.out.println("Elapsed time in Nanoseconds ==> " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
		System.out.println("Elapsed time in Microseconds ==> " + stopwatch.elapsed(TimeUnit.MICROSECONDS));
		System.out.println("Elapsed time in Milliseconds ==> " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
		System.out.println("Elapsed time in Seconds ==> " + stopwatch.elapsed(TimeUnit.SECONDS));
		System.out.println("Elapsed time in Minutes ==> " + stopwatch.elapsed(TimeUnit.MINUTES));
	}
}
