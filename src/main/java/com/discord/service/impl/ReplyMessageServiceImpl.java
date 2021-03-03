package com.discord.service.impl;

import com.discord.command.Command;
import com.discord.model.Player;
import com.discord.model.Query;
import com.discord.service.PlayerService;
import com.discord.service.ReplyMessageService;
import com.discord.service.handler.RoleHandler;
import com.discord.service.handler.StatsTableHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReplyMessageServiceImpl implements ReplyMessageService {

    private PlayerService playerService;
    private StatsTableHandler tableHandler;
    private Query query;
    private RoleHandler roleHandler;
    private final Logger logger = LoggerFactory.getLogger(ReplyMessageServiceImpl.class);

    @Autowired
    public ReplyMessageServiceImpl(PlayerService playerService, StatsTableHandler tableHandler, Query query, RoleHandler roleHandler) {
        this.playerService = playerService;
        this.tableHandler = tableHandler;
        this.query = query;
        this.roleHandler = roleHandler;
    }


    @Override
    public MessageAction getReplyMessage(GuildMessageReceivedEvent event) throws IOException {
        Player player = playerService.getPlayer();
        MessageAction messageAction = null;
        Message message = event.getMessage();
        if (player == null) {
            messageAction = message.getChannel().sendMessage("Player with nickname " + query.getNickname() + " not found!");
        } else {
            if (query.getCommand() == Command.STATS_WITHOUT_NICKNAME) {
                roleHandler.handlerRoles(player, event);
            }
            messageAction = message.getChannel().sendMessage(tableHandler.createTableStats(player));
        }
        return messageAction;
    }

}
