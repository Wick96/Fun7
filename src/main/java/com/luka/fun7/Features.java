package com.luka.fun7;

import spark.Request;

class Features {
    private String multiplayer = "disable";
    private String user_support = "disable";
    private String ads = "disable";

    Features(Request request) {
        multiplayer = new MultiplayerFeature(request).toString();
        user_support = new UserSupportFeature().toString();
        ads = new AdsFeature(request).toString();
    }
}
