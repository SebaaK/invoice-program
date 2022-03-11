package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
            cascade = CascadeType.REMOVE
    )
    private List<InvoicePayment> invoicePayment;

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
    private BigDecimal netPrice;
    private BigDecimal taxValue;
    private BigDecimal grossPrice;
    private BigDecimal includePayment;
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

    //TODO: Zmienić na skrypty po stronie bazy danych B)
    @PrePersist
    void setAutomaticFields() {
        createdDate = LocalDate.now();
        calcGrossPrice();
    }

    @PreUpdate
    public void calcGrossPrice() {
        netPrice = BigDecimal.valueOf(itemList.stream()
                .map(Item::getNetPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum()
        );

        taxValue = BigDecimal.valueOf(itemList.stream()
                .map(Item::getTaxValue)
                .mapToDouble(BigDecimal::doubleValue)
                .sum()
        );

        grossPrice = BigDecimal.valueOf(itemList.stream()
                .map(Item::getGrossPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum());

        if(invoicePayment != null) {
            includePayment = BigDecimal.valueOf(invoicePayment.stream()
                    .map(InvoicePayment::getPaymentValue)
                    .mapToDouble(BigDecimal::doubleValue)
                    .sum());
        }
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
