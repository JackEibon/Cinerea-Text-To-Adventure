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

import models.Item;

public class ItemFormDialog extends JDialog {

	private JTextField txtItemName;
	private JTextField txtDescription;
	private JTextField txtTags;

	private JButton btnSave;
	private JButton btnCancel;

	private Item item;
	private boolean saved = false;

	public ItemFormDialog(JFrame parent, Item item) {
		super(parent, true);

		this.item = item;

		setTitle(item == null ? "Add item" : "Edit item");

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
		panel.add(new JLabel("Item Form"));
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

		txtItemName = new JTextField();
		txtDescription = new JTextField();
		txtTags = new JTextField();

		panel.add(createField("Name:", txtItemName));
		panel.add(createField("Description:", txtDescription));
		panel.add(createField("Tags:", txtTags));

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
		if(item != null) {
			txtItemName.setText(item.getName());
			txtDescription.setText(item.getDescription());
			txtTags.setText(item.getTagList());
		}
	}

	private void save() {
		String itemName = txtItemName.getText();
		String description = txtDescription.getText();
		String tags= txtTags.getText();

		if(itemName == null || itemName.equals("") || description == null || description.equals("") || tags == null || tags.equals("")) {
			JOptionPane.showMessageDialog(this, "One of the fields is empty");
			return;
		}

		if(item == null) {
			item = new Item(itemName,description,tags);
		} else {
			item.setName(itemName);
			item.setDescription(description);
			item.setTags(tags);
		}

		saved = true;
		dispose();
	}

	public boolean isSaved() {
		return saved;
	}

	public Item getItem() {
		return item;
	}
}