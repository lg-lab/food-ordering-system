package com.labs.lg.food.ordering.system.payment.service.domain.exception;

import com.labs.lg.pentagon.common.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
