package com.nutriscore.domain;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RandomNumberApiDelegetate {
	private final static String ENDPOINT = "https://www.random.org/integers/?num=1&min=<MIN>&max=<MAX>&col=1&base=10&format=plain&rnd=new";

	private final OkHttpClient client = new OkHttpClient();

	/**
	 * Return a random number between the min and the max, using the www.random.org/integers API
	 * @param min the minimum value of the random value
	 * @param max the maximum value of the random value
	 * @return A random number between the min and the max
	 * @throws IOException Exceptions can be
	 * thrown when the response of the API in not parsable
	 */
	public Integer getRandomNumber(Integer min, Integer max) throws IOException {
		if (min == max) {
			return min;
		}

		String requestUrl = ENDPOINT.replace("<MIN>", String.valueOf(Math.min(min, max)))
				.replace("<MAX>", String.valueOf(Math.max(min, max)));
		Request request = new Request.Builder().url(requestUrl).build();

		try (Response response = client.newCall(request).execute()) {
			String responseText = response.body().string().trim();
			return Integer.valueOf(responseText);
		}
	}
}