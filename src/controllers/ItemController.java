package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Item;
import repository.ItemRepository;
import tablemodels.ItemTableModel;
import views.ItemFormDialog;
import views.ItemView;
import services.PDFExporter;

public class ItemController {

	private ItemView view;
	private ItemRepository repo;
	private ItemTableModel model;
	private PDFExporter pdfExporter;
	
	public ItemController(ItemView view) {
		this.view = view;
		repo = new ItemRepository();
		pdfExporter = new PDFExporter();
		
		this.view.getBtnAdd().addActionListener(e -> openForm(null));
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select an item");
				return;
			}
			openForm(model.getItemAt(row));
		});
		
		this.view.getBtnPdf().addActionListener(e -> generatePdf());
		
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select an item");
				return;
			}
			boolean deleted = repo.delete(model.getItemAt(row).getIdItem());
			if(deleted) model.removeRow(row);
		});
	}
	
	public void generatePdf() {
		File file = view.selectPdfFile();
		if(file == null) return;
		try {
			pdfExporter.exportItems(repo.getItems(), file);
			if(Desktop.isDesktopSupported()) Desktop.getDesktop().open(file);
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}
	
	public void loadItems() {
		try {
			List<Item> items = repo.getItems();
			if(model == null) {
				model = new ItemTableModel(items);
				view.setTableModel(model);
			}else {
				model.setItems(items);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void openForm(Item item) {
		ItemFormDialog dialog = new ItemFormDialog(null, item);
		dialog.setVisible(true);
		if(dialog.isSaved()) {
			Item savedItem = dialog.getItem();
			try {
				if(item == null) {
					repo.save(savedItem);
					model.addRow(savedItem);
				} else {
					int row = view.getSelectedRow();
					boolean updated = repo.update(row, savedItem);
					if(updated) model.updateRow(row, savedItem); 
				}
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		}
	}
}