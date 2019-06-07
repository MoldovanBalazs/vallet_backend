package com.utcn.vallet.resource;

import com.utcn.vallet.domain.PaymentPart;
import com.utcn.vallet.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentPartDao extends CrudRepository<PaymentPart, Long> {

    List<PaymentPart> findAllByWallet(Wallet wallet);

}
