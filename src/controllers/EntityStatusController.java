package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.EntityStatus;
import repository.EntityStatusRepository;
import tablemodels.EntityStatusTableModel;
import views.EntityStatusFormDialog;
import views.EntityStatusView;
import services.PDFExporter;

public class EntityStatusController {

	private EntityStatusView view;
	private EntityStatusRepository repo;
	private EntityStatusTableModel model;
	private PDFExporter pdfExporter;
	
	public EntityStatusController(EntityStatusView view) {
		this.view = view;
		repo = new EntityStatusRepository();
		pdfExporter = new PDFExporter();
		
		this.view.getBtnAdd().addActionListener(e -> openForm(null));
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select an entity status");
				return;
			}
			openForm(model.getEntityStatusAt(row));
		});
		
		this.view.getBtnPdf().addActionListener(e -> generatePdf());
		
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select an entity status");
				return;
			}
			boolean deleted = repo.delete(model.getEntityStatusAt(row).getIdEntityStatus());
			if(deleted) model.removeRow(row);
		});
	}
	
	public void generatePdf() {
		File file = view.selectPdfFile();
		if(file == null) return;
		try {
			pdfExporter.exportEntityStatuses(repo.getEntityStatuses(), file);
			if(Desktop.isDesktopSupported()) Desktop.getDesktop().open(file);
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}
	
	public void loadEntityStatuses() {
		try {
			List<EntityStatus> statuses = repo.getEntityStatuses();
			if(model == null) {
				model = new EntityStatusTableModel(statuses, null, null, null); 
				view.setTableModel(model);
			}else {
				model.setEntityStatuses(statuses);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void openForm(EntityStatus status) {
		EntityStatusFormDialog dialog = new EntityStatusFormDialog(null, status);
		dialog.setVisible(true);
		if(dialog.isSaved()) {
			EntityStatus savedStatus = dialog.getEntityStatus();
			try {
				if(status == null) {
					repo.save(savedStatus);
					model.addRow(savedStatus);
				} else {
					int row = view.getSelectedRow();
					boolean updated = repo.update(row, savedStatus);
					if(updated) model.updateRow(row, savedStatus); 
				}
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		}
	}
}