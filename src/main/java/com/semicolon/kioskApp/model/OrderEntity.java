package com.semicolon.kioskApp.model;

import com.semicolon.kioskApp.model.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.mail.Address;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String number;
    @CreationTimestamp
    private Date ordered;
    @CreationTimestamp
    private Date shipped;
    private Address ship_to;
    private OrderStatus status;
    private int total;

}
