package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.User;

public class UserTableModel extends AbstractTableModel{
	
	private List<User> users;
	
	private final String[] columns = {
			"Nickname",
			"Email",
			"Gem",
			"Weapon",
			"Element"
	};
	
	public UserTableModel(List<User> users) {
		this.users = users;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return users.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}
	
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = users.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return user.getNickname();
		case 1:
			return user.getEmail();
		case 2:
			return user.getGem();
		case 3:
			return user.getWeapon();
		case 4:
			return user.getElements();
		}
		return user;
	}
	
	public User getUserAt(int row) {
		return users.get(row);
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
		fireTableDataChanged();
	}
	
	public void removeRow(int row) {
		users.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
	public void addRow(User user) {
		int row = users.size();
		users.add(user);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, User user) {
		users.set(row, user);
		fireTableRowsUpdated(row, row);
	}
	
}
