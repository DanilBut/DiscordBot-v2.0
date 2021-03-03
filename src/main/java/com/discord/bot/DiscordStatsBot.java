package com.discord.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
public class DiscordStatsBot {
    @Value("${bot.Token}")
    private String BOT_TOKEN;

    private DiscordBot discordBot;

    @Autowired
    public DiscordStatsBot(DiscordBot discordBot) {
        this.discordBot = discordBot;
    }


    @PostConstruct
    public void startBot() throws LoginException {
        JDA jdaBuilder = JDABuilder.createDefault(BOT_TOKEN).build();
        jdaBuilder.addEventListener(discordBot);
    }

}
