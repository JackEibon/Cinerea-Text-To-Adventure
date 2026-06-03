package tablemodels;

import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import models.EntityStatus;

public class EntityStatusTableModel extends AbstractTableModel {

	private List<EntityStatus> entityStatuses;
	private Map<Integer, String> userNames;
	private Map<Integer, String> npcNames;
	private Map<Integer, String> effectNames;

	private final String[] columns = { "ID", "User", "NPC", "Status Effect" };

	public EntityStatusTableModel(List<EntityStatus> entityStatuses, Map<Integer, String> userNames,
			Map<Integer, String> npcNames, Map<Integer, String> effectNames) {
		this.entityStatuses = entityStatuses;
		this.userNames = userNames;
		this.npcNames = npcNames;
		this.effectNames = effectNames;
	}

	@Override
	public int getRowCount() {
		return entityStatuses.size();
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
		EntityStatus status = entityStatuses.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return status.getIdEntityStatus();
		case 1:
			if (status.getIdUser() != null) {
				return (userNames != null && userNames.containsKey(status.getIdUser()))
						? userNames.get(status.getIdUser())
						: "ID: " + status.getIdUser();
			}
			return "N/A";
		case 2:
			if (status.getIdNpc() != null) {
				return (npcNames != null && npcNames.containsKey(status.getIdNpc())) ? npcNames.get(status.getIdNpc())
						: "ID: " + status.getIdNpc();
			}
			return "N/A";
		case 3:
			return (effectNames != null && effectNames.containsKey(status.getIdStatusEffect()))
					? effectNames.get(status.getIdStatusEffect())
					: "ID: " + status.getIdStatusEffect();
		}
		return status;
	}

	public EntityStatus getEntityStatusAt(int row) {
		return entityStatuses.get(row);
	}

	public void setEntityStatuses(List<EntityStatus> entityStatuses) {
		this.entityStatuses = entityStatuses;
		fireTableDataChanged();
	}

	public void setDictionaries(Map<Integer, String> userNames, Map<Integer, String> npcNames,
			Map<Integer, String> effectNames) {
		this.userNames = userNames;
		this.npcNames = npcNames;
		this.effectNames = effectNames;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		entityStatuses.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(EntityStatus entityStatus) {
		int row = entityStatuses.size();
		entityStatuses.add(entityStatus);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, EntityStatus entityStatus) {
		entityStatuses.set(row, entityStatus);
		fireTableRowsUpdated(row, row);
	}
}