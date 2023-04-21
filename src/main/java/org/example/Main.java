package org.example;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        initContacts();

        var app = Javalin.create(
                config -> {
                    config.plugins.enableCors(cors -> {
                        cors.add(it -> {
                            it.anyHost();
                        });
                    });
                }
        ).start(7070);

        app.get("/", ctx -> {
            ctx.result("Hello World");
        });

        app.get("/api/contacts", ctx -> {
            ctx.json(contacts);
        });

        app.get("/api/contacts/{id}", ctx -> {
            try {
                int index = Integer.parseInt(ctx.pathParam("id"));
                if (index >= 0 && index < contacts.size()) {
                    ctx.json(contacts.get(index));
                } else {
                    ctx.result("Index is Out of Boundary").status(404);
                }
            } catch (NumberFormatException e) {
                ctx.result("Invalid Index").status(404);
            }
        });

        app.post("/api/contacts", ctx -> {
            try {
                String name = ctx.formParam("name");
                String phone = ctx.formParam("phone");

                if (name != null && name.length() > 0 && !name.isBlank()
                        && phone != null && phone.length() > 0 && !phone.isBlank()) {
                    contacts.add(new Contact(name, phone));
                    ctx.result("Successfully Added!").status(200);
                } else {
                    ctx.result("Invalid Name and Phone").status(404);
                }
            } catch (Exception e) {
                ctx.result("Unknown Error when Creating Contact").status(404);
            }
        });
    }

    public static void initContacts() {
        contacts.add(new Contact("Kelvin", "123123123"));
        contacts.add(new Contact("Skye", "184748393"));
        contacts.add(new Contact("Jack", "1234283578"));
    }
}