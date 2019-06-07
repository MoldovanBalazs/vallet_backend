package com.utcn.vallet.dto;

import com.utcn.vallet.domain.Wallet;
import lombok.Data;

import java.util.Date;

@Data
public class WalletDTO {

    private Long id;
    private Double sum;
    private Date registrationDate;
    private Date expirationDate;
    private Wallet.Currency currency;
    private String username;


    public WalletDTO(Long id, Double sum, Date registrationDate, Date expiraionDate, Wallet.Currency currency, String username) {
        this.id = id;
        this.sum = sum;
        this.registrationDate = registrationDate;
        this.expirationDate = expiraionDate;
        this.currency = currency;
        this.username = username;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Wallet.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Wallet.Currency currency) {
        this.currency = currency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "id=" + id +
                ", sum=" + sum +
                ", registrationDate=" + registrationDate +
                ", expirationDate=" + expirationDate +
                ", currency=" + currency +
                ", username='" + username + '\'' +
                '}';
    }
}
