package com.qa.opencart.utils;

public class TimeUtil {

	public final static int DEFAULT_TIMEOUT = 10;
	public final static long SMALL_TIMEOUT = 1000; // All constant variable with final keyboard should write with caps
	public final static long MEDIUM_TIMEOUT = 5000;
	public final static long LARGE_TIMEOUT = 10000;
	public final static long EXTRALARGE_TIMEOUT = 20000;

	public static void smallWait() {

		try {
			Thread.sleep(SMALL_TIMEOUT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mediumWait() {

		try {
			Thread.sleep(MEDIUM_TIMEOUT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void largeWait() {

		try {
			Thread.sleep(LARGE_TIMEOUT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void extralargeWait() {

		try {
			Thread.sleep(EXTRALARGE_TIMEOUT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Either use the above or use this custom wait in the Element Util
	 */
	public static void applyWait(long time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
