package com.utcn.vallet.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utcn.vallet.domain.Payment;
import com.utcn.vallet.domain.User;
import com.utcn.vallet.domain.Wallet;
import com.utcn.vallet.domain.builder.WalletBuilder;
import com.utcn.vallet.dto.PaymentDTO;
import com.utcn.vallet.dto.UserDTO;
import com.utcn.vallet.dto.WalletDTO;
import com.utcn.vallet.resource.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @PostMapping(path = "payment/insert")
    public PaymentDTO insertPayment(@RequestBody String paymentJSON) {

        System.out.println("JSON: " + paymentJSON);
        Gson gson = new GsonBuilder().create();
        PaymentDTO paymentDTO = gson.fromJson(paymentJSON, PaymentDTO.class);
        System.out.println(paymentDTO.toString());

        Payment payment = new Payment();
        payment.setSum(paymentDTO.getSum());
        payment.setDate(new Date(paymentDTO.getDate().getTime()));
        payment.setCurrency(paymentDTO.getCurrency());

        Payment toReturn = paymentDao.save(payment);

        if(toReturn != null) {
            paymentDTO.setId(toReturn.getId());
            return paymentDTO;
        }
        return null;
    }

    @GetMapping(path = "/payments/all")
    public List<PaymentDTO> findAll() {
        List<PaymentDTO> dtos = new ArrayList<>();
        paymentDao.findAll().forEach(payment -> {
            dtos.add(new PaymentDTO(null, payment.getSum(), payment.getDate(), payment.getCurrency()));
        });
        System.out.println(dtos);
        return dtos;
    }
}
