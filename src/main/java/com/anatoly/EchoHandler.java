package com.anatoly;

import com.botplatform.command.CommandHandler;

public class EchoHandler implements CommandHandler {

    @Override
    public String handleCommand() {
        return "Echo";
    }
}
