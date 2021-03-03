package com.discord.service.handler;


import com.discord.bot.config.Config;
import com.discord.model.Player;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleHandler {
    private Guild guild;


    public void handlerRoles(Player player, GuildMessageReceivedEvent event) {
        guild = event.getGuild();
        Member member = event.getMember();

        double kd = player.getHigherKd();

        Role newRankRole = getNewRankRole(player.getRank());
        Role newKdRole = getNewKdRole(kd);

        Role oldKdRole = getOldKdRoleFromMember(member);
        Role oldRankRole = getOldRankRoleFromMember(member);

        updateMemberRoles(member, getRolesToAdd(newRankRole, newKdRole), getRolesToRemove(oldRankRole, oldKdRole));

    }

    private void updateMemberRoles(Member member, List<Role> rolesToAdd, List<Role> rolesToRemove) {
        guild.modifyMemberRoles(member, rolesToAdd, rolesToRemove).queue();
    }

    private List<Role> getRolesToAdd(Role rank, Role kd) {
        List<Role> list = new ArrayList<>();
        if (rank != null)
            list.add(rank);
        if (kd != null)
            list.add(kd);
        return list;
    }

    private List<Role> getRolesToRemove(Role rank, Role kd) {
        List<Role> list = new ArrayList<>();
        if (rank != null)
            list.add(rank);
        if (kd != null)
            list.add(kd);
        return list;
    }

    private Role getNewKdRole(double kd) {

        if (kd > 2.0d) {
            return guild.getRolesByName("2.0+", true).get(0);
        }
        Optional<Role> optional = Config.getKdRoles(guild)
                .entrySet()
                .stream()
                .filter(k -> k.getKey() > kd)
                .map(Map.Entry::getValue)
                .findFirst();

        Role role = null;
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    private Role getNewRankRole(String rank) {
        if (!containsRank(rank)) {
            return null;
        }
        if (Integer.parseInt(rank) > 300)
            return guild.getRolesByName("300+", true).get(0);

        int playerRank = Integer.parseInt(rank);
        Optional<Role> optional = Config.getRankRoles(guild)
                .entrySet()
                .stream()
                .filter(r -> r.getKey() > playerRank)
                .map(Map.Entry::getValue)
                .findFirst();

        Role role = null;
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    private Role getOldKdRoleFromMember(Member member) {
        Optional<Role> role = Config.getKdRoles(guild)
                .values()
                .stream()
                .filter(v -> member.getRoles().contains(v))
                .findFirst();
        return role.orElse(null);
    }

    private Role getOldRankRoleFromMember(Member member) {
        Optional<Role> role = Config.getRankRoles(guild)
                .values()
                .stream()
                .filter(r -> member.getRoles().contains(r))
                .findFirst();
        return role.orElse(null);
    }

    private boolean containsRank(String rank) {
        return !rank.equalsIgnoreCase("UNKNOWN");
    }
}