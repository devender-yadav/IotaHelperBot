package com.dev.telegram.iotahelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class IotaHelperApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(IotaHelperApp.class);

	public static void main(String[] args) {

		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new IotaHelperBot());
			LOGGER.info("IotaHelperBot has started!");
		} catch (TelegramApiException e) {
			LOGGER.error("Not able to start IotaHelperBot.", e);
		}
	}

}
