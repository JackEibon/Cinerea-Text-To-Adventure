package services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import models.User;
import models.Config;
import models.Item;

public class PDFExporter {

	public void exportUsers(List<User> users, File file) throws IOException {
		try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
				Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());) {

			addLogo(doc);

			doc.add(new Paragraph("Users Report").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));
			doc.add(new Paragraph("").setMarginTop(30));

			float[] columnsWidth = { 1, 4, 4, 3, 3, 3 };
			Table table = new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();
			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

			Cell cell = new Cell(1, 6).add(new Paragraph("System Users")).setFont(font).setFontSize(14)
					.setFontColor(DeviceGray.WHITE).setBackgroundColor(new DeviceRgb(45, 111, 164))
					.setTextAlignment(TextAlignment.CENTER);
			table.addHeaderCell(cell);

			for (int i = 0; i < 2; i++) {
				Cell[] headerFooter = new Cell[] { createHeaderFooterCell("#"), createHeaderFooterCell("Nickname"),
						createHeaderFooterCell("Email"), createHeaderFooterCell("Weapon"),
						createHeaderFooterCell("Gem"), createHeaderFooterCell("Element") };

				for (Cell celda : headerFooter) {
					if (i == 0)
						table.addHeaderCell(celda);
					else
						table.addFooterCell(celda);
				}
			}

			int indx = 1;
			for (User u : users) {
				table.addCell(
						new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(indx))));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(u.getNickname())));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(u.getEmail())));
				table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(u.getWeapon())));
				table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(u.getGem())));
				table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(u.getElements())));
				indx++;
			}
			doc.add(table);
		}
	}

	public void exportItems(List<Item> items, File file) throws IOException {
		try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
				Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());) {

			addLogo(doc);
			doc.add(new Paragraph("Items Report").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));
			doc.add(new Paragraph("").setMarginTop(30));

			float[] columnsWidth = { 1, 3, 3, 5 };
			Table table = new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();
			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

			Cell cell = new Cell(1, 4).add(new Paragraph("Game Items")).setFont(font).setFontSize(14)
					.setFontColor(DeviceGray.WHITE).setBackgroundColor(new DeviceRgb(45, 111, 164))
					.setTextAlignment(TextAlignment.CENTER);
			table.addHeaderCell(cell);

			for (int i = 0; i < 2; i++) {
				Cell[] headerFooter = new Cell[] { createHeaderFooterCell("#"), createHeaderFooterCell("Name"),
						createHeaderFooterCell("Description"), createHeaderFooterCell("Type") };
				for (Cell celda : headerFooter) {
					if (i == 0)
						table.addHeaderCell(celda);
					else
						table.addFooterCell(celda);
				}
			}

			int indx = 1;
			for (Item item : items) {
				table.addCell(
						new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(indx))));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(item.getName())));
				table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(item.getDescription())));
				table.addCell(
						new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(item.getTagList())));
				indx++;
			}
			doc.add(table);
		}
	}
	
	private void addLogo(Document doc) throws IOException {
		InputStream is = getClass().getResourceAsStream("/assets/img/pixeles.png");
		if (is != null) {
			ImageData data = ImageDataFactory.create(is.readAllBytes());
			Image img = new Image(data).scaleAbsolute(50, 50);
			float altoPagina = PageSize.LETTER.rotate().getHeight();
			float margen = 40;
			img.setFixedPosition(margen, altoPagina - margen - 50);
			doc.add(img);
		}
	}

	private Cell createHeaderFooterCell(String text) {
		return new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
				.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph(text));
	}
}