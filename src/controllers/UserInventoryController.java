package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.UserInventory;
import repository.UserInventoryRepository;
import tablemodels.UserInventoryTableModel;
import views.UserInventoryFormDialog;
import views.UserInventoryView;
import services.PDFExporter;

public class UserInventoryController {

	private UserInventoryView view;
	private UserInventoryRepository repo;
	private UserInventoryTableModel model;
	private PDFExporter pdfExporter;
	
	public UserInventoryController(UserInventoryView view) {
		this.view = view;
		repo = new UserInventoryRepository();
		pdfExporter = new PDFExporter();
		
		this.view.getBtnAdd().addActionListener(e -> openForm(null));
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select an inventory record");
				return;
			}
			openForm(model.getUserInventoryAt(row));
		});
		
		this.view.getBtnPdf().addActionListener(e -> generatePdf());
		
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select an inventory record");
				return;
			}
			boolean deleted = repo.delete(model.getUserInventoryAt(row).getIdInventory());
			if(deleted) model.removeRow(row);
		});
	}
	
	public void generatePdf() {
		File file = view.selectPdfFile();
		if(file == null) return;
		try {
			pdfExporter.exportUserInventories(repo.getUserInventories(), file);
			if(Desktop.isDesktopSupported()) Desktop.getDesktop().open(file);
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}
	
	public void loadUserInventories() {
		try {
			List<UserInventory> inventories = repo.getUserInventories();
			if(model == null) {
				model = new UserInventoryTableModel(inventories, null, null); 
				view.setTableModel(model);
			}else {
				model.setInventories(inventories);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void openForm(UserInventory inventory) {
		UserInventoryFormDialog dialog = new UserInventoryFormDialog(null, inventory);
		dialog.setVisible(true);
		if(dialog.isSaved()) {
			UserInventory savedInventory = dialog.getUserInventory();
			try {
				if(inventory == null) {
					repo.save(savedInventory);
					model.addRow(savedInventory);
				} else {
					int row = view.getSelectedRow();
					boolean updated = repo.update(row, savedInventory);
					if(updated) model.updateRow(row, savedInventory); 
				}
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		}
	}
}