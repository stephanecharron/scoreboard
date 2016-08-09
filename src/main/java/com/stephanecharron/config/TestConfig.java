package com.stephanecharron.config;

import com.stephanecharron.client.ConsoleExtracter;
import com.stephanecharron.command.*;
import com.stephanecharron.command.Error;
import com.stephanecharron.domain.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by stephanecharron on 16-08-08.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestConfig {


    @Bean
    public Game game() {
        return new Game();
    }

    @Bean
    CommandHandler commandHandler (){
        return new CommandHandler();
    }

    @Bean
    ConsoleExtracter consoleExtracter (){
        return new ConsoleExtracter();
    }

    @Bean
    CommandStrategies commandValidation (){ return new CommandStrategies(); }

    @Bean
    public AddGoal addGoal() {
        return new AddGoal();
    }

    @Bean
    public StartGame startGame() {
        return new StartGame();
    }

    @Bean
    public PrintGame printGame() {
        return new PrintGame();
    }

    @Bean
    public com.stephanecharron.command.Error error() {
        return new Error();
    }

    @Bean
    public EndGame endGame() {return new EndGame();
    }


}
