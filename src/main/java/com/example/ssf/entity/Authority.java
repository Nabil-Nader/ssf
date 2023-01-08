package com.example.ssf.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // when we type mappedBy and a name this name need to be the same as the one we create in the user table
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
