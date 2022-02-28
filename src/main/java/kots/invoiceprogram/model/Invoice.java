package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "invoices")
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
    private List<Item> itemList;

    @OneToMany(
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.PERSIST
            }
    )
    @JoinColumn(name = "invoice_id")
    private List<InvoiceGtu> gtuType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String invoiceNumber;
    private Date createdDate;
    private Date issueDate;
    private Date dueDate;
    private BigDecimal grossPrice;
    private String otherCurrencyName;
    private BigDecimal otherCurrencyGrossPrice;
    private BigDecimal exchangeRate;
}
