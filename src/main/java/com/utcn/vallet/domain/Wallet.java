package com.utcn.vallet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Wallet {

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

    @Column(name = "sum")
    private Double sum;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "wallet", targetEntity = PaymentPart.class, fetch = FetchType.LAZY)
    private Set<PaymentPart> paymentPart = new HashSet<>();

    @Column(name = "registrationDate")
    private Date registrationDate;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "currency")
    private Currency currency;

    public enum Currency{

        @SerializedName("0")
        EURO,

        @SerializedName("1")
        LEI,

        @SerializedName("2")
        DOLLAR,

        @SerializedName("3")
        TEMPORARY
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PaymentPart> getPaymentPart() {
        return paymentPart;
    }

    public void setPaymentPart(Set<PaymentPart> paymentPart) {
        this.paymentPart = paymentPart;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
