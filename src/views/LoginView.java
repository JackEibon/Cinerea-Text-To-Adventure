package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import assets.utils.AppFont;

public class LoginView extends JPanel {

    private LoginWindow window;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel errEmail, errPassword;
    private JButton btnLogin, btnRegister;

    public LoginView(LoginWindow window) {
        this.window = window;
        setLayout(new BorderLayout());
        initializeCompounds();
    }

    public void initializeCompounds() {
        Color bgColor = new Color(92, 122, 237);
        Color bigBdColor = new Color(17, 53, 189);
        Color midBdColor = new Color(52, 86, 217);
        
        JPanel bigPanel = new JPanel();
        bigPanel.setBackground(bigBdColor);
        bigPanel.setLayout(new BorderLayout());
        bigPanel.setBorder(BorderFactory.createLineBorder(bigBdColor, 20));
        
        JPanel midPanel = new JPanel();
        midPanel.setBackground(midBdColor);
        midPanel.setLayout(new BorderLayout());
        midPanel.setBorder(BorderFactory.createLineBorder(midBdColor, 20));
        
        JPanel centerPanel = new JPanel(); 
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bgColor);
        centerPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(AppFont.title());
        lblTitle.setForeground(Color.black);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        txtEmail = new JTextField();
        errEmail = addFormGroup(centerPanel, "EMAIL", txtEmail);
        
        txtPassword = new JPasswordField();
        errPassword = addFormGroup(centerPanel, "PASSWORD", txtPassword);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        btnLogin = new JButton(); 
        addButtonForm("Log In", btnLogin);
        
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                changeBackground(btnLogin);
            }
            public void mouseExited(MouseEvent e) {
                resetBackground(btnLogin);
            }
        });
        
        centerPanel.add(btnLogin);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        btnRegister = new JButton(); 
        addButtonForm("Sign Up", btnRegister);
        
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                changeBackground(btnRegister);
            }
            public void mouseExited(MouseEvent e) {
                resetBackground(btnRegister);
            }
        });
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(btnRegister);
        
        bigPanel.add(midPanel);
        midPanel.add(centerPanel);
        add(bigPanel, BorderLayout.CENTER);
    }

    private void changeBackground(JComponent component) {
        component.setBackground(new Color(17, 53, 189));
        component.setForeground(Color.white);
    }

    private void resetBackground(JComponent component) {
        component.setBackground(Color.white);
        component.setForeground(Color.black);
    }

    private void addButtonForm(String labelText, JButton button) {
        pixelBorderText(button);
        button.setText(labelText);
        button.setFont(AppFont.titleSecondary());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JLabel addFormGroup(JPanel panel, String labelText, JTextField field) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(AppFont.normalSecondary());
        lbl.setForeground(Color.black);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lbl);
        
        pixelBorderText(field);
        field.setMaximumSize(new Dimension(400, 45));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field);
        
        JLabel lblError = new JLabel(" ");
        lblError.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblError.setForeground(Color.CYAN); 
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

    public LoginWindow getWindow() {
        return window;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JLabel getErrEmail() {
        return errEmail;
    }

    public JLabel getErrPassword() {
        return errPassword;
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public char[] getPassword() {
        return txtPassword.getPassword();
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }
}