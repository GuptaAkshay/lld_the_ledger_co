package dev.guptaakshay.dao;

import dev.guptaakshay.model.EMI;
import dev.guptaakshay.model.Loan;
import dev.guptaakshay.model.Payment;

import java.util.List;

public interface LoanDao {

    public Loan saveLoan(Loan saveLoan);
    public boolean updatePaymentForLoan(String loanId, Payment payment);

    public Loan getLoan(String loanId);
    public List<Payment> getPaymentsForLoan(String loanId);
    public EMI getEmiDetailsForLoan(String loanId);
}
