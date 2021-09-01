package dev.guptaakshay.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    public void validateLoanCreation(){
        Double years = 3D;
        Loan loan = new Loan("IDIDI", "Akshay", 10000D, years, 7D);
        Double amount  = 12100D;
        EMI emi = new EMI(amount, years);

        assertEquals(amount.intValue(), loan.getAmount().intValue());
        assertEquals(emi.getNoOfEmi().intValue(), loan.getEmiDetails().getNoOfEmi().intValue());
        assertEquals(emi.getEmiPerMonth().intValue(), loan.getEmiDetails().getEmiPerMonth().intValue());
    }

}