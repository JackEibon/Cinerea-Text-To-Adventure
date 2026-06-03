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

import models.Config;

public class ConfigFormDialog extends JDialog {

	private JTextField txtIdUser;
	private JTextField txtSettingKey;
	private JTextField txtSettingValue;

	private JButton btnSave;
	private JButton btnCancel;

	private Config config;
	private boolean saved = false;

	public ConfigFormDialog(JFrame parent, Config config) {
		super(parent, true);

		this.config = config;

		setTitle(config == null ? "Add config" : "Edit config");

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
		panel.add(new JLabel("Config Form"));
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
		txtSettingKey = new JTextField();
		txtSettingValue = new JTextField();

		panel.add(createField("User ID:", txtIdUser));
		panel.add(createField("Setting Key:", txtSettingKey));
		panel.add(createField("Setting Value:", txtSettingValue));

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
		if(config != null) {
			txtIdUser.setText(String.valueOf(config.getIdUser()));
			txtSettingKey.setText(config.getSettingKey());
			txtSettingValue.setText(config.getSettingValue());
		}
	}

	private void save() {
		String idUserStr = txtIdUser.getText();
		String settingKey = txtSettingKey.getText();
		String settingValue = txtSettingValue.getText();

		if(idUserStr == null || idUserStr.equals("") || settingKey == null || settingKey.equals("") || settingValue == null || settingValue.equals("")) {
			JOptionPane.showMessageDialog(this, "One of the fields is empty");
			return;
		}

		try {
			int idUser = Integer.parseInt(idUserStr);

			if(config == null) {
				config = new Config(idUser, settingKey, settingValue);
			} else {
				config.setIdUser(idUser);
				config.setSettingKey(settingKey);
				config.setSettingValue(settingValue);
			}

			saved = true;
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "User ID must be a valid number");
		}
	}

	public boolean isSaved() {
		return saved;
	}

	public Config getConfig() {
		return config;
	}
}