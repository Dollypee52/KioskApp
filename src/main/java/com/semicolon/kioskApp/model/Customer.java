package com.semicolon.kioskApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @SequenceGenerator(name = "customer_id_sequence",
            sequenceName =  "customer_id_sequence")
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_id_sequence")
    private Long id;
    @NotNull
    @NotBlank
    private String customerFirstName;

    @NotNull
    @NotBlank
    private String customerLastName;
    @NotNull
    @NotBlank
    private String customerAddress;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @Email
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;
    @OneToOne
    private Account account;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Cart myCart;




    }

