package org.example.pdfgenerator;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.example.article.Article;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PDFGenerator {

    public static void generatePDF(String filePath, List<Article> articles) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float tableMarginTop = 70f;
                float yPosition = yStart;
                float rowHeight = 20f;

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                float yTextPos = yStart - 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yTextPos);
                contentStream.showText("Product");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Description");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Quantity");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Price");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                for (Article article : articles) {
                    if (yPosition - rowHeight < tableMarginTop) {
                        contentStream.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream.moveTo(margin, page.getMediaBox().getHeight() - margin);
                        yPosition = page.getMediaBox().getHeight() - margin;
                    }
                    yPosition -= rowHeight;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(article.getReference().toString());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(article.getName());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(String.valueOf(article.getQuantity()));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(String.valueOf(article.getPrice()));
                    contentStream.endText();
                }
            }
            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
