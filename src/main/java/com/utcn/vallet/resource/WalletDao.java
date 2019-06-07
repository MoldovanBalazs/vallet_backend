package com.utcn.vallet.resource;

import com.utcn.vallet.domain.User;
import com.utcn.vallet.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WalletDao extends CrudRepository<Wallet, Long> {

    List<Wallet> findAllByUser(User user);

    Wallet findAllByUserAndCurrency(User user, Wallet.Currency currency);

}
