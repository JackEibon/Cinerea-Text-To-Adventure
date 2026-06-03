package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Score;

public class ScoreFormDialog extends JDialog {

	private JTextField txtIdUser;
	private JTextField txtBestScore;
	private JTextField txtLastScore;

	private JButton btnSave;
	private JButton btnCancel;

	private Score score;
	private boolean saved = false;

	public ScoreFormDialog(JFrame parent, Score score) {
		super(parent, true);

		this.score = score;

		setTitle(score == null ? "Add score" : "Edit score");

		setSize(400, 300);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		add(createTitlePanel(), BorderLayout.NORTH);
		add(createFormPanel());
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Score Form"));
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();

		btnSave = new JButton("Save");
		btnCancel = new JButton("Cancel");

		panel.add(btnSave);
		panel.add(btnCancel);

		btnSave.addActionListener(e -> save());
		btnCancel.addActionListener(e -> dispose());

		return panel;
	}

	private JScrollPane createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBar(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);

		txtIdUser = new JTextField();
		txtBestScore = new JTextField();
		txtLastScore = new JTextField();

		panel.add(createField("User ID:", txtIdUser));
		panel.add(createField("Best Score:", txtBestScore));
		panel.add(createField("Last Score:", txtLastScore));

		loadData();
		return scroll;
	}

	private JPanel createField(String labelText, Component field) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(field);

		return panel;
	}

	private void loadData() {
		if(score != null) {
			txtIdUser.setText(String.valueOf(score.getIdUser()));
			txtBestScore.setText(String.valueOf(score.getBestScore()));
			txtLastScore.setText(String.valueOf(score.getLastScore()));
		}
	}

	private void save() {
		String idUserStr = txtIdUser.getText();
		String bestScoreStr = txtBestScore.getText();
		String lastScoreStr = txtLastScore.getText();

		if(idUserStr == null || idUserStr.equals("") || bestScoreStr == null || bestScoreStr.equals("") || lastScoreStr == null || lastScoreStr.equals("")) {
			JOptionPane.showMessageDialog(this, "One of the fields is empty");
			return;
		}

		try {
			int idUser = Integer.parseInt(idUserStr);
			int bestScore = Integer.parseInt(bestScoreStr);
			int lastScore = Integer.parseInt(lastScoreStr);

			if(score == null) {
				score = new Score(idUser, bestScore, lastScore);
			} else {
				score.setIdUser(idUser);
				score.setBestScore(bestScore);
				score.setLastScore(lastScore);
			}

			saved = true;
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "All fields must be valid numbers");
		}
	}

	public boolean isSaved() {
		return saved;
	}

	public Score getScore() {
		return score;
	}
}