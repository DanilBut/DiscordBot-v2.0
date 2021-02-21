package com.discord.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@PropertySource("classpath:parser.properties")
@Scope("prototype")
public class Connection {

    @Value("${api.FortniteTracker}")
    private static String API;

    public HttpURLConnection getConnection(String requestUrl) throws IOException {
        java.net.URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("TRN-Api-Key", API);
        return connection;
    }
}
