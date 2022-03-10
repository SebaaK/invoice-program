package kots.invoiceprogram.controller;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.Mail;
import kots.invoiceprogram.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/view/business/{idBusiness}")
@RequiredArgsConstructor
@ResponseBody
class ViewInvoiceController {

    private final BusinessService businessService;
    private final InvoiceService invoiceService;
    private final TemplateViewsService templateViewsService;
    private final PdfService pdfService;
    private final MailService mailService;

    @RequestMapping("/invoice/{idInvoice}")
    void getInvoicePdf(@PathVariable Long idBusiness, @PathVariable Long idInvoice, HttpServletResponse response) {
        Business singleBusiness = businessService.getSingleBusiness(idBusiness);
        Invoice singleInvoice = invoiceService.getSingleInvoice(idBusiness, idInvoice);
        String generatedHTML = templateViewsService.viewInvoiceAsPdf(singleBusiness, singleInvoice);

        try {
            Path file = Paths.get(pdfService.generatePdf(generatedHTML).getAbsolutePath());
            if(Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/invoice/{idInvoice}/send")
    void sendMail(@PathVariable Long idBusiness, @PathVariable Long idInvoice) {
        Business singleBusiness = businessService.getSingleBusiness(idBusiness);
        Invoice singleInvoice = invoiceService.getSingleInvoice(idBusiness, idInvoice);
        String generatedHTML = templateViewsService.viewInvoiceAsPdf(singleBusiness, singleInvoice);

        Mail mail = Mail.builder()
                .mailTo("sebaa.kot@gmail.com")
                .subject("Test mail with attachment")
                .message("FV")
                .attachmentFileName("fakturka.pdf")
                .htmlCodeToGeneratePdf(generatedHTML)
                .build();

        mailService.send(mail);
    }
}
