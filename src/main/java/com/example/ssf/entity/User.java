package com.example.ssf.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String password;

    private String userName;

    // here we create a third table called users_authorities
    @ManyToMany(fetch = FetchType.EAGER) // if remove eager it probably will not work,

    // exception it will fail to get the authorities, userDetailsService will be called and the transaction will end,
    // it will try to grab authorities in a new session but the transaction is already closed being the list is lazy it will not be able to take it anymore

    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;
}
