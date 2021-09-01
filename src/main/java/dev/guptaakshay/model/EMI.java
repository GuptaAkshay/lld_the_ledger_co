package dev.guptaakshay.model;

import lombok.Getter;

@Getter
public class EMI {

    private Double noOfEmi;
    private Double emiPerMonth;
    public final Integer MONTHS = 12;

    public EMI(Double amount, Double years) {
        this.emiPerMonth = calculateEmiPerMonth(amount, years);
        this.noOfEmi = years * MONTHS;
    }

    private Double calculateEmiPerMonth(Double amount, Double years){
        Double emiPerMonth = Math.ceil(amount / (years * MONTHS));
        return emiPerMonth;
    }
}
