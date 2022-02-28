package kots.invoiceprogram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "businessId")
    private Business business;

    private String customerName;
    private String businessName;
    private String businessAddress;
    private String businessPostalCode;
    private String businessCity;
    private String businessCountry;
    private Integer taxId;
    private String emailAddress;
}
