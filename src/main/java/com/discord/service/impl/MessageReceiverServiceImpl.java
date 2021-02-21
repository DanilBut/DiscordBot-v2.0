package com.discord.service.impl;

import com.discord.command.Command;
import com.discord.exception.NicknameException;
import com.discord.model.Query;
import com.discord.service.MessageReceiverService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class MessageReceiverServiceImpl implements MessageReceiverService {

    private final Logger logger = LoggerFactory.getLogger(MessageReceiverServiceImpl.class);
    private final Query query;

    @Autowired
    public MessageReceiverServiceImpl(Query query) {
        this.query = query;
    }

    @Override
    public void messageReceiver(Message message) {
//        String nickname = "";
//        Command command;
//        try {
//            command = getCommandFromMessage(message.getContentRaw());
//            System.out.println(command);
//            if (command != Command.NON_COMMAND) {
//                nickname = getNicknameFromMessage(message);
//                query.setCommand(command);
//                query.setNickname(nickname);
//
//                logger.info("Request has been created: {}", query);
//
//            }
//        } catch (NicknameException e) {
//            System.out.println("AAAAAAAAAAA");
//            message.getChannel().sendMessage(e.getMessage()).queue();
//        }
    }


//    private String getNicknameFromMessage(Message msg) {
//        String nick = "";
//        String[] arr = msg.getContentRaw().split(" ");
//        if (arr.length == 1) {
//            nick = msg.getMember().getEffectiveName();
//        } else if (arr.length > 3) {
//            throw new NicknameException("Player nickname is too long.");
//        } else if (arr.length == 2)
//            nick = arr[1];
//        else if (arr.length == 3) {
//            nick = arr[1] + " " + arr[2];
//        }
//        return nick;
//    }
//
//    private Command getCommandFromMessage(String msg) {
//        Command command;
//        String[] arr = msg.split(" ");
//        switch (arr[0]) {
//            case "/stats": {
//                if (arr.length > 1) {
//                    command = Command.STATS_WITH_NICKNAME;
//                } else {
//                    command = Command.STATS_WITHOUT_NICKNAME;
//                }
//                break;
//            }
//            case "/pvp": {
//                if (arr.length > 1) {
//                    command = Command.PVP_WITH_NICKNAME;
//                } else {
//                    command = Command.PVP_WITHOUT_NICKNAME;
//                }
//                break;
//            }
//            default: {
//                command = Command.NON_COMMAND;
//            }
//
//        }
//        return command;
//    }
}
