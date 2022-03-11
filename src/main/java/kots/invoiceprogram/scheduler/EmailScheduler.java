package kots.invoiceprogram.scheduler;

import kots.invoiceprogram.model.Mail;
import kots.invoiceprogram.repository.InvoiceRepository;
import kots.invoiceprogram.service.MailService;
import kots.invoiceprogram.service.TemplateMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
class EmailScheduler {

    private final InvoiceRepository repository;
    private final TemplateMailService templateMailService;
    private final MailService mailService;

    @Scheduled(cron = "${cron.expression}")
    void sendInvoiceReminder() {
        List<Mail> prepareMailsToSend = repository.dataForEmailReminderScheduled().stream()
                .map(invoice -> Mail.builder()
                        .mailTo(invoice.getCustomer().getEmailAddress())
                        .replyTo(invoice.getBusiness().getEmailAddress())
                        .replyToTitle(invoice.getBusiness().getFullName())
                        .subject("Wezwanie do zapłaty za fakturę nr " + invoice.getInvoiceNumber())
                        .messageTemplate(templateMailService.buildPaymentReminder(invoice))
                        .build())
                .collect(Collectors.toList());

        for(Mail mailToSend : prepareMailsToSend) {
            mailService.sendPaymentReminder(mailToSend);
        }
    }
}
