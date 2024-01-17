package com.api.users.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "users")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;


    @Column(name="email",length = 100,unique = true)
    private String email;

    @Lob
    private  String address;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;
}
