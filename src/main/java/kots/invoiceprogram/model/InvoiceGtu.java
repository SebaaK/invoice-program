package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "invoicesGtu")
public class InvoiceGtu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GTUType gtuType;
}
