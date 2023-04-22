package com.labs.lg.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.labs.lg.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.labs.lg.food.ordering.system.order.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
