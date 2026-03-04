package views;

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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.AppFont;

public class MainWindow extends JFrame{

    private JTextField txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JLabel errEmail, errPassword, errConfirm;

    public MainWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/img/pixeles.png"); 
        setIconImage(myIcon);
        
        setTitle("Cinerea");
        setSize(1000, 750); 
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeCompounds();
        
        setVisible(true);
    }
    
    public void initializeCompounds() {
        Color bgColor = new Color(255, 239, 182);
        
        JPanel centerPanel = new JPanel(); 
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bgColor);
        centerPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel lblTitle = new JLabel("CINEREA");
        lblTitle.setFont(AppFont.title());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
        JButton btnPlay = new JButton("Start");
        addButtonMain(centerPanel, "Start", btnPlay);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JButton btnConfig = new JButton();
        addButtonMain(centerPanel, "Configurations", btnConfig);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JButton btnCredits = new JButton();
        addButtonMain(centerPanel, "Credits", btnCredits);
        
        add(centerPanel);
    }
    
    private JButton addButtonMain(JPanel panel, String labelText, JButton button) {
    	JButton btn = new JButton(labelText);
    	pixelBorderText(btn);
    	btn.setFont(AppFont.titleSecondary());
    	btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btn.setMaximumSize(new Dimension(400, 100));
    	btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	panel.add(btn);
    	
    	return btn;
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
        lblError.setFont(new Font("Monospaced", Font.BOLD, 14));
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
}
