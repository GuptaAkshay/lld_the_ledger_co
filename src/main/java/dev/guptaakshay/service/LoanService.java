package dev.guptaakshay.service;

import dev.guptaakshay.exception.InvalidInputException;
import dev.guptaakshay.model.BalanceResponse;
import dev.guptaakshay.model.Loan;
import dev.guptaakshay.model.Payment;

public interface LoanService {

    Loan provideLoan(Loan loan);

    boolean acceptPayment(String loanId, Payment payment) throws InvalidInputException;

    BalanceResponse fetchBalance(String loanId, Integer emiNo);

}
