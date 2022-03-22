package kots.invoiceprogram.model;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "invoicesGtu")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
