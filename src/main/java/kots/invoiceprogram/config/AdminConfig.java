package kots.invoiceprogram.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${spring.mail.username}")
    private String mailSystem;

    @Value("${spring.mail.personal_title}")
    private String mailPersonalTitle;
}
