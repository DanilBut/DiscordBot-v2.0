package com.discord.service;

import com.discord.command.Command;
import com.discord.model.Query;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public interface MessageReceiverService {
    void messageReceiver(Message message);

}
