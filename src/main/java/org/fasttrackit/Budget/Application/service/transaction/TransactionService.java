package org.fasttrackit.Budget.Application.service.transaction;

import org.fasttrackit.Budget.Application.exception.ResourceNotFoundException;
import org.fasttrackit.Budget.Application.model.transaction.Product;
import org.fasttrackit.Budget.Application.model.transaction.Transaction;
import org.fasttrackit.Budget.Application.model.transaction.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final List<Transaction> transactionList;

    public TransactionService(MemoryTransactionProvider memoryTransactionProvider) {
        this.transactionList = memoryTransactionProvider.getTransactionList();
    }

    public List<Transaction> getAll() {
        return transactionList;
    }

    public List<Transaction> getAllByProduct(String name) {
        if (!(transactionList.stream()
                .map(transaction -> transaction.getProduct().getName())
                .toList().contains(name))) {
            throw new ResourceNotFoundException("Product name not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getProduct().getName().equalsIgnoreCase(name))
                .toList();
    }

    public List<Transaction> getAllByType(String type) {
        if (!(transactionList.stream()
                .map(transaction -> transaction.getType().toString())
                .toList().contains(type))) {
            throw new ResourceNotFoundException("Transaction type not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getType().toString().equalsIgnoreCase(type))
                .toList();
    }

    public List<Transaction> getAllByMinAmount(Double minAmount) {
        return transactionList.stream()
                .filter(transaction -> transaction.getAmount() >= minAmount)
                .toList();
    }

    public List<Transaction> getAllByMaxAmount(Double maxAmount) {
        return transactionList.stream()
                .filter(transaction -> transaction.getAmount() <= maxAmount)
                .toList();
    }

    public List<Transaction> getAllByMinMAxAmount(Double minAmount, Double maxAmount) {
        return transactionList.stream()
                .filter(transaction -> transaction.getAmount() >= minAmount && transaction.getAmount() <= maxAmount)
                .toList();
    }


    public List<Transaction> getAllByProductAndMinAmount(String name, Double minAmount) {
        if (!(transactionList.stream()
                .map(transaction -> transaction.getProduct().getName())
                .toList().contains(name))) {
            throw new ResourceNotFoundException("Product name not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getProduct().getName().equalsIgnoreCase(name))
                .filter(transaction -> transaction.getAmount() >= minAmount)
                .toList();
    }

    public List<Transaction> getAllByProductAndMaxAmount(String name, Double maxAmount) {
        if (!(transactionList.stream()
                .map(transaction -> transaction.getProduct().getName())
                .toList().contains(name))) {
            throw new ResourceNotFoundException("Product name not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getProduct().getName().equalsIgnoreCase(name))
                .filter(transaction -> transaction.getAmount() <= maxAmount)
                .toList();
    }

    public List<Transaction> getAllByProductAndMinMaxAmount(String name, Double minAmount, Double maxAmount) {
        if (!(transactionList.stream()
                .map(transaction -> transaction.getProduct().getName())
                .toList().contains(name))) {
            throw new ResourceNotFoundException("Product name not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getProduct().getName().equalsIgnoreCase(name))
                .filter(transaction -> transaction.getAmount() <= maxAmount && transaction.getAmount() >= minAmount)
                .toList();
    }

    public List<Transaction> getAllByTypeAndMinAmount(String type, Double minAmount) {

        if (!(transactionList.stream()
                .map(transaction -> transaction.getType().toString())
                .toList().contains(type))) {
            throw new ResourceNotFoundException("Transaction type not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getType().toString().equalsIgnoreCase(type))
                .filter(transaction -> transaction.getAmount() >= minAmount)
                .toList();
    }

    public List<Transaction> getAllByTypeAndMaxAmount(String type, Double maxAmount) {

        if (!(transactionList.stream()
                .map(transaction -> transaction.getType().toString())
                .toList().contains(type))) {
            throw new ResourceNotFoundException("Transaction type not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getType().toString().equalsIgnoreCase(type))
                .filter(transaction -> transaction.getAmount() <= maxAmount)
                .toList();
    }

    public List<Transaction> getAllByTypeAndMinMAxAmount(String type, Double minAmount, Double maxAmount) {

        if (!(transactionList.stream()
                .map(transaction -> transaction.getType().toString())
                .toList().contains(type))) {
            throw new ResourceNotFoundException("Transaction type not found");
        }
        return transactionList.stream()
                .filter(transaction -> transaction.getType().toString().equalsIgnoreCase(type))
                .filter(transaction -> transaction.getAmount() >= minAmount && transaction.getAmount() <= maxAmount)
                .toList();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionList.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id!"));
    }

    public void addTransaction(Integer id, String name, Double price, String category, Integer productID, String type, Double amount) {
        if (id == null || name == null || price == null || category == null || productID == null || type == null || amount == null) {
            throw new ResourceNotFoundException("Invalid input!");
        }
        transactionList.add(Transaction.builder().id(id).product(Product.builder().name(name).price(price).category(category).id(productID).build()).type(Type.fromStringToEnum(type)).amount(amount).build());
    }

    public void replaceTransaction(Integer id, String type, Double amount) {
        if (id == null || type == null || amount == null) {
            throw new ResourceNotFoundException("Invalid input!");
        }
        if (!transactionList.stream()
                .map(Transaction::getId)
                .toList().contains(id)) {
            throw new ResourceNotFoundException("Invalid id!");
        }

        transactionList.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst().get().setType(Type.fromStringToEnum(type));
        transactionList.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst().get().setAmount(amount);
    }

    public void deleteTransaction(Integer id) {

        if (!transactionList.stream()
                .map(Transaction::getId)
                .toList().contains(id)) {
            throw new ResourceNotFoundException("Invalid id!");
        }

        transactionList.remove(transactionList.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst().get());
    }

    public Map<Type, List<Transaction>> mapFromTypeToList() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<Product, List<Transaction>> mapFromProductToList() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }

    public Map<String, List<Transaction>> mapFromProductNameToList() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getProduct().getName()));
    }

}
