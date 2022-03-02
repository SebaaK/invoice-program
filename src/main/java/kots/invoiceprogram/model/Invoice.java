package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.PaymentMethod;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Setter(AccessLevel.NONE)
    private Business business;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @Setter(AccessLevel.NONE)
    private Customer customer;

    @OneToMany(
            mappedBy = "invoice",
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.PERSIST
            }
    )
    private Set<Item> itemList;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "invoice_id")
    private Set<InvoiceGtu> gtuType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String invoiceNumber;
    @Setter(AccessLevel.NONE)
    private LocalDate createdDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal grossPrice;
    private String otherCurrencyName;
    private BigDecimal otherCurrencyGrossPrice;
    private BigDecimal exchangeRate;

    public Invoice(Long id, PaymentMethod paymentMethod, String invoiceNumber, LocalDate issueDate, LocalDate dueDate, BigDecimal grossPrice, String otherCurrencyName, BigDecimal otherCurrencyGrossPrice, BigDecimal exchangeRate) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.grossPrice = grossPrice;
        this.otherCurrencyName = otherCurrencyName;
        this.otherCurrencyGrossPrice = otherCurrencyGrossPrice;
        this.exchangeRate = exchangeRate;
    }

    @PrePersist
    void setCreatedDate() {
        createdDate = LocalDate.now();
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
