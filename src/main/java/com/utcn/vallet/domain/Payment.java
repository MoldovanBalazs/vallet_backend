package com.utcn.vallet.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

   @OneToMany(mappedBy = "payment", targetEntity = PaymentPart.class)
   private Set<PaymentPart> paymentParts = new HashSet<PaymentPart>();

    @Column(name = "sum")
    private Double sum;

    @Column(name = "date")
    private Date date;

    @Column(name = "currency")
    private Wallet.Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PaymentPart> getPaymentParts() {
        return paymentParts;
    }

    public void setPaymentParts(Set<PaymentPart> paymentParts) {
        this.paymentParts = paymentParts;
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

    public Wallet.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Wallet.Currency currency) {
        this.currency = currency;
    }
}
