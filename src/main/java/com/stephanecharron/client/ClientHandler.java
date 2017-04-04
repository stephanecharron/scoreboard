package com.stephanecharron.client;

import com.stephanecharron.command.CommandHandler;
import com.stephanecharron.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by stephanecharron on 16-08-08.
 */
public class ClientHandler implements CommandLineRunner {
    @Autowired
    public ConsoleExtracter consoleExtracter;

    @Autowired
    public CommandHandler commandHandler;

    @Autowired
    public Game game;

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void run(String... strings) throws RuntimeException {


        final String GREETING = "Enter scoreboard command";

        String input;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(GREETING);

        try {

            while ((input = bufferedReader.readLine()) != null) {
                commandHandler.handle(consoleExtracter.parse(input.trim()));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
