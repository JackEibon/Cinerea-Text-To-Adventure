package tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Npc;

public class NpcTableModel extends AbstractTableModel {

	private List<Npc> npcs;

	private final String[] columns = {"ID", "Name", "Role", "Location"};

	public NpcTableModel(List<Npc> npcs) {
		this.npcs = npcs;
	}

	@Override
	public int getRowCount() {
		return npcs.size();
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
		Npc npc = npcs.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return npc.getIdNpc();
		case 1:
			return npc.getNpcName();
		case 2:
			return npc.getNpcRole();
		case 3:
			return npc.getLocation();
		}
		return npc;
	}

	public Npc getNpcAt(int row) {
		return npcs.get(row);
	}

	public void setNpcs(List<Npc> npcs) {
		this.npcs = npcs;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		npcs.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(Npc npc) {
		int row = npcs.size();
		npcs.add(npc);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, Npc npc) {
		npcs.set(row, npc);
		fireTableRowsUpdated(row, row);
	}
}