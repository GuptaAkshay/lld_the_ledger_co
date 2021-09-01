package dev.guptaakshay.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EMITest {

    @Test
    public void validateEMICreation(){
        EMI emi = new EMI( 5300D, 1D);
        int emiPerMonth = 442;
        int noOfEmi = 12;
        assertEquals(emiPerMonth, emi.getEmiPerMonth().intValue());
        assertEquals(noOfEmi, emi.getNoOfEmi().intValue());
    }
}