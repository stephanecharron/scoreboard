package com.stephanecharron;

import com.stephanecharron.client.ClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreboardApplication {

    @Autowired
    public ClientHandler clientHandler;

	public static void main(String[] args) {
		SpringApplication.run(ScoreboardApplication.class, args);
	}
}
