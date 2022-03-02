package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "invoicesGtu")
public class InvoiceGtu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GTUType gtuType;
}
