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

import models.Npc;

public class NpcFormDialog extends JDialog {

	private JTextField txtNpcName;
	private JTextField txtNpcRole;
	private JTextField txtLocation;

	private JButton btnSave;
	private JButton btnCancel;

	private Npc npc;
	private boolean saved = false;

	public NpcFormDialog(JFrame parent, Npc npc) {
		super(parent, true);

		this.npc = npc;

		setTitle(npc == null ? "Add NPC" : "Edit NPC");

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
		panel.add(new JLabel("NPC Form"));
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

		txtNpcName = new JTextField();
		txtNpcRole = new JTextField();
		txtLocation = new JTextField();

		panel.add(createField("Name:", txtNpcName));
		panel.add(createField("Role:", txtNpcRole));
		panel.add(createField("Location:", txtLocation));

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
		if(npc != null) {
			txtNpcName.setText(npc.getNpcName());
			txtNpcRole.setText(npc.getNpcRole());
			txtLocation.setText(npc.getLocation());
		}
	}

	private void save() {
		String npcName = txtNpcName.getText();
		String npcRole = txtNpcRole.getText();
		String location = txtLocation.getText();

		if(npcName == null || npcName.equals("") || npcRole == null || npcRole.equals("")) {
			JOptionPane.showMessageDialog(this, "Name and Role fields cannot be empty");
			return;
		}

		if(npc == null) {
			npc = new Npc(npcName, npcRole, location);
		} else {
			npc.setNpcName(npcName);
			npc.setNpcRole(npcRole);
			npc.setLocation(location);
		}

		saved = true;
		dispose();
	}

	public boolean isSaved() {
		return saved;
	}

	public Npc getNpc() {
		return npc;
	}
}