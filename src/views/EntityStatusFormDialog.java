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

import models.EntityStatus;

public class EntityStatusFormDialog extends JDialog {

	private JTextField txtIdUser;
	private JTextField txtIdNpc;
	private JTextField txtIdStatusEffect;

	private JButton btnSave;
	private JButton btnCancel;

	private EntityStatus entityStatus;
	private boolean saved = false;

	public EntityStatusFormDialog(JFrame parent, EntityStatus entityStatus) {
		super(parent, true);

		this.entityStatus = entityStatus;

		setTitle(entityStatus == null ? "Add Entity Status" : "Edit Entity Status");

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
		panel.add(new JLabel("Entity Status Form"));
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
		txtIdNpc = new JTextField();
		txtIdStatusEffect = new JTextField();

		panel.add(createField("User ID (Leave empty if NPC):", txtIdUser));
		panel.add(createField("NPC ID (Leave empty if User):", txtIdNpc));
		panel.add(createField("Status Effect ID:", txtIdStatusEffect));

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
		if (entityStatus != null) {
			txtIdUser.setText(entityStatus.getIdUser() != null ? String.valueOf(entityStatus.getIdUser()) : "");
			txtIdNpc.setText(entityStatus.getIdNpc() != null ? String.valueOf(entityStatus.getIdNpc()) : "");
			txtIdStatusEffect.setText(String.valueOf(entityStatus.getIdStatusEffect()));
		}
	}

	private void save() {
		String idUserStr = txtIdUser.getText().trim();
		String idNpcStr = txtIdNpc.getText().trim();
		String idStatusEffectStr = txtIdStatusEffect.getText().trim();

		if (idStatusEffectStr.equals("")) {
			JOptionPane.showMessageDialog(this, "Status Effect ID cannot be empty");
			return;
		}

		if (idUserStr.equals("") && idNpcStr.equals("")) {
			JOptionPane.showMessageDialog(this, "You must specify either a User ID or an NPC ID");
			return;
		}

		try {
			Integer idUser = idUserStr.isEmpty() ? null : Integer.parseInt(idUserStr);
			Integer idNpc = idNpcStr.isEmpty() ? null : Integer.parseInt(idNpcStr);
			int idStatusEffect = Integer.parseInt(idStatusEffectStr);

			if (entityStatus == null) {
				entityStatus = new EntityStatus(idUser, idNpc, idStatusEffect);
			} else {
				entityStatus.setIdUser(idUser);
				entityStatus.setIdNpc(idNpc);
				entityStatus.setIdStatusEffect(idStatusEffect);
			}

			saved = true;
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "IDs must be valid numbers");
		}
	}

	public boolean isSaved() {
		return saved;
	}

	public EntityStatus getEntityStatus() {
		return entityStatus;
	}
}