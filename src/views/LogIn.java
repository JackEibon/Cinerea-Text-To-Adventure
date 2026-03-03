package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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

public class LogIn extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JLabel lblErrorEmail, lblErrorPassword, lblErrorConfirm;

    public LogIn() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/img/pixeles.png"); 
        setIconImage(myIcon);
        
        setTitle("Login");
        setSize(900, 600); 
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeCompounds();
        
        setVisible(true);
    }
    
    public void initializeCompounds() {
        Color bgCream = new Color(255, 239, 182);
        
        JPanel centerPanel = new JPanel(); 
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bgCream);
        centerPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(AppFont.title());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        txtEmail = new JTextField();
        lblErrorEmail = addFormGroup(centerPanel, "EMAIL", txtEmail);
        
        txtPassword = new JPasswordField();
        lblErrorPassword = addFormGroup(centerPanel, "PASSWORD", txtPassword);

        txtConfirmPassword = new JPasswordField();
        lblErrorConfirm = addFormGroup(centerPanel, "CONFIRM PASSWORD", txtConfirmPassword);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JButton btnLogin = new JButton("Log In"); 
        pixelBorderText(btnLogin);
        btnLogin.setFont(AppFont.titleSecondary());
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(250, 50));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnLogin.addActionListener(e -> validateAndShow());
        
        centerPanel.add(btnLogin);
        
        add(centerPanel);
    }

    private JLabel addFormGroup(JPanel panel, String labelText, JTextField field) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(AppFont.normalSecondary());
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lbl);
        
        pixelBorderText(field);
        field.setMaximumSize(new Dimension(400, 45));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field);
        
        JLabel lblError = new JLabel(" ");
        lblError.setFont(new Font("Monospaced", Font.BOLD, 12));
        lblError.setForeground(new Color(200, 0, 0)); 
        lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblError.setVisible(false);
        panel.add(lblError);
        
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        return lblError;
    }

    private void pixelBorderText(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        component.setFont(AppFont.normalSecondary());
        component.setBackground(Color.WHITE);
        component.putClientProperty("JComponent.focusWidth", 0);
    }

    private void validateAndShow() {
        lblErrorEmail.setVisible(false);
        lblErrorPassword.setVisible(false);
        lblErrorConfirm.setVisible(false);

        String email = txtEmail.getText().trim();
        String pass = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirmPassword.getPassword());
        boolean isValid = true;

        if (email.isEmpty()) {
            lblErrorEmail.setText("Email is required");
            lblErrorEmail.setVisible(true);
            isValid = false;
        }else if(!email.contains("@")) { 
        	lblErrorEmail.setText("Valid email required"); 
        	lblErrorEmail.setVisible(true); 
        	isValid = false; 
        }

        if (pass.isEmpty()) {
            lblErrorPassword.setText("Password is required");
            lblErrorPassword.setVisible(true);
            isValid = false;
        } else if (!pass.equals(confirm)) {
            lblErrorConfirm.setText("Passwords do not match");
            lblErrorConfirm.setVisible(true);
            isValid = false;
        }

        if (isValid) {
            JOptionPane.showMessageDialog(this, "Welcome, " + email + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        
        revalidate();
        repaint();
    }
}