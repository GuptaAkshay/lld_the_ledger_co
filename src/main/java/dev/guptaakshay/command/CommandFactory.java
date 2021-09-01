package dev.guptaakshay.command;

import dev.guptaakshay.exception.InvalidCommandException;
import dev.guptaakshay.model.Command;
import dev.guptaakshay.service.LoanService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {

    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandFactory(LoanService loanService) {
        commands.put(LoanCommandExecutor.COMMAND_NAME, new LoanCommandExecutor(loanService));
        commands.put(BalanceCommandExecutor.COMMAND_NAME, new BalanceCommandExecutor(loanService));
        commands.put(PaymentCommandExecutor.COMMAND_NAME, new PaymentCommandExecutor(loanService));
    }

    public CommandExecutor getCommandExecutor(Command command) {
        CommandExecutor commandExecutor = commands.get(command.getCommand());
        if (Objects.isNull(commandExecutor)){
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }
}
