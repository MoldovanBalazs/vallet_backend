package com.utcn.vallet.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PaymentPartDTO {

    private Long id;
    private Double sumPercentage;

    @SerializedName("payment")
    private PaymentDTO paymentDTO;

    @SerializedName("user")
    private UserDTO userDTO;

    public PaymentPartDTO(Long id, Double sumPercentage) {
        this.id = id;
        this.sumPercentage = sumPercentage;
    }

    public PaymentPartDTO(Long id, Double sumPercentage, PaymentDTO paymentDTO) {
        this.id = id;
        this.sumPercentage = sumPercentage;
        this.paymentDTO = paymentDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSumPercentage() {
        return sumPercentage;
    }

    public void setSumPercentage(Double sumPercentage) {
        this.sumPercentage = sumPercentage;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }


}
