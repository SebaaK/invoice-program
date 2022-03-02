package kots.invoiceprogram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "businessDetails")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private Integer taxId;
    private String emailAddress;

    @OneToMany(
            mappedBy = "business",
            cascade = CascadeType.REMOVE
    )
    private Set<Customer> customerList;

    @OneToMany(
            mappedBy = "business",
            cascade = CascadeType.REMOVE
    )
    private Set<Invoice> invoiceList;
}
