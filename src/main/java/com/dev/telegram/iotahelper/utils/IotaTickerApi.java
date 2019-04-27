package com.dev.telegram.iotahelper.utils;

import org.json.JSONArray;

public class IotaTickerApi {

	public static final String BITFINEX_IOTA_USD_TICKER = "https://api.bitfinex.com/v2/tickers?symbols=tIOTUSD";
	public static final String BITFINEX_IOTA_BTC_TICKER = "https://api.bitfinex.com/v2/tickers?symbols=tIOTBTC";
	public static final String BITFINEX_IOTA_ETH_TICKER = "https://api.bitfinex.com/v2/tickers?symbols=tIOTETH";
	
	private IotaTickerApi(){
	}
	public static Double getBitfinexPriceUsd() {
		String resp = RestUtil.fetchData(BITFINEX_IOTA_USD_TICKER);
		JSONArray arr = new JSONArray(resp);
		return (Double) ((JSONArray) arr.get(0)).get(3);
	}

	public static Double getBitfinexPriceBTC() {
		String resp = RestUtil.fetchData(BITFINEX_IOTA_BTC_TICKER);
		JSONArray arr = new JSONArray(resp);
		return (Double) ((JSONArray) arr.get(0)).get(3);
	}

	public static Double getBitfinexPriceETH() {
		String resp = RestUtil.fetchData(BITFINEX_IOTA_ETH_TICKER);
		JSONArray arr = new JSONArray(resp);
		return (Double) ((JSONArray) arr.get(0)).get(3);
	}

}
