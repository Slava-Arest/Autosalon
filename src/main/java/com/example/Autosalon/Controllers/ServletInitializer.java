package com.example.Autosalon.Controllers;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer {
    SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources();
    }
}
