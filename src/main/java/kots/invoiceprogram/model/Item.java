package kots.invoiceprogram.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "invoicesItems")
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//
//    private Invoice invoice;

    private String name;
    private int quantity;
    private BigDecimal netPrice;
    private double taxPercent;
    private BigDecimal taxValue;
    private BigDecimal grossPrice;
    private double discount;

    public Item(Long id, String name, int quantity, BigDecimal netPrice, double taxPercent, BigDecimal taxValue, BigDecimal grossPrice, double discount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.netPrice = netPrice;
        this.taxPercent = taxPercent;
        this.taxValue = taxValue;
        this.grossPrice = grossPrice;
        this.discount = discount;
    }

    @PreUpdate
    @PrePersist
    public void calculateGrossPrice() {
        // FIXME: 03.03.2022 Trzeba poprawić liczenie Netto?Brutto
        taxValue = netPrice.multiply(BigDecimal.valueOf(taxPercent));
        grossPrice = netPrice.add(taxValue);
        grossPrice.divide(BigDecimal.valueOf(1 + discount));
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && Double.compare(item.taxPercent, taxPercent) == 0 && Double.compare(item.discount, discount) == 0 && id.equals(item.id) && name.equals(item.name) && Objects.equals(netPrice, item.netPrice) && Objects.equals(taxValue, item.taxValue) && Objects.equals(grossPrice, item.grossPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, netPrice, taxPercent, taxValue, grossPrice, discount);
    }
}
