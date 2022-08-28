package org.fasttrackit.Budget.Application.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Transaction {

    private Integer id;
    private Product product;
    private Type type;
    private Double amount;
}
