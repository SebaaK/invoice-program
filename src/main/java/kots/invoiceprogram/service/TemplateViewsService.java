package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.model.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class TemplateViewsService {

    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String viewInvoiceAsPdf(Business business, Invoice invoice) {
        Context context = new Context();
        context.setVariable("business", business);
        context.setVariable("invoice", invoice);
        context.setVariable("customer", invoice.getCustomer());
        context.setVariable("invoiceItems", invoice.getItemList());
        return templateEngine.process("invoice", context);
    }

    public String buildPaymentReminder(BusinessDto business, InvoiceDto invoice) {
        Context context = new Context();
        context.setVariable("business", business);
        context.setVariable("invoice", invoice);
        return templateEngine.process("mail/payment-reminder", context);
    }
}
