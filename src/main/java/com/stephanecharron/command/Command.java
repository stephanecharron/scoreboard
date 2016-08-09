package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    public void execute(ConsoleEntry consoleEntry);
}
