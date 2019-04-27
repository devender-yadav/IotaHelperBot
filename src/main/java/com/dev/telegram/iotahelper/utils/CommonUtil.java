package com.dev.telegram.iotahelper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

	// 1 minute wait
	public static final int WAIT_INTERVAL = 1 * 60 * 1000;
	
	private CommonUtil(){
	}

	public static void sleep() {
		try {
			Thread.sleep(WAIT_INTERVAL);
		} catch (InterruptedException e) {
			LOGGER.error("Error while sleeping!!! ",e);
		}
	}

	public static double floatToDouble(float f) {

		return Math.round(f * 10000.0) / 100.0;

	}
}
