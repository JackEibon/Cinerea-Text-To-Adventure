package tablemodels;

import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import models.UserInventory;

public class UserInventoryTableModel extends AbstractTableModel {

	private List<UserInventory> inventories;
	private Map<Integer, String> userNames;
	private Map<Integer, String> itemNames;

	private final String[] columns = {"ID", "User", "Item", "Quantity"};

	public UserInventoryTableModel(List<UserInventory> inventories, 
			Map<Integer, String> userNames, 
			Map<Integer, String> itemNames) {
		this.inventories = inventories;
		this.userNames = userNames;
		this.itemNames = itemNames;
	}

	@Override
	public int getRowCount() {
		return inventories.size();
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
		UserInventory inventory = inventories.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return inventory.getIdInventory();
		case 1:
			if (userNames != null && userNames.containsKey(inventory.getIdUser())) {
				return userNames.get(inventory.getIdUser());
			}
			return "ID: " + inventory.getIdUser();
		case 2:
			if (itemNames != null && itemNames.containsKey(inventory.getIdItem())) {
				return itemNames.get(inventory.getIdItem());
			}
			return "ID: " + inventory.getIdItem();
		case 3:
			return inventory.getQuantity();
		}
		return inventory;
	}

	public UserInventory getUserInventoryAt(int row) {
		return inventories.get(row);
	}

	public void setInventories(List<UserInventory> inventories) {
		this.inventories = inventories;
		fireTableDataChanged();
	}

	public void setDictionaries(Map<Integer, String> userNames, Map<Integer, String> itemNames) {
		this.userNames = userNames;
		this.itemNames = itemNames;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		inventories.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(UserInventory inventory) {
		int row = inventories.size();
		inventories.add(inventory);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, UserInventory inventory) {
		inventories.set(row, inventory);
		fireTableRowsUpdated(row, row);
	}
}