package com.labs.lg.food.ordering.system.order.service.domain.dto.track;

import com.labs.lg.food.ordering.system.domain.valueobject.OrderStatus;
import lombok.Builder;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
@Builder
public record TrackOrderResponse(@NotNull UUID orderTrackingId,
                                 @NotNull OrderStatus orderStatus,
                                 List<String> failureMessages) {
}
