package kots.invoiceprogram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {

    private final String mailTo;
    private final String mailFromTitle;
    private final String subject;
    private final String message;
}
