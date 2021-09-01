package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "Accounts")
public class Account {
    @Id
    @NotNull(message = "Username is not blank!")
    private String username;
    @NotNull(message = "Password is not blank!")
    private String password;
    @NotBlank(message = "Fullname is not blank!")
    private String fullname;
    @Email
    @NotBlank(message = "Email is not blank!")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "account" , fetch = FetchType.EAGER)
    List<Authorities> authorities = new ArrayList<Authorities>();

    public Account(String email, String password, String fullname, String email1) {
        this.email = email;
        this.password = password;
        this.username = email1;
        this.fullname = fullname;
    }

    public Account() {

    }
}
