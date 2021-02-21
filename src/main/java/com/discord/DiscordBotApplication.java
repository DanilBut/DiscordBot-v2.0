package com.discord;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class DiscordBotApplication {

    private static final Logger logger = LoggerFactory.getLogger(DiscordBotApplication.class);

    public static void main(String[] args) throws IOException, LoginException {
        logger.trace("The DiscordBotApplication started with arguments: {}.", Arrays.toString(args));
        SpringApplication.run(DiscordBotApplication.class, args);
    }

}
