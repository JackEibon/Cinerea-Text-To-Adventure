package tablemodels;

import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import models.Config;

public class ConfigTableModel extends AbstractTableModel {

	private List<Config> configs;
	private Map<Integer, String> userNames; // Diccionario para los nombres

	private final String[] columns = {"ID", "User", "Setting Key", "Setting Value"};

	public ConfigTableModel(List<Config> configs, Map<Integer, String> userNames) {
		this.configs = configs;
		this.userNames = userNames;
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
			if (userNames != null && userNames.containsKey(config.getIdUser())) {
				return userNames.get(config.getIdUser());
			}
			return "ID: " + config.getIdUser();
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

	public void setUserNames(Map<Integer, String> userNames) {
		this.userNames = userNames;
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