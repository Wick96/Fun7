package com.luka.fun7;

import spark.Spark;

public class Main {
    public static void main(String[] args) {
        Spark.get("/features", (request, response) -> {
            response.type("application/json");
            return new Features(request);
        }, new JsonTransformer());
    }
}
