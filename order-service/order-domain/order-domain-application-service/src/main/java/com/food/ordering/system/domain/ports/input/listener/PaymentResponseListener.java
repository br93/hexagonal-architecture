package com.food.ordering.system.domain.ports.input.listener;

import com.food.ordering.system.domain.dto.message.PaymentResponse;

//SAGA ROLLBACK//
public interface PaymentResponseListener {

    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
    
}
