package com.discord.exception;

import java.util.function.Supplier;

public class NoRankException extends RuntimeException {
    public NoRankException(String message) {
        super(message);
    }
}
