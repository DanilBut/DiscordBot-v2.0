package com.discord.bot.config;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;

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


    public static List<Role> getRankRoles(Guild guild) {
        List<Role> rolesRank = new ArrayList<>();
        rolesRank.add(guild.getRolesByName("50", true).get(0));
        rolesRank.add(guild.getRolesByName("100", true).get(0));
        rolesRank.add(guild.getRolesByName("150", true).get(0));
        rolesRank.add(guild.getRolesByName("300", true).get(0));
        rolesRank.add(guild.getRolesByName("300+", true).get(0));
        return rolesRank;
    }


    public static List<Role> getKdRoles(Guild guild) {
        List<Role> rolesKd = new ArrayList<>();
        rolesKd.add(guild.getRolesByName("0-0.5", true).get(0));
        rolesKd.add(guild.getRolesByName("0.5-1.0", true).get(0));
        rolesKd.add(guild.getRolesByName("1.0-1.5", true).get(0));
        rolesKd.add(guild.getRolesByName("1.5-2.0", true).get(0));
        rolesKd.add(guild.getRolesByName("2.0+", true).get(0));
        return rolesKd;
    }
}
