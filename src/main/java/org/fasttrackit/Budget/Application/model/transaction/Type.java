package org.fasttrackit.Budget.Application.model.transaction;

import org.fasttrackit.Budget.Application.exception.ResourceNotFoundException;

public enum Type {
    SELL, BUY;

    public static Type fromStringToEnum(String type) {
        if (Type.BUY.toString().equalsIgnoreCase(type)) {
            return Type.BUY;
        } else if (Type.SELL.toString().equalsIgnoreCase(type)) {
            return Type.SELL;
        } else {
            throw new ResourceNotFoundException("Invalid type!");
        }
    }
}
