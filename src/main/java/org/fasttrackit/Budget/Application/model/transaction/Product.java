package org.fasttrackit.Budget.Application.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private String name;
    private Double price;
    private String category;
    private Integer id;
}
