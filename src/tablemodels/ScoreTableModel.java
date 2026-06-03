package tablemodels;

import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import models.Score;

public class ScoreTableModel extends AbstractTableModel {

	private List<Score> scores;
	private Map<Integer, String> userNames;

	private final String[] columns = {"ID", "User", "Best Score", "Last Score"};

	public ScoreTableModel(List<Score> scores, Map<Integer, String> userNames) {
		this.scores = scores;
		this.userNames = userNames;
	}

	@Override
	public int getRowCount() {
		return scores.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Score score = scores.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return score.getIdScore();
		case 1:
			if (userNames != null && userNames.containsKey(score.getIdUser())) {
				return userNames.get(score.getIdUser());
			}
			return "ID: " + score.getIdUser();
		case 2:
			return score.getBestScore();
		case 3:
			return score.getLastScore();
		}
		return score;
	}

	public Score getScoreAt(int row) {
		return scores.get(row);
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
		fireTableDataChanged();
	}

	public void setUserNames(Map<Integer, String> userNames) {
		this.userNames = userNames;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		scores.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(Score score) {
		int row = scores.size();
		scores.add(score);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, Score score) {
		scores.set(row, score);
		fireTableRowsUpdated(row, row);
	}
}