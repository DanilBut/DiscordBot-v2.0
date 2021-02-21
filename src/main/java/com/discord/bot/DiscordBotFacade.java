package com.discord.bot;

import com.discord.command.Command;
import com.discord.exception.NicknameException;
import com.discord.exception.NoPvpException;
import com.discord.model.Query;
import com.discord.service.ReplyMessageService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DiscordBotFacade {

    private ReplyMessageService replyMessageService;
    private Query query;

    @Autowired
    public DiscordBotFacade(ReplyMessageService replyMessageService, Query query) {
        this.replyMessageService = replyMessageService;
        this.query = query;
    }

    public MessageAction handleEvent(GuildMessageReceivedEvent event) {
        MessageChannel channel = event.getMessage().getChannel();
        MessageAction messageAction = null;
        Message message = event.getMessage();
        Command command = getCommandFromMessage(message);
        if(command != Command.NON_COMMAND) {
            try {
                String nickname = getNicknameFromMessage(message);

                query.setCommand(command);
                query.setNickname(nickname);

                messageAction = replyMessageService.getReplyMessage(message);
            } catch (NicknameException | NoPvpException | IOException e) {
                messageAction = channel.sendMessage(e.getMessage());
            }
        }
        return messageAction;
    }

    private String getNicknameFromMessage(Message msg) {
        String nick = "";
        String[] arr = msg.getContentRaw().split(" ");
        if (arr.length == 1) {
            nick = msg.getMember().getEffectiveName();
        } else if (arr.length > 3) {
            throw new NicknameException("Player nickname is too long.");
        } else if (arr.length == 2)
            nick = arr[1];
        else if (arr.length == 3) {
            nick = arr[1] + " " + arr[2];
        }
        return nick;
    }

    private Command getCommandFromMessage(Message message) {
        Command command;
        String[] arr = message.getContentRaw().split(" ");
        if (arr[0].equalsIgnoreCase("/stats")) {
            command = arr.length > 1 ? Command.STATS_WITH_NICKNAME : Command.STATS_WITHOUT_NICKNAME;
        } else if (arr[0].equalsIgnoreCase("/pvp")) {
            command = arr.length > 1 ? Command.PVP_WITH_NICKNAME : Command.PVP_WITHOUT_NICKNAME;
        } else {
            command = Command.NON_COMMAND;
        }
//            switch (arr[0]) {
//                case "/stats": {
//                    if (arr.length > 1) {
//                        command = Command.STATS_WITH_NICKNAME;
//                    } else {
//                        command = Command.STATS_WITHOUT_NICKNAME;
//                    }
//                    break;
//                }
//                case "/pvp": {
//                    if (arr.length > 1) {
//                        command = Command.PVP_WITH_NICKNAME;
//                    } else {
//                        command = Command.PVP_WITHOUT_NICKNAME;
//                    }
//                    break;
//                }
//                default: {
//                    command = Command.NON_COMMAND;
//                }

        return command;
    }
}
