package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Config;
import repository.ConfigRepository;
import tablemodels.ConfigTableModel;
import views.ConfigFormDialog;
import views.ConfigView;
import services.PDFExporter;

public class ConfigController {

	private ConfigView view;
	private ConfigRepository repo;
	private ConfigTableModel model;
	private PDFExporter pdfExporter;
	
	public ConfigController(ConfigView view) {
		this.view = view;
		repo = new ConfigRepository();
		pdfExporter = new PDFExporter();
		
		this.view.getBtnAdd().addActionListener(e -> openForm(null));
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select a config");
				return;
			}
			openForm(model.getConfigAt(row));
		});
		
		this.view.getBtnPdf().addActionListener(e -> generatePdf());
		
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Select a config");
				return;
			}
			boolean deleted = repo.delete(model.getConfigAt(row).getIdConfig());
			if(deleted) {
				model.removeRow(row);
			}
		});
	}
	
	public void generatePdf() {
		File file = view.selectPdfFile();
		if(file == null) return;

		try {
			pdfExporter.exportConfigs(repo.getConfigs(), file);
			if(Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}
	
	public void loadConfigs() {
		try {
			List<Config> configs = repo.getConfigs();
			if(model == null) {
				model = new ConfigTableModel(configs, null);
				view.setTableModel(model);
			}else {
				model.setConfigs(configs);
			}
		}catch(IOException ex) { 
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void openForm(Config config) {
		ConfigFormDialog dialog = new ConfigFormDialog(null, config);
		dialog.setVisible(true);
		
		if(dialog.isSaved()) {
			Config savedConfig = dialog.getConfig();
			try {
				if(config == null) {
					repo.save(savedConfig);
					model.addRow(savedConfig);
				} else {
					int row = view.getSelectedRow();
					boolean updated = repo.update(row, savedConfig);
					if(updated) {
						model.updateRow(row, savedConfig); 
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		}
	}
}