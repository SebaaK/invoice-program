package kots.invoiceprogram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return id.equals(business.id) && fullName.equals(business.fullName) && address.equals(business.address) && postalCode.equals(business.postalCode) && city.equals(business.city) && country.equals(business.country) && taxId.equals(business.taxId) && emailAddress.equals(business.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, address, postalCode, city, country, taxId, emailAddress);
    }
}
