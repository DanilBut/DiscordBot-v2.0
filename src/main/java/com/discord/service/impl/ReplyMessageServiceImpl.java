package com.discord.service.impl;

import com.discord.model.Query;
import com.discord.model.entity.Player;
import com.discord.service.PlayerService;
import com.discord.service.ReplyMessageService;
import com.discord.service.handler.StatsTableHandler;
import net.dv8tion.jda.api.entities.Message;
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
    private final Logger logger = LoggerFactory.getLogger(ReplyMessageServiceImpl.class);

    @Autowired
    public ReplyMessageServiceImpl(PlayerService playerService, StatsTableHandler tableHandler, Query query) {
        this.playerService = playerService;
        this.tableHandler = tableHandler;
        this.query = query;
    }

    @Override
    public MessageAction getReplyMessage(Message message) throws IOException {
        Player player = playerService.getPlayer();
        MessageAction messageAction = null;
        if (player == null) {
            logger.debug("Player with nickname {} not found.", query.getNickname());

            messageAction = message.getChannel().sendMessage("Player with nickname " + query.getNickname() + " not found!");
        } else {
            logger.debug("A player was found: {}", player);

            messageAction = message.getChannel().sendMessage(tableHandler.createTableStats(player));
        }
        return messageAction;
    }

}
