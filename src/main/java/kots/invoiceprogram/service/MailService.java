package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Mail;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.model.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private MimeMessagePreparator createMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(buildPaymentReminder(null, null));
            //invoicebysebaa@gmail.com
        };
    }

    private String buildPaymentReminder(BusinessDto business, InvoiceDto invoice) {
        Context context = new Context();
        context.setVariable("business", business);
        context.setVariable("invoice", invoice);
        return templateEngine.process("mail/payment-reminder.html", context);
    }
}
