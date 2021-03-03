package com.discord.utils;

import com.discord.bot.config.Config;
import com.discord.command.Command;
import com.discord.exception.NoPvpException;
import com.discord.model.Player;
import com.discord.model.Query;
import com.discord.model.Statistic;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
@PropertySource("classpath:parser.properties")
public class Parser {

    private Query query;
    private final Logger logger = LoggerFactory.getLogger(Parser.class);


    @Autowired
    public Parser(Query query) {
        this.query = query;
    }

    public Player parseJsonToPlayer() throws IOException {
        logger.info("method parseJsonToPlayer started working.");
        String nickname = query.getNickname();

        HttpURLConnection con = getConnection(Config.URL_PROFILE + Config.PLATFORM_PC + nickname);
        if (isFoundProfile(con.getContentLength())) {
            return null;
        }
        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        JsonElement element = jsonParser.parse(new InputStreamReader((InputStream) con.getContent()));
        JsonObject object = element.getAsJsonObject();

        List<Statistic> statistics = parseStatistics(object);

        Player player = new Player();

        player.setName(nickname);
        player.setStatistic(statistics);
        player.setRank(findRank(nickname));
        return player;

    }

    private String findRank(String nickname) throws IOException {

        HttpURLConnection con = getConnection(Config.URL_EVENTS + Config.PLATFORM_PC + Config.REGION_EU + nickname);

        if (!isFoundEvents(con.getResponseMessage())) {
            return "UNKNOWN";
        }
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) con.getContent()));
        String rank = jsonElement.getAsJsonObject().get("points").getAsString();
        return rank;

    }

    private List<Statistic> parseStatistics(JsonObject object) {
        String[] status = null;
        Command command = query.getCommand();
        List<Statistic> list = new ArrayList<>();

        if (command == Command.STATS_WITH_NICKNAME || command == Command.STATS_WITHOUT_NICKNAME) {
            status = new String[]{"p2", "p10", "p9"};
        }
        if (command == Command.PVP_WITH_NICKNAME || command == Command.PVP_WITHOUT_NICKNAME) {
            status = new String[]{"curr_p2", "curr_p10", "curr_p9"};
        }
        try {
            for (String s : status) {

                String score = object.getAsJsonObject("stats").getAsJsonObject(s).getAsJsonObject("score").get("value").getAsString();
                String kd = object.getAsJsonObject("stats").getAsJsonObject(s).getAsJsonObject("kd").get("value").getAsString();
                String winRatio = object.getAsJsonObject("stats").getAsJsonObject(s).getAsJsonObject("winRatio").get("value").getAsString() + "%";
                String kills = object.getAsJsonObject("stats").getAsJsonObject(s).getAsJsonObject("kills").get("value").getAsString();
                String matches = object.getAsJsonObject("stats").getAsJsonObject(s).getAsJsonObject("matches").get("value").getAsString();
                String wins = object.getAsJsonObject("stats").getAsJsonObject(s).getAsJsonObject("top1").get("value").getAsString();
                list.add(new Statistic(score, kd, kills, winRatio, wins, matches));
            }
        } catch (Exception e) {
            throw new NoPvpException("Player with nickname " + query.getNickname() + " has no pvp statistics!");
        }
        return list;
    }

    private HttpURLConnection getConnection(String urlRequest) throws IOException {
        java.net.URL url = new URL(urlRequest);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("TRN-Api-Key", "ed86790e-004d-4ab7-bacd-f563df7d9ae9");
        return connection;
    }

    private boolean isFoundEvents(String responseMsg) {
        return !responseMsg.equalsIgnoreCase("Not Found");
    }

    private boolean isFoundProfile(Integer length) {
        return (length < 100);
    }

}
