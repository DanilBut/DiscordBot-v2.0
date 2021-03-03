package com.discord.model;

import com.discord.command.Command;
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
