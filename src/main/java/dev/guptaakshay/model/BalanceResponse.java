package dev.guptaakshay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceResponse {

    private String bankName;
    private String borrowerName;
    private Integer amountPaid;
    private Integer emiLeft;

}
