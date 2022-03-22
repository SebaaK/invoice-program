package kots.invoiceprogram.scheduler;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.Mail;
import kots.invoiceprogram.model.selectors.PaymentMethod;
import kots.invoiceprogram.repository.InvoiceRepository;
import kots.invoiceprogram.service.MailService;
import kots.invoiceprogram.service.TemplateMailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kots.invoiceprogram.GenerateData.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

    public static final int COUNT_INVOICES = 10;
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private InvoiceRepository repository;

    @Mock
    private TemplateMailService templateMailService;

    @Mock
    private MailService mailService;

    @Test
    void shouldSendEmailReminder() {
        // given
        when(repository.dataForEmailReminderScheduled()).thenReturn(getInvoiceLists());
        when(templateMailService.buildPaymentReminder(any(Invoice.class))).thenReturn("GenerateTemplateMail");

        // when
        emailScheduler.sendInvoiceReminder();

        // then
        verify(mailService, times(COUNT_INVOICES)).sendPaymentReminder(any(Mail.class));
    }

    private List<Invoice> getInvoiceLists() {
        List<Invoice> invoiceList = new ArrayList<>();
        for(long i = 1; i<= COUNT_INVOICES; i++) {
            invoiceList.add(
                    Invoice.builder()
                            .id(i)
                            .business(getBusiness())
                            .customer(getCustomer())
                            .paymentMethod(PaymentMethod.PRZELEW)
                            .invoiceNumber(i + "/11/2022")
                            .createdDate(LocalDate.now())
                            .issueDate(LocalDate.now())
                            .dueDate(LocalDate.now())
                            .netPrice(BigDecimal.ONE)
                            .taxValue(BigDecimal.ZERO)
                            .grossPrice(BigDecimal.ONE)
                            .includePayment(BigDecimal.ONE)
                            .currencyName("PLN")
                            .otherCurrencyName("EUR")
                            .otherCurrencyGrossPrice(BigDecimal.ONE)
                            .exchangeRate(new BigDecimal(3.39))
                            .build()
            );
        }
        return invoiceList;
    }
}