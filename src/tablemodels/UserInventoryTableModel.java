package tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.UserInventory;

public class UserInventoryTableModel extends AbstractTableModel {

	private List<UserInventory> inventories;

	private final String[] columns = {"ID", "User", "Item", "Quantity"};

	public UserInventoryTableModel(List<UserInventory> inventories) {
		this.inventories = inventories;
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
			return inventory.getNickname() != null ? inventory.getNickname() : "ID: " + inventory.getIdUser();
		case 2:
			return inventory.getItemName() != null ? inventory.getItemName() : "ID: " + inventory.getIdItem();
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