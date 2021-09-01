package dev.guptaakshay.mode;

import dev.guptaakshay.command.CommandExecutor;
import dev.guptaakshay.command.CommandFactory;
import dev.guptaakshay.model.Command;

import java.io.IOException;

public abstract class Mode {

    private CommandFactory commandFactory;

    public Mode(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    protected void processCommand(Command command){
        CommandExecutor commandExecutor = commandFactory.getCommandExecutor(command);
        if(commandExecutor.validate(command)){
            commandExecutor.execute(command);
        }
        else {
            //todo throw invalid command
        }
    }

    public abstract void process() throws IOException;
}
