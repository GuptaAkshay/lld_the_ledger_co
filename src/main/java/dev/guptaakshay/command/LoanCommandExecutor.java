package dev.guptaakshay.command;

import dev.guptaakshay.model.Command;
import dev.guptaakshay.model.Loan;
import dev.guptaakshay.service.LoanService;

import java.util.List;

public class LoanCommandExecutor extends CommandExecutor{

    public static final String COMMAND_NAME = "LOAN";

    public LoanCommandExecutor(LoanService loanService) {
        super(loanService);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        if(params.size() != 5)
            return false;
        return true;
    }

    @Override
    public void execute(Command command) {

        List<String> params = command.getParams();
        String bankName = params.get(0);
        String borrowerName = params.get(1);
        Double principal = Double.parseDouble(params.get(2));
        Double time = Double.parseDouble(params.get(3));
        Double roi = Double.parseDouble(params.get(4));

        Loan loan = new Loan(bankName, borrowerName, principal, time, roi);
        loanService.provideLoan(loan);
    }
}
