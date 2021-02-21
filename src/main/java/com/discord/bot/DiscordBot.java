package com.discord.bot;

import com.discord.service.impl.MessageReceiverServiceImpl;
import com.discord.service.impl.ReplyMessageServiceImpl;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.entities.DataMessage;
import net.dv8tion.jda.internal.entities.ReceivedMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class DiscordBot extends ListenerAdapter {

    private DiscordBotFacade discordBotFacade;

    private static final Logger logger = LoggerFactory.getLogger(DiscordBot.class);

    @Autowired
    public DiscordBot(DiscordBotFacade discordBotFacade) {
        this.discordBotFacade = discordBotFacade;
    }

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {


        if (!event.getAuthor().isBot()) {
            logger.info("The Bot received command from nickname: {} , message: {}",
                    event.getMember().getEffectiveName(),
                    event.getMessage().getContentRaw());
            MessageAction messageAction = discordBotFacade.handleEvent(event);
            if (messageAction != null) {
//                messageAction.delay(Duration.ofSeconds(20)).flatMap(Message::delete).queue();
                messageAction.queue();

                logger.info("The bot sent a reply message.");
            }

        }
    }


}
