package org.fasttrackit.Budget.Application.controller.transaction;

import lombok.AllArgsConstructor;
import org.fasttrackit.Budget.Application.model.transaction.Product;
import org.fasttrackit.Budget.Application.model.transaction.Transaction;
import org.fasttrackit.Budget.Application.model.transaction.Type;
import org.fasttrackit.Budget.Application.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    List<Transaction> getTransactions(@RequestParam(required = false) String productName,
                                      @RequestParam(required = false) String type,
                                      @RequestParam(required = false) Double minAmount,
                                      @RequestParam(required = false) Double maxAmount) {
        if (productName == null) {
            if (type == null) {
                if (minAmount == null) {
                    if (maxAmount == null) {
                        return transactionService.getAll();
                    } else {
                        return transactionService.getAllByMaxAmount(maxAmount);
                    }
                } else if (maxAmount == null) {
                    return transactionService.getAllByMinAmount(minAmount);
                } else {
                    return transactionService.getAllByMinMAxAmount(minAmount, maxAmount);
                }
            } else if (minAmount == null) {
                if (maxAmount == null) {
                    return transactionService.getAllByType(type);
                } else {
                    return transactionService.getAllByTypeAndMaxAmount(type, maxAmount);
                }
            } else if (maxAmount == null) {
                return transactionService.getAllByTypeAndMinAmount(type, minAmount);
            } else {
                return transactionService.getAllByTypeAndMinMAxAmount(type, minAmount, maxAmount);
            }
        } else if (type == null && minAmount == null) {
            if (maxAmount == null) {
                return transactionService.getAllByProduct(productName);
            } else {
                return transactionService.getAllByProductAndMaxAmount(productName, maxAmount);
            }
        } else if (type == null && minAmount != null) {
            if (maxAmount == null) {
                return transactionService.getAllByProductAndMinAmount(productName, minAmount);
            } else {
                return transactionService.getAllByProductAndMinMaxAmount(productName, minAmount, maxAmount);
            }

        }
        return List.of();
    }

    @GetMapping("{id}")
    Transaction getTransactionById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    void addTransaction(@RequestParam Integer id,
                        @RequestParam String name,
                        @RequestParam Double price,
                        @RequestParam String category,
                        @RequestParam Integer productID,
                        @RequestParam String type,
                        @RequestParam Double amount) {
        transactionService.addTransaction(id, name, price, category, productID, type, amount);
    }

    @PutMapping("{id}")
    void replaceTransaction(@PathVariable Integer id,
                            @RequestParam String type,
                            @RequestParam Double amount) {
        transactionService.replaceTransaction(id, type, amount);
    }

    @DeleteMapping("{id}")
    void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("reports/type")
    Map<Type, List<Transaction>> mapFromTypeToList() {
        return transactionService.mapFromTypeToList();
    }

    @GetMapping("reports/product")
    Map<Product, List<Transaction>> mapFromProductToList() {
        return transactionService.mapFromProductToList();
    }

    @GetMapping("reports/productName")
    Map<String, List<Transaction>> mapFromProductNameToList() {
        return transactionService.mapFromProductNameToList();
    }

}
