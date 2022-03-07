package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "invoices")
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "businessId")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(
            mappedBy = "invoice",
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.PERSIST
            }
    )
    private Set<Item> itemList;

    @OneToMany(
            mappedBy = "invoice",
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.PERSIST
            }
    )
    private Set<InvoiceGtu> gtuType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String invoiceNumber;
    @Setter(AccessLevel.NONE)
    private LocalDate createdDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal grossPrice;
    private String currencyName;
    private String otherCurrencyName;
    private BigDecimal otherCurrencyGrossPrice;
    private BigDecimal exchangeRate;

    public Invoice(Set<Item> itemList, Set<InvoiceGtu> gtuType, PaymentMethod paymentMethod, String invoiceNumber, LocalDate createdDate, LocalDate issueDate, LocalDate dueDate, BigDecimal grossPrice, String currencyName, String otherCurrencyName, BigDecimal otherCurrencyGrossPrice, BigDecimal exchangeRate) {
        this.itemList = itemList;
        this.gtuType = gtuType;
        this.paymentMethod = paymentMethod;
        this.invoiceNumber = invoiceNumber;
        this.createdDate = createdDate;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.grossPrice = grossPrice;
        this.currencyName = currencyName;
        this.otherCurrencyName = otherCurrencyName;
        this.otherCurrencyGrossPrice = otherCurrencyGrossPrice;
        this.exchangeRate = exchangeRate;
    }


    @PrePersist
    void setAutomaticFields() {
        createdDate = LocalDate.now();
        calcGrossPrice();
    }

    @PreUpdate
    void calcGrossPrice() {
        grossPrice = BigDecimal.valueOf(itemList.stream()
                .map(Item::getGrossPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id.equals(invoice.id) && invoiceNumber.equals(invoice.invoiceNumber) && createdDate.equals(invoice.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoiceNumber, createdDate);
    }
}
