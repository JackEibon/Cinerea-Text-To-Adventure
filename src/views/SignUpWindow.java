package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.AppFont;

public class SignUpWindow extends JFrame {

    private JTextField txtEmail, txtNickname;
    private JPasswordField txtPass, txtConfirmPass;
    private JComboBox<String> cbGems, cbWeapon, cbElement;
    private JLabel errEmail, errNickname, errGem, errWeapon, errElement, errPassword, errConfirm;

    public SignUpWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/img/pixeles.png"); 
        setIconImage(myIcon);
        
        setTitle("Sign Up");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeCompounds();
        setVisible(true);
    }
    
    public void initializeCompounds() {
        Color bgColor = new Color(255, 239, 182);
        Dimension fatSize = new Dimension(350, 40);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bgColor);
        centerPanel.setBorder(new EmptyBorder(35, 80, 35, 80));

        JLabel lblTitle = new JLabel("SIGN UP");
        lblTitle.setFont(AppFont.title());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        txtEmail = new JTextField();
        errEmail = addFormGroup(centerPanel, "EMAIL", txtEmail);

        txtNickname = new JTextField();
        errNickname = addFormGroup(centerPanel, "NICKNAME", txtNickname);

        cbGems = new JComboBox<>(new String[]{"Select gem", "Ruby", "Sapphire", "Emerald", "Opaline", "Amethyst"});
        errGem = addSelectGroup(centerPanel, "GEM", cbGems, fatSize);

        cbWeapon = new JComboBox<>(new String[]{"Select weapon", "Spear", "Sword", "Gun"});
        errWeapon = addSelectGroup(centerPanel, "WEAPON", cbWeapon, fatSize);

        cbElement = new JComboBox<>(new String[]{"Select element", "Fire", "Earth", "Water", "Wind"});
        errElement = addSelectGroup(centerPanel, "ELEMENT", cbElement, fatSize);

        txtPass = new JPasswordField();
        errPassword = addFormGroup(centerPanel, "PASSWORD", txtPass);

        txtConfirmPass = new JPasswordField();
        errConfirm = addFormGroup(centerPanel, "CONFIRM PASSWORD", txtConfirmPass);

        JButton btnRegister = new JButton();
        addButtonForm("Sign Up", btnRegister);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        btnRegister.addActionListener(e -> validateRegister());
        centerPanel.add(btnRegister);
        
        JButton btnCancel = new JButton();
        addButtonForm("Cancel", btnCancel);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        btnCancel.addActionListener(e -> cancelRegister());
        centerPanel.add(btnCancel);

        JScrollPane scroll = new JScrollPane(centerPanel);
        scroll.setBorder(null); 
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().setBackground(bgColor); 
        add(scroll);
        
        addActionListeners();
    }
    
    private void cancelRegister() {
    	JOptionPane.showConfirmDialog(this, "Cancel Operation?");
        new LogInWindow();
        dispose();
	}

	private void addButtonForm(String labelText, JButton button) {
    	pixelBorderText(button);
    	button.setText(labelText);
    	button.setFont(AppFont.titleSecondary());
    	button.setAlignmentX(Component.CENTER_ALIGNMENT);
    	button.setMaximumSize(new Dimension(250, 50));
    	button.setPreferredSize(new Dimension(250, 50));
    	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JLabel addFormGroup(JPanel panel, String labelText, JTextField field) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(AppFont.titleSecondary());
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lbl);
        
        pixelBorderText(field);
        field.setMaximumSize(new Dimension(350, 40));
        field.setPreferredSize(new Dimension(350, 40));;
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field);
        
        return crearLabelError(panel);
    }

    private JLabel addSelectGroup(JPanel panel, String labelText, JComboBox<String> combo, Dimension size) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(AppFont.titleSecondary());
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lbl);
        
        pixelBorderText(combo);
        combo.setMaximumSize(size);
        combo.setPreferredSize(size);
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(combo);
        
        return crearLabelError(panel);
    }

    private JLabel crearLabelError(JPanel panel) {
        JLabel lblError = new JLabel(" ");
        lblError.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblError.setForeground(new Color(180, 0, 0));
        lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblError.setVisible(false);
        panel.add(lblError);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        return lblError;
    }

    private void pixelBorderText(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        component.setFont(AppFont.normalSecondary());
        component.setBackground(Color.WHITE);
        component.putClientProperty("JComponent.focusWidth", 0);
    }
<<<<<<< Updated upstream:src/views/SignUpWindow.java
    
    private boolean validateTxtEmail() {
    	if(txtEmail.getText().trim().isEmpty()) { 
=======

    private void validarRegistro() {
        JLabel[] errors = {errEmail, errNickname, errGem, errWeapon, errElement, errPass, errConfirm};
        for(JLabel l : errors) l.setVisible(false);

        boolean itsTrue = true;

        if(txtEmail.getText().trim().isEmpty()) { 
>>>>>>> Stashed changes:src/views/SignIn.java
        	errEmail.setText("Email required"); 
        	errEmail.setVisible(true); 
        	return false;
        }else if(!txtEmail.getText().contains("@")) { 
        	errEmail.setText("Valid email required"); 
        	errEmail.setVisible(true);
        	return false;
        }
        errEmail.setText(""); 
        return true;
    }
    
    private boolean validateTxtNickname() {
    	if(txtNickname.getText().trim().isEmpty()) { 
        	errNickname.setText("Nickname required"); 
        	errNickname.setVisible(true); 
        	return false;
        }else if(txtNickname.getText().trim().length() <= 4) {
        	errNickname.setText("5 characters minimum"); 
        	errNickname.setVisible(true); 
        	return false;
        }
    	errNickname.setText(""); 
        return true;
    }
    
    private boolean validateCb(String text, JComboBox<String> combo, JLabel err) {
    	if(combo.getSelectedIndex() == 0) { 
        	err.setText(text); 
        	err.setVisible(true); 
        	return false; 
        }
    	err.setText("");
    	return true;
    }
    
    private void addActionListeners() {
    	txtEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	validateTxtEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateTxtEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	validateTxtEmail();
            }
        });
    	
    	txtNickname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	validateTxtNickname();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	validateTxtNickname();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	validateTxtNickname();
            }
        });
    	
    	cbGems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateCb("Pick a gem", cbGems, errGem);
            }
        });
    	
    	cbWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateCb("Pick a weapon", cbWeapon, errWeapon);
            }
        });
    	
    	cbElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateCb("Pick an element", cbElement, errElement);
            }
        });
    }

    private void validateRegister() {
        JLabel[] errors = {errWeapon, errElement, errPassword, errConfirm};
        for(JLabel l : errors) l.setVisible(false);

        boolean isValid = true;
        
        if(!validateTxtEmail() || !validateTxtNickname() || !validateCb("Pick a gem", cbGems, errGem) || !validateCb("Pick a weapon", cbWeapon, errWeapon) || !validateCb("Pick an element", cbElement, errElement)) {
        	isValid = false;
        }

        String password = new String(txtPass.getPassword());
        String secondPassword = new String(txtConfirmPass.getPassword());

        if(password.isEmpty()) { 
        	errPassword.setText("Password required"); 
        	errPassword.setVisible(true); 
        	isValid = false; 
        } else if(!password.equals(secondPassword)) { 
        	errConfirm.setText("Passwords don't match"); 
        	errConfirm.setVisible(true); 
        	isValid = false; 
        }

        if(isValid) {
            JOptionPane.showMessageDialog(this, "Character Created: " + txtNickname.getText());
            new LogInWindow();
            dispose();
        }
        
        revalidate();
        repaint();
    }
}