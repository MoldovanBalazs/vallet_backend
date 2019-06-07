package com.utcn.vallet.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utcn.vallet.domain.User;
import com.utcn.vallet.domain.Wallet;
import com.utcn.vallet.domain.builder.WalletBuilder;
import com.utcn.vallet.dto.WalletDTO;
import com.utcn.vallet.resource.UserDao;
import com.utcn.vallet.resource.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class WalletService {

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/wallets/{username}")
    public List<WalletDTO> getUserWallets(@PathVariable("username") String username) {

        User user = userDao.findByUsername(username);
        List<Wallet> wallets = walletDao.findAllByUser(user);
        List<WalletDTO> dtoList = new ArrayList<>();
        wallets.forEach(wallet -> {
            WalletDTO walletDTO = new WalletDTO(wallet.getId(), wallet.getSum(), wallet.getRegistrationDate(), wallet.getExpirationDate(), wallet.getCurrency(), user.getUsername());
            dtoList.add(walletDTO);
        });
        if(dtoList.isEmpty()) {
            return null;
        }
        return dtoList;
    }

    @PostMapping(path = "wallets/insert")
    public WalletDTO insertWallet(@RequestBody String walletJSON) {

        System.out.println("JSON: " + walletJSON);
        Gson gson = new GsonBuilder().create();
        WalletDTO walletDTO = gson.fromJson(walletJSON, WalletDTO.class);
        System.out.println(walletDTO.toString());
        User user = userDao.findByUsername(walletDTO.getUsername());
        Wallet wallet = WalletBuilder.createWalletBuilder()
                .user(user)
                .sum(walletDTO.getSum())
                .registrationDate(new Date(new java.util.Date().getTime()))
                .expirationDate(new Date(walletDTO.getExpirationDate().getTime()))
                .currency(walletDTO.getCurrency())
                .build();
        Wallet toReturn = walletDao.save(wallet);

        if(toReturn != null) {
            return walletDTO;
        }
        return null;
    }

}
