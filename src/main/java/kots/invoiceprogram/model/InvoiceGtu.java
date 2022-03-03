package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        InvoiceGtu that = (InvoiceGtu) o;
        return gtuType == that.gtuType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gtuType);
    }
}
