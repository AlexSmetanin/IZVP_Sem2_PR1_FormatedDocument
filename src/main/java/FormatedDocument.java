import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class FormatedDocument {
    public static void main(String[] args) throws IOException, DocumentException {
        // створення екземпляру об’єкту документу
        Document document = new Document(PageSize.A4,25,10,20,20);

        // створення об’єкту редактора для pdf файлів PdfWriter
        PdfWriter pdfWriter = PdfWriter.getInstance(document,
                new FileOutputStream("formatedDocument.pdf"));
        document.open();

        // створення об’єкту paragraph
        Anchor anchorTarget = new Anchor("First page of the document");
        anchorTarget.setName("BackToTop");

        Paragraph paragraph1 = new Paragraph();
        paragraph1.setSpacingBefore(50);
        paragraph1.add(anchorTarget);

        document.add(paragraph1);
        document.add(new Paragraph("Some more text on the first page with different color and font type",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));

        // створення об’єкту chapter
        Paragraph title1 = new Paragraph("Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC,
                        new CMYKColor(0, 255, 255,17)));
        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);

        // створення об’єкт section
        Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 255,17)));
        Section section1 = chapter1.addSection(title11);
        Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
        section1.add(someSectionText);
        someSectionText = new Paragraph("Following is a 3 X 2 table.");
        section1.add(someSectionText);

        // створення об’єкту типу table
        PdfPTable t = new PdfPTable(3);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
        t.addCell(c3);
        t.addCell("1.1");
        t.addCell("1.2");
        t.addCell("1.3");
        section1.add(t);

        // створення об’єкта класу list
        List l = new List(true, false, 10);
        l.add(new ListItem("First item of list"));
        l.add(new ListItem("Second item of list"));
        section1.add(l);

        // додавання зображення в основний документ:
        Image image2 = Image.getInstance("./src/main/resources/IBMLogo.png");
        image2.scaleAbsolute(120f, 120f);
        section1.add(image2);

        // додавання якоря в основний документ
        Paragraph title2 = new Paragraph("Using Anchor",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 0, 0)));
        section1.add(title2);
        title2.setSpacingBefore(2500);
//        Anchor anchor2 = new Anchor("Back To Top");
//        anchor2.setReference("#BackToTop");
//        section1.add(anchor2);
        Anchor anchor = new Anchor("iText API");
        anchor.setReference("http://developers.itextpdf.com/examples/itext-building-blocks");
        section1.add(anchor);

        document.add(chapter1);
        document.close();
    }
}
