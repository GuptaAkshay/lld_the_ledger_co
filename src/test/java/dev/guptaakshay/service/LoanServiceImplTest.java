package dev.guptaakshay.service;

import dev.guptaakshay.dao.LoanDao;
import dev.guptaakshay.dao.LoanDaoImpl;
import dev.guptaakshay.exception.InvalidInputException;
import dev.guptaakshay.exception.NoLoanFoundException;
import dev.guptaakshay.model.BalanceResponse;
import dev.guptaakshay.model.Loan;
import dev.guptaakshay.model.Payment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {

    private static LoanDao loanDao;
    private static LoanService loanService;

    @BeforeAll
    static void initialize(){
         loanDao = new LoanDaoImpl();
         loanService = new LoanServiceImpl(loanDao);
    }

    @Test
    void provideLoanCreateLoan() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "IDIDI_Dale";
        assertEquals(loan, loanService.provideLoan(loan));
        assertEquals(loan, loanDao.getLoan(loanId));
    }

    @Test
    void acceptPaymentValid() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "IDIDI_Dale";
        loanService.provideLoan(loan);
        Payment payment = new Payment(1000, 5);
        assertTrue(loanService.acceptPayment(loanId, payment));
        assertEquals(payment, loanDao.getPaymentsForLoan(loanId).get(0));
    }

    @Test
    void acceptPaymentInValidForNoLoan() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "IDIDI_Dale2";
        loanService.provideLoan(loan);
        Payment payment = new Payment(1000, 5);
        assertThrows(NoLoanFoundException.class, () -> loanService.acceptPayment(loanId, payment));
    }

    @Test
    void acceptPaymentInValidInputEmptyLoanId() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "";
        loanService.provideLoan(loan);
        Payment payment = new Payment(1000, 5);
        assertThrows(InvalidInputException.class, () -> loanService.acceptPayment(loanId, payment));
    }

    @Test
    void acceptPaymentInValidInputNullPayment() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "IDIDI_Dale";
        loanService.provideLoan(loan);
        Payment payment = null;
        assertThrows(InvalidInputException.class, () -> loanService.acceptPayment(loanId, payment));
    }

    @Test
    void fetchBalanceValid() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "IDIDI_Dale";
        loanService.provideLoan(loan);
        BalanceResponse balanceResponse = new BalanceResponse("IDIDI", "Dale", 1000, 55);
        assertEquals(balanceResponse, loanService.fetchBalance(loanId, 5));
    }

    @Test
    void fetchBalanceInValid() {
        Loan loan = new Loan("IDIDI", "Dale", 10000D, 5D, 4D);
        String loanId = "IDIDI_Dale";
        loanService.provideLoan(loan);
        BalanceResponse balanceResponse = new BalanceResponse("IDIDI", "Dale2", 1000, 55);
        assertNotEquals(balanceResponse, loanService.fetchBalance(loanId, 5));
    }

    @Test
    void fetchBalanceWhenPaymentMade() {
        Loan loan = new Loan("MBI", "Dale", 10000D, 3D, 7D);
        Payment payment = new Payment(2000, 0);
        loan.getPayments().add(payment);
        String loanId = "MBI_Dale";
        loanService.provideLoan(loan);
        BalanceResponse balanceResponse1 = new BalanceResponse("MBI", "Dale", 2000, 30);
        assertEquals(balanceResponse1, loanService.fetchBalance(loanId, 0));
        BalanceResponse balanceResponse2 = new BalanceResponse("MBI", "Dale", 3348, 26);
        assertEquals(balanceResponse2, loanService.fetchBalance(loanId, 4));
        BalanceResponse balanceResponse3 = new BalanceResponse("MBI", "Dale", 12100, 0);
        assertEquals(balanceResponse3, loanService.fetchBalance(loanId, 30));
    }
}