package kots.invoiceprogram.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfService {

    @SneakyThrows
    public File generatePdf(String html) {
        File file = new File(System.getProperty("java.io.tmpdir") + "/faktura.pdf");
        FileOutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20 * 4f / 3f, 20);
        renderer.setDocumentFromString(html, file.getAbsoluteFile().getAbsolutePath());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }
}
