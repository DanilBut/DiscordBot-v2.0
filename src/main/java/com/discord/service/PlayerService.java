package com.discord.service;

import com.discord.model.Query;
import com.discord.model.entity.Player;

import java.io.IOException;

public interface PlayerService {
    Player getPlayer() throws IOException;
}
