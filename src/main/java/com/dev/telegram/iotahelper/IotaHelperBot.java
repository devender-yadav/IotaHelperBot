package com.dev.telegram.iotahelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.dev.telegram.iotahelper.utils.Constants.*;
import com.dev.telegram.iotahelper.utils.IotaTickerApi;
import com.dev.telegram.iotahelper.utils.PropertyReader;

public class IotaHelperBot extends TelegramLongPollingBot {

	private static final Logger LOGGER = LoggerFactory.getLogger(IotaHelperBot.class);

	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			String txt = update.getMessage().getText().toLowerCase();
			String msg = null;
			// processing commands
			if (txt.startsWith(SLASH)) {
				msg = commandToMsg(txt);
			}

			if (msg != null) {
				LOGGER.info(msg);
				postMsg(update, msg);
			}
		}
	}

	private String commandToMsg(String command) {

		switch (command.split("@")[0]) {

		case "/start":
			return "News: @iotatoken\nGeneral: @iotacafe\nTechnical: @iotatangle\nTrading: @iotatrader\nDevelopment: @iotadevelopment\n\nClick /help to see commands!";
		case "/help":
			return buildHelpMsg();

		case "/price":
			return "1 MIOTA = " + Double.toString(IotaTickerApi.getBitfinexPriceUsd()) + " USD (from Bitfinex)";
		// community:
		case "/discord":
			return "Join IOTA discord - " + IOTA_DISCORD_LINK
					+ "\n\nAsk in @iotatangle group if this link is not working.";
		case "/twitter":
			return "https://twitter.com/iotatoken";
		case "/reddit":
			return "https://www.reddit.com/r/Iota/";
		case "/telegram":
			return "@iotatangle\n@iotacafe (for general talks)\n@iotatrader (for trading)\n@iotatoken (for feeds)\n@iotadevelopment (for development)";

		case "/forum":
			return "http://forum.helloiota.com/";
		case "/faucet":
			return "https://www.reddit.com/r/IOTAFaucet/";
		case "/whitepaper":
			return "https://iota.org/IOTA_Whitepaper.pdf";
		case "/tutorial":
			return "1. http://learn.iota.org/\n2. http://iotasupport.com/";
		case "/news":
			return "http://iota.news/";

		// tangle tools:
		case "/explorer":
			return "1. https://iotasear.ch/\n2. https://thetangle.org/";
		case "/visualizer":
			return "1. https://tangle.blox.pm/\n2. http://live.iotaknot.com/";
		case "/spammer":
			return "http://www.iotaledger.nl/";
		case "/nodes":
			return "http://iota.dance/nodes/";

		// wallet
		case "/wallet":
			return "http://trinity.iota.org/\n\nIf you want link for the older GUI wallet, click /oldwallet";

		case "/oldwallet":
			return "Never use any online link to generate seed! In case of confusion ask the community.\nDownload wallet from here - https://github.com/iotaledger/wallet/releases";

		// exchanges
		case "/exchange":
			return "Major exchanges -\n\n1. BTC, ETH and USD pairs on Bitfinex\n2. BTC and ETH pairs on Binance\n\nCheck IOTA Markets for all the exchanges - https://coinmarketcap.com/currencies/iota/#markets\n\nClick /bitfinexlink to get bitfinex referral link";

		// others
		case "/balance0":
			return "IOTA balance is zero? Join IOTA discord and ask there.";
		case "/iotavideo":
			return "IOTA in 2 minutes - https://www.youtube.com/watch?v=ivWqqfzunhI";
		case "/partners":
			return "https://steemit.com/iota/@nateq/every-partner-and-affiliation-that-iota-has-the-tangle";
		case "/seedguide":
			return "Never use any online link to generate seed! In case of confusion ask the community.\n"
					+ seedGuide();
		case "/tip":
			return IOTA_ADDRESS + "\n\nThis is IOTA telegram community address.Thanks for the tip!";
		case "/contact":
			return "Ping me @itsmedev :)";

		case "/bitfinexlink":
			return "Get 10% discount on first month's fees‎ using IOTA telegram community Bitfinex referral link -\n"
					+ BITFINEX_REFERRAL;
		default:
			return null;
		}
	}

	private String buildHelpMsg() {

		StringBuilder builder = new StringBuilder();

		builder.append("IOTA communities:");
		builder.append(NEW_LINE);
		builder.append("/discord - IOTA discord");
		builder.append(NEW_LINE);
		builder.append("/reddit - IOTA reddit");
		builder.append(NEW_LINE);
		builder.append("/twitter - IOTA twitter");
		builder.append(NEW_LINE);
		builder.append("/telegram - IOTA telegram");
		builder.append(NEW_LINE);
		builder.append("/forum - IOTA forum");
		builder.append(NEW_LINE);

		builder.append(NEW_LINE);

		builder.append("IOTA related stuff:");
		builder.append(NEW_LINE);
		builder.append("/whitepaper - IOTA whitepaper");
		builder.append(NEW_LINE);
		builder.append("/wallet - IOTA GUI wallet");
		builder.append(NEW_LINE);
		builder.append("/faucet - IOTA reddit faucet");
		builder.append(NEW_LINE);
		builder.append("/exchange - List of IOTA exchanges");
		builder.append(NEW_LINE);
		builder.append("/tutorial - IOTA related tutorials");
		builder.append(NEW_LINE);
		builder.append("/news - IOTA news");
		builder.append(NEW_LINE);

		builder.append(NEW_LINE);

		builder.append("Tangle based projects:");
		builder.append(NEW_LINE);
		builder.append("/explorer - Tangle explorers");
		builder.append(NEW_LINE);
		builder.append("/visualizer - Tangle visualizers");
		builder.append(NEW_LINE);
		builder.append("/nodes - Remote tangle nodes");
		builder.append(NEW_LINE);
		builder.append("/spammer - Tangle spammer");
		builder.append(NEW_LINE);

		builder.append(NEW_LINE);

		builder.append("Articles and videos:");
		builder.append(NEW_LINE);
		builder.append("/balance0 - To fix 0 balance issue");
		builder.append(NEW_LINE);
		builder.append("/partners - List of IOTA partners");
		builder.append(NEW_LINE);
		builder.append("/seedguide - Guide for IOTA seed");
		builder.append(NEW_LINE);
		builder.append("/iotavideo - IOTA intro video");
		builder.append(NEW_LINE);

		builder.append(NEW_LINE);

		builder.append("/price - MIOTA current price");
		builder.append(NEW_LINE);
		builder.append("/tip - Tip IOTA telegram community");
		builder.append(NEW_LINE);
		builder.append("/contact - Contact me");

		return builder.toString();

	}

	private void postMsg(Update update, String reply) {
		SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId()).setText(reply);
		try {
			execute(message);
			LOGGER.info("sending msg!!!");
		} catch (TelegramApiException e) {
			LOGGER.error("Some error occured.", e);
		}
	}

	private String seedGuide() {
		StringBuilder builder = new StringBuilder();
		builder.append(NEW_LINE);
		builder.append("Open Terminal and type or paste the following then press enter:");
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
		builder.append("For linux:");
		builder.append(NEW_LINE);
		builder.append("cat /dev/urandom |tr -dc A-Z9|head -c${1:-81}");
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
		builder.append("For Mac:");
		builder.append(NEW_LINE);
		builder.append("cat /dev/urandom |LC_ALL=C tr -dc 'A-Z9' | fold -w 81 | head -n 1");
		return builder.toString();
	}

	public String getBotUsername() {
		return PropertyReader.getProperty("bot.username");
	}

	@Override
	public String getBotToken() {
		return PropertyReader.getProperty("bot.token");
	}

}