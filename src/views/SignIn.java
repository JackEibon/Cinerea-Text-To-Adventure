package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

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

import utils.AppFont;

public class SignIn extends JFrame {

    private JTextField txtEmail, txtNickname;
    private JPasswordField txtPass, txtConfirmPass;
    private JComboBox<String> cbGems, cbWeapon, cbElement;
    private JLabel errEmail, errNickname, errGem, errWeapon, errElement, errPass, errConfirm;

    public SignIn() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/img/pixeles.png"); 
        setIconImage(myIcon);
        
        setTitle("Sign In");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeCompounds();
        setVisible(true);
    }
    
    public void initializeCompounds() {
        Color bgCream = new Color(255, 239, 182);
        Dimension fatSize = new Dimension(350, 45);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bgCream);
        centerPanel.setBorder(new EmptyBorder(35, 80, 35, 80));

        JLabel lblTitle = new JLabel("SIGN IN");
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
        errPass = addFormGroup(centerPanel, "PASSWORD", txtPass);

        txtConfirmPass = new JPasswordField();
        errConfirm = addFormGroup(centerPanel, "CONFIRM PASSWORD", txtConfirmPass);

        JButton btnRegister = new JButton("Sign In");
        pixelBorderText(btnRegister);
        btnRegister.setFont(AppFont.titleSecondary());
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(300, 55));
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        btnRegister.addActionListener(e -> validarRegistro());
        centerPanel.add(btnRegister);

        JScrollPane scroll = new JScrollPane(centerPanel);
        scroll.setBorder(null); 
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().setBackground(bgCream); 
        add(scroll);
    }

    private JLabel addFormGroup(JPanel panel, String labelText, JTextField field) {
        JLabel lbl = new JLabel(labelText);
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
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lbl);
        
        pixelBorderText(combo);
        combo.setMaximumSize(size);
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(combo);
        
        return crearLabelError(panel);
    }

    private JLabel crearLabelError(JPanel panel) {
        JLabel lblError = new JLabel(" ");
        lblError.setFont(new Font("Arial", Font.BOLD, 11));
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

    private void validarRegistro() {
        JLabel[] errores = {errEmail, errNickname, errGem, errWeapon, errElement, errPass, errConfirm};
        for(JLabel l : errores) l.setVisible(false);

        boolean itsTrue = true;

        if(txtEmail.getText().trim().isEmpty()) { 
        	errEmail.setText("Email required"); 
        	errEmail.setVisible(true); itsTrue = false; 
        }
        
        if(txtNickname.getText().trim().isEmpty()) { 
        	errNickname.setText("Nickname required"); 
        	errNickname.setVisible(true); 
        	itsTrue = false; 
        }

        if(cbGems.getSelectedIndex() == 0) { 
        	errGem.setText("Pick a gem"); 
        	errGem.setVisible(true); 
        	itsTrue = false; 
        }
        
        if(cbWeapon.getSelectedIndex() == 0) { 
        	errWeapon.setText("Pick a weapon"); 
        	errWeapon.setVisible(true); 
        	itsTrue = false; 
        }
        
        if(cbElement.getSelectedIndex() == 0) { 
        	errElement.setText("Pick an element"); 
        	errElement.setVisible(true); 
        	itsTrue = false; 
        }

        String password = new String(txtPass.getPassword());
        String secondPassword = new String(txtConfirmPass.getPassword());

        if(password.isEmpty()) { 
        	errPass.setText("Password required"); 
        	errPass.setVisible(true); 
        	itsTrue = false; 
        } else if(!password.equals(secondPassword)) { 
        	errConfirm.setText("Passwords don't match"); 
        	errConfirm.setVisible(true); 
        	itsTrue = false; 
        }

        if(itsTrue) {
            JOptionPane.showMessageDialog(this, "Character Created: " + txtNickname.getText());
        }
        
        revalidate();
        repaint();
    }
}