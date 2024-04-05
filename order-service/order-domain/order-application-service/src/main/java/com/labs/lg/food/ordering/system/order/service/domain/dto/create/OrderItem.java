package com.labs.lg.food.ordering.system.order.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderItem(@NotNull UUID productId, @NotNull int quantity, @NotNull BigDecimal price,
                        @NotNull BigDecimal subTotal) {
}
