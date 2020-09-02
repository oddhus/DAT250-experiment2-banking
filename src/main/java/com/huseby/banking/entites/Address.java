package com.huseby.banking.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private int number;

    @ManyToMany(mappedBy = "addresses")
    private List<Person> residents;

}
