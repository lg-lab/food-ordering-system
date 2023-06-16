package com.labs.lg.food.ordering.system.restaurant.service.dataaccess.restaurant.entity;

import com.labs.lg.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_approval", schema = "restaurant")
@Entity
public class OrderApprovalEntity {

    @Id
    private UUID id;
    private UUID restaurantId;
    private UUID orderId;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderApprovalStatus status;
}