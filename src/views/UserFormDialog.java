package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.User;

public class UserFormDialog extends JDialog{

	private JTextField txtName;
    private JTextField txtEmail;

    private JComboBox<String> cbGem;
    private JComboBox<String> cbWeapon;
    private JComboBox<String> cbElement;

    private JButton btnSave;
    private JButton btnCancel;

    private User user;
    private boolean saved = false;
    		
    public UserFormDialog(JFrame parent, User user) {
    	super(parent, true);
    	
    	this.user = user;
    	
    	setTitle(user == null ? "Add user" : "Edit user");
    	
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
        panel.add(new JLabel("User Form"));
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

		txtName = new JTextField();

		txtEmail = new JTextField();

		cbGem = new JComboBox<>(new String[]{"Select a gem", "Ruby", "Sapphire", "Emerald", "Opaline", "Amethyst"});

        cbWeapon = new JComboBox<>(new String[]{"Select a weapon", "Spear", "Sword", "Gun"});

        cbElement = new JComboBox<>(new String[]{"Select an element", "Fire", "Earth", "Water", "Wind"});

		panel.add(createField("Name:", txtName));
		panel.add(createField("Email:", txtEmail));
		panel.add(createField("Gem:", cbGem));
		panel.add(createField("Weapon:", cbWeapon));
		panel.add(createField("Element:", cbElement));

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
    	if(user != null) {
    		txtName.setText(user.getNickname());
            txtEmail.setText(user.getEmail());
            cbGem.setSelectedItem(user.getGem());
            cbWeapon.setSelectedItem(user.getWeapon());
            cbElement.setSelectedItem(user.getElements());
    	}
    }
    
    private void save() {
    	String nickname = txtName.getText();
    	String email = txtEmail.getText();
    	String gem = (String) cbGem.getSelectedItem();
    	String element = (String) cbElement.getSelectedItem();
    	String weapon = (String) cbWeapon.getSelectedItem();
    	
    	if(nickname == null || nickname.equals("") || email == null || email.equals("") || gem == "Select a gem" || element == "Select an element" || weapon == "Select a weapon") {
    		JOptionPane.showMessageDialog(this, "One of the fields is empty");
    		return;
    	}
    	
    	if(user == null) {
			user = new User(nickname, email, gem, weapon, element, "USER");

    	} else {
    		user.setNickname(nickname);
    		user.setEmail(email);
    		user.setGem(gem);
    		user.setElements(element);
    		user.setWeapon(weapon);
    	}
    	
    	saved = true;
    	dispose();
    }
    
    public boolean isSaved() {
    	return saved;
    }
    
    public User getUser() {
    	return user;
    }
}