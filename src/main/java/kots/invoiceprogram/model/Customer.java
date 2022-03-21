package kots.invoiceprogram.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public Customer(String customerName, String businessName, String businessAddress, String businessPostalCode, String businessCity, String businessCountry, Integer taxId, String emailAddress) {
        this.customerName = customerName;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessPostalCode = businessPostalCode;
        this.businessCity = businessCity;
        this.businessCountry = businessCountry;
        this.taxId = taxId;
        this.emailAddress = emailAddress;
    }
}
