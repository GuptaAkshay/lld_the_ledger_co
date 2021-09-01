package dev.guptaakshay.model;

import dev.guptaakshay.util.Utils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Loan {

    private String loanId;
    private String bankName;
    private String borrowerName;

    private Double principal;
    private Double years;
    private Double roi;
    private Double amount;

    private EMI emiDetails;

    private List<Payment> payments;


    public Loan(String bankName, String borrowerName, Double principal, Double years, Double roi) {
        this.loanId = Utils.generateLoanId(bankName, borrowerName);
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.roi = roi;
        this.years = years;
        this.amount = calculateAmount(principal, years, roi);
        this.emiDetails = new EMI(this.amount, years);
        this.payments = new ArrayList<>();
    }

    private Double calculateAmount(Double principal, Double years, Double roi){
        Double interest = Math.ceil((principal * years * roi) / 100);
        return principal + interest;
    }


}
