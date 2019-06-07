package com.utcn.vallet.domain.builder;

import com.utcn.vallet.domain.User;
import com.utcn.vallet.domain.Wallet;

import java.sql.Date;
import java.util.Objects;

public class WalletBuilder {

    private Wallet underConstruction;

    private WalletBuilder() {
        this.underConstruction = new Wallet();
        underConstruction.setSum((double) 0);
    }

    public static WalletBuilder createWalletBuilder() {
        return new WalletBuilder();
    }

    public WalletBuilder sum(Double sum) {
        this.underConstruction.setSum(sum);
        return this;
    }

    public WalletBuilder user(User user) {
        this.underConstruction.setUser(user);
        return this;
    }

    public WalletBuilder registrationDate(Date date) {
        this.underConstruction.setRegistrationDate(date);
        return this;
    }

    public WalletBuilder expirationDate(Date date) {
        this.underConstruction.setExpirationDate(date);
        return this;
    }

    public WalletBuilder currency(Wallet.Currency currency) {
        this.underConstruction.setCurrency(currency);
        return this;
    }

    public void checkValid() {
        Objects.requireNonNull(underConstruction.getCurrency());
        Objects.requireNonNull(underConstruction.getUser());
        Objects.requireNonNull(underConstruction.getRegistrationDate());
        Objects.requireNonNull(underConstruction.getExpirationDate());
    }

    public Wallet build() {
        checkValid();
        return underConstruction;
    }

}
