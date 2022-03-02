package kots.invoiceprogram.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "invoicesItems")
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    private String name;
    private String ui;
    private int quantity;
    private BigDecimal netPrice;
    private double taxPercent;

    @Setter(AccessLevel.NONE)
    private BigDecimal taxValue;

    @Setter(AccessLevel.NONE)
    private BigDecimal grossPrice;
    private double discount;

    public Item(Long id, String name, String ui, int quantity, BigDecimal netPrice, double taxPercent, double discount) {
        this.id = id;
        this.name = name;
        this.ui = ui;
        this.quantity = quantity;
        this.netPrice = netPrice;
        this.taxPercent = taxPercent;
        this.discount = discount;
    }

    @PreUpdate
    @PrePersist
    public void calculateGrossPrice() {
        taxValue = netPrice.multiply(BigDecimal.valueOf(taxPercent));
        grossPrice = netPrice.add(taxValue);
        grossPrice.divide(BigDecimal.valueOf(1 + discount));
    }
}
