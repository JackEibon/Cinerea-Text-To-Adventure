package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Status;
import repository.StatusRepository;
import tablemodels.StatusTableModel;
import views.StatusFormDialog;
import views.StatusView;
import services.PDFExporter;

public class StatusController {

	private StatusView view;
	private StatusRepository repo;
	private StatusTableModel model;
	private PDFExporter pdfExporter;
	
	public StatusController(StatusView view) {
		this.view = view;
		repo = new StatusRepository();
		pdfExporter = new PDFExporter();
		
		this.view.getBtnAdd().addActionListener(e -> openForm(null));
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select a status");
				return;
			}
			openForm(model.getStatusAt(row));
		});
		
		this.view.getBtnPdf().addActionListener(e -> generatePdf());
		
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select a status");
				return;
			}
			boolean deleted = repo.delete(model.getStatusAt(row).getIdStatus());
			if(deleted) model.removeRow(row);
		});
	}
	
	public void generatePdf() {
		File file = view.selectPdfFile();
		if(file == null) return;
		try {
			pdfExporter.exportStatuses(repo.getStatuses(), file);
			if(Desktop.isDesktopSupported()) Desktop.getDesktop().open(file);
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}
	
	public void loadStatuses() {
		try {
			List<Status> statuses = repo.getStatuses();
			if(model == null) {
				model = new StatusTableModel(statuses);
				view.setTableModel(model);
			}else {
				model.setStatuses(statuses);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void openForm(Status status) {
		StatusFormDialog dialog = new StatusFormDialog(null, status);
		dialog.setVisible(true);
		if(dialog.isSaved()) {
			Status savedStatus = dialog.getStatus();
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