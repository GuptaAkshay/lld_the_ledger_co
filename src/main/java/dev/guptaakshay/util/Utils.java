package dev.guptaakshay.util;

import dev.guptaakshay.exception.InvalidInputException;
import dev.guptaakshay.model.BalanceResponse;

import java.util.Objects;

public class Utils {

    public static String generateLoanId(String bankName, String borrowerName){
        if(Objects.isNull(bankName) || Objects.isNull(borrowerName) || bankName.isEmpty() || borrowerName.isEmpty()){
            throw new InvalidInputException();
        }
        return bankName+"_"+borrowerName;
    }

    public static void printBalanceResponse(BalanceResponse balanceResponse){
        System.out.println(balanceResponse.getBankName()+" "+balanceResponse.getBorrowerName()+" "
                +balanceResponse.getAmountPaid()+" "+balanceResponse.getEmiLeft());
    }
}
