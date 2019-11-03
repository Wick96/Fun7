package com.luka.fun7;

import com.google.gson.Gson;
import spark.Request;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

class AdsFeature {
    private final String USERNAME = "xxxxxx";
    private final String PASSWORD = "xxxxxx";
    private final String URL = "https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner?countryCode=";
    private final String OK_RESPONSE = "sure, why not!";

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final Gson gson = new Gson();

    private boolean isEnabled;

    AdsFeature(Request request) {
        String countryCode = "";

        try {
            countryCode = URLEncoder.encode(request.queryParams("cc"), StandardCharsets.UTF_8.toString());
        } catch (Exception ignored) {
            //Silence fail because we want to API return "disable" in case something gone wrong.
        }

        isEnabled = areAdsAvailable(countryCode);
    }

    private boolean areAdsAvailable(String countryCode) {
        HttpRequest apiRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL + countryCode))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(apiRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                HashMap body = gson.fromJson(response.body(), HashMap.class);
                return body.get("ads").equals(OK_RESPONSE);
            }
        } catch (Exception ignored) {
            //Silence fail because we want to API return "disable" in case something gone wrong.
        }

        return false;
    }

    @Override
    public String toString() {
        return isEnabled ? "enable" : "disable";
    }
}
