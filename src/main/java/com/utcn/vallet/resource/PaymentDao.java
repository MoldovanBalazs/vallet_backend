package com.utcn.vallet.resource;

import com.utcn.vallet.domain.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDao extends CrudRepository<Payment, Long> {
}
