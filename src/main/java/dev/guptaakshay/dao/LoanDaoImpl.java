package dev.guptaakshay.dao;

import dev.guptaakshay.exception.NoLoanFoundException;
import dev.guptaakshay.model.EMI;
import dev.guptaakshay.model.Loan;
import dev.guptaakshay.model.Payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoanDaoImpl implements LoanDao {

    private Map<String, Loan> loanDb = null;

    public LoanDaoImpl() {
        this.loanDb = new HashMap<>();
    }

    @Override
    public Loan saveLoan(Loan saveLoan) {
        loanDb.put(saveLoan.getLoanId(), saveLoan);
        return saveLoan;
    }

    @Override
    public boolean updatePaymentForLoan(String loanId, Payment payment) {
        Loan existingLoan = getLoan(loanId);
        if (Objects.isNull(existingLoan)){
            throw new NoLoanFoundException();
        }
        List<Payment> payments = existingLoan.getPayments();
        payments.add(payment);
        loanDb.put(loanId, existingLoan);
        return true;
    }

    @Override
    public Loan getLoan(String loanId) {
        return loanDb.getOrDefault(loanId, null);
    }

    @Override
    public List<Payment> getPaymentsForLoan(String loanId) {
        Loan existingLoan = getLoan(loanId);
        if (Objects.isNull(existingLoan)){
            throw new NoLoanFoundException();
        }
        return existingLoan.getPayments();
    }

    @Override
    public EMI getEmiDetailsForLoan(String loanId) {
        Loan existingLoan = getLoan(loanId);
        if (Objects.isNull(existingLoan)){
            throw new NoLoanFoundException();
        }
        return existingLoan.getEmiDetails();
    }
}
