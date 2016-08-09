package com.stephanecharron.config;

import com.stephanecharron.client.ClientHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by stephanecharron on 16-08-08.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan
public class ScoreboardConfig {

    @Bean
    ClientHandler clientHandler (){
        return new ClientHandler();
    }


}
