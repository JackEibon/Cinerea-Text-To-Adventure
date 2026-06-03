package tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Item;

public class ItemTableModel extends AbstractTableModel {

	private List<Item> items;

	private final String[] columns = {"ID", "Name", "Type", "Description"};

	public ItemTableModel(List<Item> items) {
		this.items = items;
	}

	@Override
	public int getRowCount() {
		return items.size();
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
		Item item = items.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return item.getIdItem();
		case 1:
			return item.getItemName();
		case 2:
			return item.getItemType();
		case 3:
			return item.getDescription();
		}
		return item;
	}

	public Item getItemAt(int row) {
		return items.get(row);
	}

	public void setItems(List<Item> items) {
		this.items = items;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		items.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(Item item) {
		int row = items.size();
		items.add(item);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, Item item) {
		items.set(row, item);
		fireTableRowsUpdated(row, row);
	}
}