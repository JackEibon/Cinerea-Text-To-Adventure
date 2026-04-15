package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import controllers.SignUpController;
import repository.UserRepository;

public class SignUpWindow extends JFrame {

    private SignUpView signUpView;
    private UserRepository repository;

    public SignUpWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/assets/img/pixeles.png"); 
        setIconImage(myIcon);
        
        setTitle("Sign Up");
        setSize(620, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        signUpView = new SignUpView(this);
        add(signUpView);
        
        new SignUpController(signUpView);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                Image poorIcon = Toolkit.getDefaultToolkit().getImage("src/assets/img/pixelesgray.png"); 
                setIconImage(poorIcon);
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
                Image icon = Toolkit.getDefaultToolkit().getImage("src/assets/img/pixeles.png"); 
                setIconImage(icon);
            }
        });
        
        setVisible(true);
    }

    public SignUpView getSignUpView() {
        return signUpView;
    }
}