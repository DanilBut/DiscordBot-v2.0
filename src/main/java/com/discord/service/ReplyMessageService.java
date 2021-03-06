package com.discord.service;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.io.IOException;

public interface ReplyMessageService {
    MessageAction getReplyMessage(GuildMessageReceivedEvent event) throws IOException;
}
