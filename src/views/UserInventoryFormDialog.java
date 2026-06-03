package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.UserInventory;

public class UserInventoryFormDialog extends JDialog {

	private JTextField txtIdUser;
	private JTextField txtIdItem;
	private JTextField txtQuantity;

	private JButton btnSave;
	private JButton btnCancel;

	private UserInventory userInventory;
	private boolean saved = false;

	public UserInventoryFormDialog(JFrame parent, UserInventory userInventory) {
		super(parent, true);

		this.userInventory = userInventory;

		setTitle(userInventory == null ? "Add to inventory" : "Edit inventory");

		setSize(400, 300);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		add(createTitlePanel(), BorderLayout.NORTH);
		add(createFormPanel());
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("User Inventory Form"));
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();

		btnSave = new JButton("Save");
		btnCancel = new JButton("Cancel");

		panel.add(btnSave);
		panel.add(btnCancel);

		btnSave.addActionListener(e -> save());
		btnCancel.addActionListener(e -> dispose());

		return panel;
	}

	private JScrollPane createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBar(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);

		txtIdUser = new JTextField();
		txtIdItem = new JTextField();
		txtQuantity = new JTextField();

		panel.add(createField("User ID:", txtIdUser));
		panel.add(createField("Item ID:", txtIdItem));
		panel.add(createField("Quantity:", txtQuantity));

		loadData();
		return scroll;
	}

	private JPanel createField(String labelText, Component field) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(field);

		return panel;
	}

	private void loadData() {
		if(userInventory != null) {
			txtIdUser.setText(String.valueOf(userInventory.getIdUser()));
			txtIdItem.setText(String.valueOf(userInventory.getIdItem()));
			txtQuantity.setText(String.valueOf(userInventory.getQuantity()));
		}
	}

	private void save() {
		String idUserStr = txtIdUser.getText();
		String idItemStr = txtIdItem.getText();
		String quantityStr = txtQuantity.getText();

		if(idUserStr == null || idUserStr.equals("") || idItemStr == null || idItemStr.equals("") || quantityStr == null || quantityStr.equals("")) {
			JOptionPane.showMessageDialog(this, "One of the fields is empty");
			return;
		}

		try {
			int idUser = Integer.parseInt(idUserStr);
			int idItem = Integer.parseInt(idItemStr);
			int quantity = Integer.parseInt(quantityStr);

			if(userInventory == null) {
				userInventory = new UserInventory(idUser, idItem, quantity);
			} else {
				userInventory.setIdUser(idUser);
				userInventory.setIdItem(idItem);
				userInventory.setQuantity(quantity);
			}

			saved = true;
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "All fields must be valid numbers");
		}
	}

	public boolean isSaved() {
		return saved;
	}

	public UserInventory getUserInventory() {
		return userInventory;
	}
}