package kots.invoiceprogram.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Mail {

    private final String mailTo;
    private final String mailFromTitle;
    private final String subject;
    private final String message;
    private final String attachmentFileName;
    private final String htmlCodeToGeneratePdf;
}
