package com.discord;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.LoginException;
import java.io.IOException;

@SpringBootApplication
public class DiscordBotApplication {

    public static void main(String[] args) throws IOException, LoginException {

        SpringApplication.run(DiscordBotApplication.class, args);
    }

}
