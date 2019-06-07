package com.utcn.vallet.dto.custom_dto;

public class PaymentPartUpdateDTO {

    private Long paymentPartId;
    private Long walletId;

    public PaymentPartUpdateDTO(Long paymentPartId, Long walletId) {
        this.paymentPartId = paymentPartId;
        this.walletId = walletId;
    }

    public Long getPaymentPartId() {
        return paymentPartId;
    }

    public void setPaymentPartId(Long paymentPartId) {
        this.paymentPartId = paymentPartId;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
}
