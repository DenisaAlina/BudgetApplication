package org.fasttrackit.Budget.Application.service.transaction;

import org.fasttrackit.Budget.Application.model.transaction.Product;
import org.fasttrackit.Budget.Application.model.transaction.Transaction;
import org.fasttrackit.Budget.Application.model.transaction.Type;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MemoryTransactionProvider {

    public List<Transaction> getTransactionList() {
        List<Transaction> list = new LinkedList<>();
        list.add(Transaction.builder().id(1).product(Product.builder().name("Milk").price(6.5).category("dairyProducts").id(1).build()).type(Type.SELL).amount(6.0).build());
        list.add(Transaction.builder().id(2).product(Product.builder().name("Flour").price(4.0).category("basicFoods").id(2).build()).type(Type.SELL).amount(10.0).build());
        list.add(Transaction.builder().id(3).product(Product.builder().name("Tagliatelle").price(9.8).category("basicFood").id(3).build()).type(Type.SELL).amount(8.0).build());
        list.add(Transaction.builder().id(4).product(Product.builder().name("Orange").price(4.5).category("fruits").id(4).build()).type(Type.SELL).amount(6.0).build());
        list.add(Transaction.builder().id(5).product(Product.builder().name("Chicken Ham").price(28.5).category("meatBasedProducts").id(5).build()).type(Type.SELL).amount(12.0).build());
        list.add(Transaction.builder().id(6).product(Product.builder().name("Wine").price(30.0).category("alcoholicDrinks").id(6).build()).type(Type.SELL).amount(6.0).build());
        list.add(Transaction.builder().id(7).product(Product.builder().name("Water").price(4.0).category("nonalcoholicDrinks").id(7).build()).type(Type.SELL).amount(4.0).build());
        list.add(Transaction.builder().id(8).product(Product.builder().name("Wine").price(25.0).category("alcoholicDrinks").id(8).build()).type(Type.BUY).amount(6.0).build());
        list.add(Transaction.builder().id(9).product(Product.builder().name("Rice").price(6.5).category("basicFoods").id(9).build()).type(Type.BUY).amount(6.0).build());
        list.add(Transaction.builder().id(10).product(Product.builder().name("Milk").price(6.5).category("dairyProducts").id(10).build()).type(Type.BUY).amount(12.0).build());
        return list;
    }

}
