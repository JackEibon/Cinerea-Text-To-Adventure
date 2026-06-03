package tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Status;

public class StatusTableModel extends AbstractTableModel {

	private List<Status> statuses;

	private final String[] columns = {"ID", "Effect Name", "Duration"};

	public StatusTableModel(List<Status> statuses) {
		this.statuses = statuses;
	}

	@Override
	public int getRowCount() {
		return statuses.size();
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
		Status status = statuses.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return status.getIdStatus();
		case 1:
			return status.getEffectName();
		case 2:
			return status.getDuration();
		}
		return status;
	}

	public Status getStatusAt(int row) {
		return statuses.get(row);
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		statuses.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(Status status) {
		int row = statuses.size();
		statuses.add(status);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, Status status) {
		statuses.set(row, status);
		fireTableRowsUpdated(row, row);
	}
}