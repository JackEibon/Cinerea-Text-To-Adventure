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

import models.Status;

public class StatusFormDialog extends JDialog {

	private JTextField txtEffectName;
	private JTextField txtDuration;

	private JButton btnSave;
	private JButton btnCancel;

	private Status status;
	private boolean saved = false;

	public StatusFormDialog(JFrame parent, Status status) {
		super(parent, true);

		this.status = status;

		setTitle(status == null ? "Add status effect" : "Edit status effect");

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
		panel.add(new JLabel("Status Effect Form"));
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

		txtEffectName = new JTextField();
		txtDuration = new JTextField();

		panel.add(createField("Effect Name:", txtEffectName));
		panel.add(createField("Duration:", txtDuration));

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
		if (status != null) {
			txtEffectName.setText(status.getEffectName());
			txtDuration.setText(String.valueOf(status.getDuration()));
		}
	}

	private void save() {
		String effectName = txtEffectName.getText();
		String durationStr = txtDuration.getText();

		if (effectName == null || effectName.equals("") || durationStr == null || durationStr.equals("")) {
			JOptionPane.showMessageDialog(this, "One of the fields is empty");
			return;
		}

		try {
			int duration = Integer.parseInt(durationStr);

			if (status == null) {
				status = new Status(effectName, duration);
			} else {
				status.setEffectName(effectName);
				status.setDuration(duration);
			}

			saved = true;
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Duration must be a valid number");
		}
	}

	public boolean isSaved() {
		return saved;
	}

	public Status getStatus() {
		return status;
	}
}