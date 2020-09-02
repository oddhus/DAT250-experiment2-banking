package com.huseby.banking.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name ="person_pk")
    private List<CreditCard> creditCards;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_address",
            joinColumns = @JoinColumn(name = "address_fk"),
            inverseJoinColumns = @JoinColumn(name = "person_fk"))
    private List<Address> addresses;

}
