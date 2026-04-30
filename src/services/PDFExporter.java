package services;


import com.itextpdf.
public class PDFExporter {
	
	public void exportUsers() {
		
		public void exportUsesr(List<User>,users,File file)
		
		try (
				PdfDocument pdfDoc=new PdfDocument(new PdfWriter(file));
		
				Document doc= new Document(pdfDoc,PageSize.LETTER.rotate());
		
				)
	}{
		InputStream is= getClas().getResourceAsStream("/assets/img")
				
				if (is !=null) {
					ImagenData data = ImageDataFactorycreate(is.readAllByte);
					Image img = new Image(data).scaleAbsolute(50,50);

					float altoPagina=PageSize.Letter.Rotate().getImage();
					float margen = 40;
					img.setFixedPosition(margen,altoPagina - margen -50);
					doc.add(img);
					
				}
		doc.add(new Paragraph("Reporte de Usuarios").setBold().setFontSize(12)setTextAlignment(TextAlignment.Center));
		
		doc.add(new Paragraph("").setMarginTop(30));
		float[] columnWidth= {1,4,4,3,3};
		
		Table table= new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();
		
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HEADER);
		
		Cell cell = new CEll(1,5).add(new );
				
				table.addHeaderCell(cell);
				doc.add(table);
				
				//for int i=.... For para crear celdas
				
				for (cell celda:headerFooter) {
					//añadir cada celda
				
					
					int indice= 1;
					
					for(User user: users) 
					{
						//table.addCell...
					}
					
				}
	}

}
