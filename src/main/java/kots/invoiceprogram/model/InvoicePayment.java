package kots.invoiceprogram.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    private LocalDate paymentDate;
    private BigDecimal paymentValue;

    @PrePersist
    void setDate() {
        paymentDate = LocalDate.now();
    }
}
