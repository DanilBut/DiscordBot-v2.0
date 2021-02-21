package com.discord.service.impl;

import com.discord.bot.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
public class DiscordBotServiceImpl {
    @Value("${bot.Token}")
    private String BOT_TOKEN;

    private DiscordBot discordBot;

    @Autowired
    public DiscordBotServiceImpl(DiscordBot discordBot) {
        this.discordBot = discordBot;
    }


    @PostConstruct
    public void startBot()  {
        JDA jdaBuilder = null;
        try {
            jdaBuilder = JDABuilder.createDefault(BOT_TOKEN).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        jdaBuilder.addEventListener(discordBot);
    }

}
