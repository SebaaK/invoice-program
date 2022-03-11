package kots.invoiceprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InvoiceProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceProgramApplication.class, args);
    }
}
