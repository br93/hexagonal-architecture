package com.food.ordering.system.domain.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.food.ordering.system.domain.valueobject.PaymentStatus;

public record PaymentResponse(String id, String sagaId, String orderId, String paymentId, String customerId,
        BigDecimal price, Instant createdAt, PaymentStatus paymentStatus, List<String> failureMessages) {

}
