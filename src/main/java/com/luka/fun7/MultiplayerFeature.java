package com.luka.fun7;

import com.google.cloud.datastore.*;

import spark.Request;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class MultiplayerFeature {
    private final long SKILLED_PLAYER_REQUIREMENT = 5;

    private boolean isEnabled;

    MultiplayerFeature(Request request) {
        String countryCode = "";
        String userId = "";

        try {
            countryCode = URLEncoder.encode(request.queryParams("cc"), StandardCharsets.UTF_8.toString());
            userId = URLEncoder.encode(request.queryParams("userId"), StandardCharsets.UTF_8.toString());
        } catch (Exception ignored) {
            //Silence fail because we want to API return "disable" in case something gone wrong.
        }

        isEnabled = countryCode.toLowerCase().equals("us") && isUserSkilledPlayer(userId);
    }

    private boolean isUserSkilledPlayer(String userId) {
        long accessCount = 1;

        if (!userId.equals("")) {
            Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

            Key taskKey = datastore.newKeyFactory().setKind("ApiAccess").newKey(userId);
            Entity access = datastore.get(taskKey);

            if (access != null && !access.isNull("access_count")) {
                accessCount = access.getLong("access_count") + 1;
            }

            access = Entity.newBuilder(taskKey).set("access_count", accessCount).build();
            datastore.put(access);
        }

        return accessCount > SKILLED_PLAYER_REQUIREMENT;
    }

    @Override
    public String toString() {
        return isEnabled ? "enable" : "disable";
    }
}
