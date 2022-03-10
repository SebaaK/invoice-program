package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final PdfService pdfService;

    public void send(final Mail mail) {
        try {
            javaMailSender.send(createMailWithAttachment(mail));
        } catch (MailException e) {
            log.error("Mail doesn`t send. Info: " + e.getMessage());
        }
    }

    private MimeMessagePreparator createMailWithAttachment(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
            messageHelper.addAttachment(mail.getAttachmentFileName(), pdfService.generatePdf(mail.getHtmlCodeToGeneratePdf()));
        };
    }
}
