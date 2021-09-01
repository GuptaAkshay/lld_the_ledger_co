package dev.guptaakshay.command;

import dev.guptaakshay.model.BalanceResponse;
import dev.guptaakshay.model.Command;
import dev.guptaakshay.service.LoanService;
import dev.guptaakshay.util.Utils;

import java.util.List;

public class BalanceCommandExecutor  extends CommandExecutor{
    public static final String COMMAND_NAME = "BALANCE";

    public BalanceCommandExecutor(LoanService loanService) {
        super(loanService);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        if (params.size() != 3){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {

        String bankName = command.getParams().get(0);
        String borrowerName = command.getParams().get(1);

        String loanId = Utils.generateLoanId(bankName, borrowerName);
        Integer emiNo = Integer.parseInt(command.getParams().get(2));
        BalanceResponse balanceResponse = loanService.fetchBalance(loanId, emiNo);
        Utils.printBalanceResponse(balanceResponse);
    }
}
