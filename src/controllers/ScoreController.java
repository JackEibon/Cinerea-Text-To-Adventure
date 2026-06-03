package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Score;
import repository.ScoreRepository;
import tablemodels.ScoreTableModel;
import views.ScoreFormDialog;
import views.ScoreView;
import services.PDFExporter;

public class ScoreController {

	private ScoreView view;
	private ScoreRepository repo;
	private ScoreTableModel model;
	private PDFExporter pdfExporter;

	public ScoreController(ScoreView view) {
		this.view = view;
		repo = new ScoreRepository();
		pdfExporter = new PDFExporter();

		this.view.getBtnAdd().addActionListener(e -> openForm(null));

		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Select a score");
				return;
			}
			openForm(model.getScoreAt(row));
		});

		this.view.getBtnPdf().addActionListener(e -> generatePdf());

		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Select a score");
				return;
			}
			boolean deleted = repo.delete(model.getScoreAt(row).getIdScore());
			if (deleted)
				model.removeRow(row);
		});
	}

	public void generatePdf() {
		File file = view.selectPdfFile();
		if (file == null)
			return;
		try {
			pdfExporter.exportScores(repo.getScores(), file);
			if (Desktop.isDesktopSupported())
				Desktop.getDesktop().open(file);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Exporting Error");
		}
	}

	public void loadScores() {
		try {
			List<Score> scores = repo.getScores();
			if (model == null) {
				model = new ScoreTableModel(scores);
				view.setTableModel(model);
			} else {
				model.setScores(scores);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}

	private void openForm(Score score) {
		ScoreFormDialog dialog = new ScoreFormDialog(null, score);
		dialog.setVisible(true);
		if (dialog.isSaved()) {
			Score savedScore = dialog.getScore();
			try {
				if (score == null) {
					repo.save(savedScore);
					loadScores();
				} else {
					int row = view.getSelectedRow();
					boolean updated = repo.update(row, savedScore);
					if (updated)
						loadScores();
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		}
	}
}