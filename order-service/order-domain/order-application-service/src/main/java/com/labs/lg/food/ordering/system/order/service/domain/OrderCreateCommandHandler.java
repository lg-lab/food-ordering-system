package com.labs.lg.food.ordering.system.order.service.domain;

import com.labs.lg.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.labs.lg.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.labs.lg.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.labs.lg.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {
  private final OrderCreateHelper orderCreateHelper;

  private final OrderDataMapper orderDataMapper;


  public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper,
                                   OrderDataMapper orderDataMapper) {
    this.orderCreateHelper = orderCreateHelper;
    this.orderDataMapper = orderDataMapper;
  }

  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
    log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
    orderCreatedEvent.fire();
    return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(),
        "Order created successfully");
  }
}
