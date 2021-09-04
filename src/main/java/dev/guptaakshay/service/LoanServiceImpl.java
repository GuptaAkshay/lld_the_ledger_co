package dev.guptaakshay.service;

import dev.guptaakshay.dao.LoanDao;
import dev.guptaakshay.exception.InvalidInputException;
import dev.guptaakshay.model.BalanceResponse;
import dev.guptaakshay.model.EMI;
import dev.guptaakshay.model.Loan;
import dev.guptaakshay.model.Payment;

import java.util.List;
import java.util.Objects;

public class LoanServiceImpl implements LoanService {

    private LoanDao loanDao;

    public LoanServiceImpl(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public Loan provideLoan(Loan loan) {
        if (Objects.isNull(loan)){
            throw new InvalidInputException();
        }
        return loanDao.saveLoan(loan);
    }

    @Override
    public boolean acceptPayment(String loanId, Payment payment) throws InvalidInputException {
        if (Objects.isNull(payment) || Objects.isNull(loanId) || loanId.isEmpty()){
           throw new InvalidInputException();
        }
        return loanDao.updatePaymentForLoan(loanId, payment);
    }

    @Override
    public BalanceResponse fetchBalance(String loanId, Integer emiNo) {
        Loan loan = loanDao.getLoan(loanId);
        EMI emiDetails = loanDao.getEmiDetailsForLoan(loanId);
        List<Payment> paymentsForLoan = loanDao.getPaymentsForLoan(loanId);

        Integer remainingAmt = emiDetails.getEmiPerMonth().intValue() * emiNo;
        Integer previousPayments = paymentsForLoan.stream().filter(payment -> payment.getAfterEmi() <= emiNo)
                .mapToInt(Payment::getLumpSum).sum();
        remainingAmt += previousPayments;

        Double remainingEmi = Math.ceil((loan.getAmount() - remainingAmt) / emiDetails.getEmiPerMonth());
        if(remainingAmt > loan.getAmount()){
            remainingAmt = loan.getAmount().intValue();
        }
        BalanceResponse balanceResponse = new BalanceResponse(loan.getBankName(), loan.getBorrowerName(), remainingAmt,
                remainingEmi.intValue());

        return balanceResponse;
    }
}
