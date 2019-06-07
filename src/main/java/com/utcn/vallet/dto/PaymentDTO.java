package com.utcn.vallet.dto;

import com.utcn.vallet.domain.Wallet;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {

    private Long id;
    private Double sum;
    private Date date;
    private Wallet.Currency currency;


    public PaymentDTO(Long id, Double sum, Date date) {
        this.id = id;
        this.sum = sum;
        this.date = date;
    }

    public PaymentDTO(Long id, Double sum, Date date, Wallet.Currency currency) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.currency = currency;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
