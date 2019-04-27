package com.dev.telegram.iotahelper.utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestUtil.class);

	public static final int MAX_RETRY = 3;

	private RestUtil() {
	}

	public static String fetchData(String url) {
		int retry = 0;
		String data = null;
		while (retry <= MAX_RETRY) {
			try {
				data = getReq(url);
				break;
			} catch (Exception ex) {
				retry++;
			}
			CommonUtil.sleep();
		}
		return data;
	}

	public static String fetchData(String url, String req) {
		int retry = 0;
		String data = null;
		while (retry <= MAX_RETRY) {
			try {
				data = postReq(url, req);
				break;
			} catch (Exception ex) {
				retry++;
			}
			CommonUtil.sleep();
		}
		return data;
	}

	public static String getReq(String url) {

		Client client = ClientBuilder.newClient();
		try {
			WebTarget target = client.target(url);
			Builder builder = target.request();

			Response response = builder.get();
			return response.readEntity(String.class);

		} catch (Exception ex) {
			LOGGER.error("Error in getting response", ex);
			return null;
		} finally {
			client.close();
		}
	}

	public static String postReq(String url, String req) {
		Client client = ClientBuilder.newClient();
		try {
			WebTarget target = client.target(url);
			Response response = target.request().post(Entity.json(req));
			return response.readEntity(String.class);

		} catch (Exception ex) {
			LOGGER.error("Error in getting response", ex);
			return null;
		} finally {
			client.close();
		}
	}

}
