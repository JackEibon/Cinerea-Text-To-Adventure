package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.AppFont;

public class SignUpView extends JPanel {

    private SignUpWindow window;
    private JTextField txtEmail, txtNickname;
    private JPasswordField txtPass, txtConfirmPass;
    private JComboBox<String> cbGems, cbWeapon, cbElement;
    private JLabel errEmail, errNickname, errGem, errWeapon, errElement, errPassword, errConfirm;
    private JButton btnRegister, btnCancel;

    public SignUpView(SignUpWindow window) {
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

        btnRegister = new JButton();
        addButtonForm("Sign Up", btnRegister);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                changeBackground(btnRegister);
            }
            public void mouseExited(MouseEvent e) {
                resetBackground(btnRegister);
            }
        });
        
        centerPanel.add(btnRegister);
        
        btnCancel = new JButton();
        addButtonForm("Cancel", btnCancel);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        btnCancel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                changeBackground(btnCancel);
            }
            public void mouseExited(MouseEvent e) {
                resetBackground(btnCancel);
            }
        });
        
        btnCancel.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                Image poorIcon = Toolkit.getDefaultToolkit().getImage("src/img/pixelesred.png"); 
                window.setIconImage(poorIcon);
            }
            @Override
            public void focusGained(FocusEvent e) {
                Image icon = Toolkit.getDefaultToolkit().getImage("src/img/pixeles.png"); 
                window.setIconImage(icon);
            }
        });
        
        centerPanel.add(btnCancel);
        
        bigPanel.add(midPanel);
        midPanel.add(centerPanel);

        JScrollPane scroll = new JScrollPane(bigPanel);
        scroll.setBorder(null); 
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().setBackground(bgColor); 
        
        add(scroll, BorderLayout.CENTER);
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
        field.setMargin(new Insets(10, 10, 10, 10));
        field.setPreferredSize(new Dimension(350, 40));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field);
        
        return createLabelError(panel);
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
        
        return createLabelError(panel);
    }

    private void changeBackground(JComponent component) {
        component.setBackground(new Color(17, 53, 189));
        component.setForeground(Color.white);
    }

    private void resetBackground(JComponent component) {
        component.setBackground(Color.white);
        component.setForeground(Color.black);
    }

    private JLabel createLabelError(JPanel panel) {
        JLabel lblError = new JLabel(" ");
        lblError.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblError.setForeground(Color.CYAN);
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

    public SignUpWindow getWindow() {
        return window;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtNickname() {
        return txtNickname;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public JPasswordField getTxtConfirmPass() {
        return txtConfirmPass;
    }

    public JComboBox<String> getCbGems() {
        return cbGems;
    }

    public JComboBox<String> getCbWeapon() {
        return cbWeapon;
    }

    public JComboBox<String> getCbElement() {
        return cbElement;
    }

    public JLabel getErrEmail() {
        return errEmail;
    }

    public JLabel getErrNickname() {
        return errNickname;
    }

    public JLabel getErrGem() {
        return errGem;
    }

    public JLabel getErrWeapon() {
        return errWeapon;
    }

    public JLabel getErrElement() {
        return errElement;
    }

    public JLabel getErrPassword() {
        return errPassword;
    }

    public JLabel getErrConfirm() {
        return errConfirm;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }
}