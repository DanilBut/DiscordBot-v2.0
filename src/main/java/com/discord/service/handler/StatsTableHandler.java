package com.discord.service.handler;

import com.discord.command.Command;
import com.discord.model.Player;
import com.discord.model.Query;
import com.discord.model.Statistic;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatsTableHandler {

    private Query query;

    @Autowired
    public StatsTableHandler(Query query) {
        this.query = query;
    }

    public MessageEmbed createTableStats(Player player) {
        Command command = query.getCommand();
        EmbedBuilder infoPlayer = new EmbedBuilder();
        String title = getTitleText(command);

        List<Statistic> statistics = player.getStatistic();
        String rank = player.getRank();

        infoPlayer.setThumbnail("https://i.ibb.co/R3ykQXD/fortnite-min.png");
        infoPlayer.setTitle(title + player.getName());
        infoPlayer.addField("Solo", setStats(statistics, 0), true);
        infoPlayer.addField("Duo", setStats(statistics, 1), true);
        infoPlayer.addField("Squad", setStats(statistics, 2), true);
        infoPlayer.addField("Rank: ", rank, true);
        infoPlayer.setColor(0xf45642);

        return infoPlayer.build();
    }

    private String setStats(List<Statistic> statistic, int count) {
        String info = "\n**Счёт:** " + statistic.get(count).getScore()
                + "\n**K/D:** " + statistic.get(count).getKd()
                + "\n**Убийств:** " + statistic.get(count).getKills()
                + "\n**% побед:** " + statistic.get(count).getWinRatio()
                + "\n**кол-во побед:** " + statistic.get(count).getWinGames()
                + "\n**кол-во игр:** " + statistic.get(count).getGames();
        return info;
    }

    private String getTitleText(Command command) {
        String title = "";
        switch (command) {
            case STATS_WITH_NICKNAME:
            case STATS_WITHOUT_NICKNAME:
                title = "Общая статистика в текущем сезоне игрока - ";
                break;
            case PVP_WITH_NICKNAME:
            case PVP_WITHOUT_NICKNAME:
                title = "Общая статистика PVP игрока - ";
                break;
        }
        return title;
    }
}
