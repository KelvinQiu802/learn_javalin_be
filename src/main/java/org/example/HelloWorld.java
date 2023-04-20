package org.example;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
                    config.plugins.enableCors(cors -> {
                        cors.add(CorsPluginConfig::anyHost);
                    });
                })
                .start(7070);

        app.get("/", ctx -> ctx.result("Hello Kelvin"));
        app.get("/name", ctx -> ctx.result("Kelvin"));
    }
}