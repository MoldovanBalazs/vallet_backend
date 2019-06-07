package com.utcn.vallet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utcn.vallet.domain.User;
import com.utcn.vallet.dto.UserDTO;
import com.utcn.vallet.resource.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserService {

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/validate/{userJson}")
    public UserDTO validateUser(@PathVariable String userJson) {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO dto = null;
        try {
            dto = mapper.readValue(userJson, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        User user = null;



        user = userDao.findByUsername(dto.getUsername());
        if(user != null) {
            if(user.getPassword().equals(dto.getPassword())) {
                dto.setName(user.getName());
                dto.setAdmin(user.getAdmin());
                return dto;
            }
            else {
                return null;
            }
        }
        return null;
    }

    @GetMapping(path = "/user/{username}")
    public List<UserDTO> searchUser(@PathVariable String username) {
        System.out.println("USERNAME: " + username);
        List<User> users = (List<User>) userDao.findAll();
        List<UserDTO> dtos = new ArrayList<>();



        userDao.findAll().forEach(user -> {
            if(user.getUsername().contains(username)) {
                dtos.add(new UserDTO(user.getName(), user.getUsername(), null, null));
            }
        });
        System.out.println(dtos);
        return dtos;
    }

    @GetMapping(path = "/user/all")
    public List<UserDTO> findAll() {
        List<UserDTO> dtos = new ArrayList<>();
        userDao.findAll().forEach(user -> {
                dtos.add(new UserDTO(user.getName(), user.getUsername(), null, user.getAdmin()));
        });
        System.out.println(dtos);
        return dtos;
    }
}
