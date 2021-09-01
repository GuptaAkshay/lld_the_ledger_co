package dev.guptaakshay.command;

import dev.guptaakshay.model.Command;
import dev.guptaakshay.model.Payment;
import dev.guptaakshay.service.LoanService;
import dev.guptaakshay.util.Utils;

import java.util.List;

public class PaymentCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "PAYMENT";


    public PaymentCommandExecutor(LoanService loanService) {
        super(loanService);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        if(params.size() != 4)
            return false;
        return true;
    }

    @Override
    public void execute(Command command) {
        List<String> params = command.getParams();
        String bankName = params.get(0);
        String borrowerName = params.get(1);
        String loanId = Utils.generateLoanId(bankName, borrowerName);

        Integer lumpSum = Integer.parseInt(params.get(2));
        Integer afterEmi = Integer.parseInt(params.get(3));

        Payment payment = new Payment(lumpSum, afterEmi);
        loanService.acceptPayment(loanId, payment);
    }
}
