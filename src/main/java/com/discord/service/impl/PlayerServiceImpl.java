package com.discord.service.impl;

import com.discord.command.Command;
import com.discord.exception.NoRankException;
import com.discord.model.Query;
import com.discord.model.entity.Player;
import com.discord.service.PlayerService;
import com.discord.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static com.discord.command.Command.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private Parser parser;

    @Autowired
    public PlayerServiceImpl(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Player getPlayer() throws IOException {
        return parser.parseJsonToPlayer();
    }
}
