package com.discord.bot.config;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static String PREFIX = "/";

    public static String PLATFORM_PC = "/pc/";
    public static String PLATFORM_CONSOLE = "/console/";
    public static String PLATFORM_MOBILE = "/mobile/";

    public static String REGION_NAE = "NAE/";
    public static String REGION_EU = "EU/";
    public static String REGION_NAW = "NAW/";

    public static String URL_PROFILE = "https://api.fortnitetracker.com/v1/profile";
    public static String URL_EVENTS = "https://api.fortnitetracker.com/v1/powerrankings";


    public static Map<Integer, Role> getRankRoles(Guild guild) {
        Map<Integer, Role> ranks = new HashMap<>();
        ranks.put(50, guild.getRolesByName("50", true).get(0));
        ranks.put(100, guild.getRolesByName("100", true).get(0));
        ranks.put(150, guild.getRolesByName("150", true).get(0));
        ranks.put(200, guild.getRolesByName("300", true).get(0));
        ranks.put(301, guild.getRolesByName("300+", true).get(0));
        return ranks;
    }

    public static Map<Double, Role> getKdRoles(Guild guild) {
        Map<Double, Role> ranks = new HashMap<>();
        ranks.put(0.5, guild.getRolesByName("0-0.5", true).get(0));
        ranks.put(1.0, guild.getRolesByName("0.5-1.0", true).get(0));
        ranks.put(1.5, guild.getRolesByName("1.0-1.5", true).get(0));
        ranks.put(2.0, guild.getRolesByName("1.5-2.0", true).get(0));
        ranks.put(2.01, guild.getRolesByName("2.0+", true).get(0));
        return ranks;
    }

}
