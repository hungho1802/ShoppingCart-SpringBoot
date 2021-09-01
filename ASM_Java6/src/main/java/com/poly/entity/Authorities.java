package com.poly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "Authorities")
@Entity
@NoArgsConstructor
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "Roleid")
    private Role role;

    public Authorities(Account account, Role role) {
        this.account = account;
        this.role = role;
    }
}
