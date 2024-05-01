package com.labs.lg.food.ordering.system.restaurant.service.domain.entity;

import com.labs.lg.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalStatus;
import com.labs.lg.food.ordering.system.restaurant.service.domain.valueobject.OrderStatus;
import com.labs.lg.food.ordering.system.restaurant.service.domain.valueobject.RestaurantId;
import com.labs.lg.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;
import com.labs.lg.pentagon.common.domain.entity.AggregateRoot;
import com.labs.lg.pentagon.common.domain.valueobject.Money;

import java.util.List;
import java.util.UUID;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private OrderApproval orderApproval;
    private boolean active;
    private final OrderDetail orderDetail;

    private Restaurant(Builder builder) {
        setId(builder.restaurantId);
        orderApproval = builder.orderApproval;
        active = builder.active;
        orderDetail = builder.orderDetail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validateOrder(List<String> failureMessages) {
        if (OrderStatus.PAID != orderDetail.getOrderStatus()) {
            failureMessages.add("Payment is not completed for order: " + orderDetail.getId());
        }
        final Money totalAmount = orderDetail.getProducts().stream()
                .map(product -> {
                    if (!product.isAvailable()) {
                        failureMessages.add("Product with id " + product.getId().getValue() + " is not available");
                    }
                    return product.getPrice().multiply(product.getQuantity());
                })
                .reduce(Money.ZERO, Money::add);

        if (!totalAmount.equals(orderDetail.getTotalAmount())) {
            failureMessages.add("Price total is not correct for order: " + orderDetail.getId());
        }
    }

    public void constructOrderApproval(OrderApprovalStatus orderApprovalStatus) {
        this.orderApproval = OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(UUID.randomUUID()))
                .restaurantId(this.getId())
                .orderId(this.getOrderDetail().getId())
                .approvalStatus(orderApprovalStatus)
                .build();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public boolean isActive() {
        return active;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public static final class Builder {
        private RestaurantId restaurantId;
        private OrderApproval orderApproval;
        private boolean active;
        private OrderDetail orderDetail;

        private Builder() {
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder orderApproval(OrderApproval val) {
            orderApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder orderDetail(OrderDetail val) {
            orderDetail = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
