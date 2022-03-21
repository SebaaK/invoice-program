package kots.invoiceprogram.service;

import kots.invoiceprogram.config.AdminConfig;
import kots.invoiceprogram.model.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.File;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @InjectMocks
    private MailService mailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private PdfService pdfService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldSendMailWithAttachment() {
        // given
        // when
        mailService.sendWithAttachment(testMail());

        // then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    void shouldSendMailReminder() {
        // given
        // when
        mailService.sendPaymentReminder(testMail());

        // then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    private Mail testMail() {
        return Mail.builder()
                .replyTo("reply@test.test")
                .replyToTitle("ReplyToThis")
                .mailTo("mail@testmail.test")
                .subject("ExampleSubjectMail")
                .messageTemplate("ExampleTextInMail")
                .attachmentFileName("Invoice.pdf")
                .htmlCodeToGeneratePdf("<b>ExampleTest</b>")
                .build();
    }
}