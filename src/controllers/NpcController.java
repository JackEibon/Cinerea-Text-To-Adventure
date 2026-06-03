package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Npc;
import repository.NpcRepository;
import tablemodels.NpcTableModel;
import views.NpcFormDialog;
import views.NpcView;
import services.PDFExporter;

public class NpcController {

	private NpcView view;
	private NpcRepository repo;
	private NpcTableModel model;
	private PDFExporter pdfExporter;

	public NpcController(NpcView view) {
		this.view = view;
		repo = new NpcRepository();
		pdfExporter = new PDFExporter();

		this.view.getBtnAdd().addActionListener(e -> openForm(null));

		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Select an NPC");
				return;
			}
			openForm(model.getNpcAt(row));
		});

		this.view.getBtnPdf().addActionListener(e -> generatePdf());

		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Select an NPC");
				return;
			}
			boolean deleted = repo.delete(model.getNpcAt(row).getIdNpc());
			if (deleted)
				model.removeRow(row);
		});
	}

	public void generatePdf() {
		File file = view.selectPdfFile();
		if (file == null)
			return;
		try {
			pdfExporter.exportNpcs(repo.getNpcs(), file);
			if (Desktop.isDesktopSupported())
				Desktop.getDesktop().open(file);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}

	public void loadNpcs() {
		try {
			List<Npc> npcs = repo.getNpcs();
			if (model == null) {
				model = new NpcTableModel(npcs);
				view.setTableModel(model);
			} else {
				model.setNpcs(npcs);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}

	private void openForm(Npc npc) {
		NpcFormDialog dialog = new NpcFormDialog(null, npc);
		dialog.setVisible(true);
		if (dialog.isSaved()) {
			Npc savedNpc = dialog.getNpc();
			try {
				if (npc == null) {
					repo.save(savedNpc);
					model.addRow(savedNpc);
				} else {
					int row = view.getSelectedRow();
					boolean updated = repo.update(row, savedNpc);
					if (updated)
						model.updateRow(row, savedNpc);
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		}
	}
}