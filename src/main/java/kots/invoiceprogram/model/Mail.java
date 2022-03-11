package kots.invoiceprogram.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.mail.internet.InternetAddress;

@Getter
@Builder
public class Mail {

    private final String mailTo;
    private final String replyTo;
    private final String replyToTitle;
    private final String subject;
    private final String messageTemplate;
    private final String attachmentFileName;
    private final String htmlCodeToGeneratePdf;
}
