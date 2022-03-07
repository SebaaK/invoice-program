package kots.invoiceprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvoiceProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceProgramApplication.class, args);
    }

    //TODO: Endpoint kraje-waluta: https://restcountries.com/#api-endpoints-v3-name
    //TODO: WALUTA https://freecurrencyapi.net/api/v2/latest?apikey=8b3a6290-9a46-11ec-8f80-c304cbbc918a&base_currency=USD
    //TODO: https://freecurrencyapi.net/dashboard
}
