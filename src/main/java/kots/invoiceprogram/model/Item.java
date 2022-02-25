package kots.invoiceprogram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "invoicesItems")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "invoiceId")
    private Invoice invoice;

    private String name;
    private String ui;
    private Integer quantity;
    private BigDecimal netPrice;
    private Integer taxValue;
    private BigDecimal grossPrice;
    private Integer discount;
}
