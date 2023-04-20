package org.example;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        initContacts();

        var app = Javalin.create(
//                config -> {
//                    config.plugins.enableCors(cors -> {
//                        cors.add(CorsPluginConfig::anyHost);
//                    });
//                }
        ).start(7070);

        app.get("/", ctx -> {
            ctx.result("Hello World");
        });

        app.get("/api/contacts", ctx -> {
            ctx.json(contacts);
        });
    }

    public static void initContacts() {
        contacts.add(new Contact("Kelvin", "123123123"));
        contacts.add(new Contact("Skye", "184748393"));
        contacts.add(new Contact("Jack", "1234283578"));
    }
}