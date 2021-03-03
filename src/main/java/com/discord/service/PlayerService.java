package com.discord.service;

import com.discord.model.Player;

import java.io.IOException;

public interface PlayerService {
    Player getPlayer() throws IOException;
}
