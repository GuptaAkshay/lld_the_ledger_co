package dev.guptaakshay.command;

import dev.guptaakshay.model.Command;
import dev.guptaakshay.service.LoanService;

public abstract class CommandExecutor {

    protected LoanService loanService;

    public CommandExecutor(LoanService loanService) {
        this.loanService = loanService;
    }

    public abstract boolean validate(Command command);

    public abstract void execute(Command command);
}
