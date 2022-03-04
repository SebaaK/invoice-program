package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "invoicesGtu")
@NoArgsConstructor
public class InvoiceGtu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @Enumerated(EnumType.STRING)
    private GTUType gtuType;

    public InvoiceGtu(GTUType gtuType) {
        this.gtuType = gtuType;
    }

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
