package com.utcn.vallet.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utcn.vallet.domain.Payment;
import com.utcn.vallet.domain.PaymentPart;
import com.utcn.vallet.domain.User;
import com.utcn.vallet.domain.Wallet;
import com.utcn.vallet.dto.PaymentDTO;
import com.utcn.vallet.dto.PaymentPartDTO;
import com.utcn.vallet.dto.custom_dto.PaymentPartUpdateDTO;
import com.utcn.vallet.resource.PaymentDao;
import com.utcn.vallet.resource.PaymentPartDao;
import com.utcn.vallet.resource.UserDao;
import com.utcn.vallet.resource.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PaymentPartService {

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private PaymentPartDao paymentPartDao;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("wallet/{walletId}/paymentparts")
    public List<PaymentPartDTO> findAllByWallet(@PathVariable("walletId") Long walletId) {

        Wallet wallet = walletDao.findById(walletId).get();
        List<PaymentPartDTO> dtos = new ArrayList<>();
        if (wallet != null) {
            List<PaymentPart> paymentParts = paymentPartDao.findAllByWallet(wallet);
            paymentParts.forEach(paymentPart -> {
                PaymentDTO dto = new PaymentDTO(paymentPart.getPayment().getId(), paymentPart.getPayment().getSum(), paymentPart.getPayment().getDate());

                dtos.add(new PaymentPartDTO(paymentPart.getId(), paymentPart.getSumPercentage(), dto));
            });
        }

        if(dtos.isEmpty()) {
            return null;
        }

        return dtos;
    }

    @PostMapping(path = "paymentparts/insert")
    public PaymentDTO insertPaymentPart(@RequestBody String paymentPartJSON) {

        System.out.println("JSON: " + paymentPartJSON);
        Gson gson = new GsonBuilder().create();
        List<PaymentPartDTO> paymentPartDTOs = gson.fromJson(paymentPartJSON, new TypeToken<List<PaymentPartDTO>>(){}.getType());

        System.out.println("ID is " + paymentPartDTOs.get(0).getPaymentDTO().getId());
        Payment payment = paymentDao.findById(paymentPartDTOs.get(0).getPaymentDTO().getId()).get();

        paymentPartDTOs.forEach(dto -> {
            PaymentPart paymentPart = new PaymentPart();
            paymentPart.setPayment(payment);
            paymentPart.setSumPercentage(dto.getSumPercentage());
            User user = userDao.findByUsername(dto.getUserDTO().getUsername());
            Wallet wallet = walletDao.findAllByUserAndCurrency(user, Wallet.Currency.TEMPORARY);
            paymentPart.setWallet(wallet);
            paymentPartDao.save(paymentPart);
        });

        return null;
    }

    @PutMapping(path = "paymentparts/update")
    public PaymentDTO updatePaymentPart(@RequestBody String paymentPartUpdateJSON) {

        System.out.println("JSON: " + paymentPartUpdateJSON);
        Gson gson = new GsonBuilder().create();
        PaymentPartUpdateDTO paymentPartUpdate = gson.fromJson(paymentPartUpdateJSON, PaymentPartUpdateDTO.class);

        PaymentPart paymentPart = paymentPartDao.findById(paymentPartUpdate.getPaymentPartId()).get();
        Wallet wallet = walletDao.findById(paymentPartUpdate.getWalletId()).get();

        paymentPart.setWallet(wallet);
        paymentPartDao.save(paymentPart);
        wallet.setSum(wallet.getSum() - paymentPart.getSumPercentage());
        walletDao.save(wallet);
        return null;
    }

    @DeleteMapping(path = "paymentparts/delete/{id}")
    public String deletePaymentPart(@PathVariable Long id) {

        PaymentPart paymentPart = paymentPartDao.findById(id).get();
        paymentPartDao.delete(paymentPart);
        return "success";
    }
}
