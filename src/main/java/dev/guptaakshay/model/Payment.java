package dev.guptaakshay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Payment {

    private Integer lumpSum;
    private Integer afterEmi;
}
