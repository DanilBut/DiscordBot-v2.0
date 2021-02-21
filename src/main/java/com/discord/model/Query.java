package com.discord.model;

import com.discord.command.Command;
import com.discord.model.entity.Player;
import lombok.*;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ToString
public class Query {
    private String nickname;
    private Command command;
}
