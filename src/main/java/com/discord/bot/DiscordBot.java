package com.discord.bot;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscordBot extends ListenerAdapter {

    private DiscordBotFacade discordBotFacade;

    @Autowired
    public DiscordBot(DiscordBotFacade discordBotFacade) {
        this.discordBotFacade = discordBotFacade;
    }

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {
            MessageAction messageAction = discordBotFacade.handleEvent(event);
            if (messageAction != null) {
                messageAction.queue();
            }

        }
    }

}
