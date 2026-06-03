package tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Config;

public class ConfigTableModel extends AbstractTableModel {

	private List<Config> configs;

	private final String[] columns = { "ID", "User", "Setting Key", "Setting Value" };

	public ConfigTableModel(List<Config> configs) {
		this.configs = configs;
	}

	@Override
	public int getRowCount() {
		return configs.size();
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
		Config config = configs.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return config.getIdConfig();
		case 1:
			return config.getNickname() != null ? config.getNickname() : "ID: " + config.getIdUser();
		case 2:
			return config.getSettingKey();
		case 3:
			return config.getSettingValue();
		}
		return config;
	}

	public Config getConfigAt(int row) {
		return configs.get(row);
	}

	public void setConfigs(List<Config> configs) {
		this.configs = configs;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		configs.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(Config config) {
		int row = configs.size();
		configs.add(config);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, Config config) {
		configs.set(row, config);
		fireTableRowsUpdated(row, row);
	}
}