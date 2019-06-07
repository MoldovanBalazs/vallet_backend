package com.utcn.vallet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    private String name;
    private String username;
    private String password;
    private Boolean isAdmin;

    public UserDTO() {}

    public UserDTO(String name, String username, String password, Boolean isAdmin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
