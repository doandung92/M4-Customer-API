package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonIgnoreProperties("province")
    @OneToMany(mappedBy = "province", fetch = FetchType.EAGER)
    List<Customer> customers;
}
