package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.AppFont;

public class LogInWindow extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel errEmail, errPassword;
    public LogInWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/img/pixeles.png"); 
        
        setIconImage(myIcon);
        
        setTitle("Login");
        setSize(630, 680); 
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeCompounds();
        
        setVisible(true);
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
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        txtEmail = new JTextField();
        errEmail = addFormGroup(centerPanel, "EMAIL", txtEmail);
        
        txtPassword = new JPasswordField();
        errPassword = addFormGroup(centerPanel, "PASSWORD", txtPassword);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton btnLogin = new JButton(); 
        addButtonForm("Log In", btnLogin);
        
        btnLogin.addActionListener(e -> validateAndShow());
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
        
        JButton btnRegister = new JButton(); 
        addButtonForm("Sign Up", btnRegister);
        
        btnRegister.addActionListener(e -> handleBtnRegister());
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
        add(bigPanel);;
        
        addActionListeners();
        
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				Image poorIcon = Toolkit.getDefaultToolkit().getImage("src/img/pixelesgray.png"); 
		        setIconImage(poorIcon);
			}
			public void windowDeiconified(WindowEvent e) {
				Image Icon = Toolkit.getDefaultToolkit().getImage("src/img/pixeles.png"); 
		        setIconImage(Icon);
				
			}
			
		});
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
    
    private boolean validateTxtEmail() {
    	if(txtEmail.getText().trim().isEmpty()) { 
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
    }

    private void validateAndShow() {
        errEmail.setVisible(false);
        errPassword.setVisible(false);

        String pass = new String(txtPassword.getPassword());
        boolean isValid = true;
        
        if(!validateTxtEmail()) {
        	isValid = false;
        }

        if (pass.isEmpty()) {
            errPassword.setText("Password is required");
            errPassword.setVisible(true);
            isValid = false;
        }

        if (isValid) {
            JOptionPane.showMessageDialog(this, "Welcome, " + txtEmail.getText().trim() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new MainWindow();
            dispose();
        } //Aqui proximamente pondremos la variable del personaje al que le pertenece la cuenta
        
        revalidate();
        repaint();
    }

    private void handleBtnRegister() {
    	new SignUpWindow();
        dispose();
	}
    
    
    
    
}