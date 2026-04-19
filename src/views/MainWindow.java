package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.utils.AppFont;

public class MainWindow extends JFrame{

	/*Para la tabla de Usuarios*/
	public static final String HOME = "Home";
	public static final String USERS = "Users";
	public JMenuItem mItemExit;
	public JButton usersBtn;
	public JButton homeBtn;
	public UsersView usersPanel;
	private CardLayout cardLayout; //gestor de diseño
	private JPanel container; //la caja/panel
	

    public MainWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image myIcon = tk.getImage("src/assets/img/pixeles.png"); 
        setIconImage(myIcon);
        
        setTitle("Cinerea");
        setSize(1000, 750); 
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        initializeCompounds();
        
        /*Para admins*/
        setMenu();
        createNavbar();
        createViews();
        
        
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

        JLabel lblTitle = new JLabel("CINEREA");
        lblTitle.setFont(AppFont.title());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTitle);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
        JButton btnPlay = new JButton("Start");
        addButtonMain(centerPanel, "Start", btnPlay);
        
        btnPlay.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnPlay);
			}
			
			public void mouseExited(MouseEvent e) {
				resetBackground(btnPlay);
			}
		});
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JButton btnConfig = new JButton();
        addButtonMain(centerPanel, "Configurations", btnConfig);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JButton btnCredits = new JButton();
        addButtonMain(centerPanel, "Credits", btnCredits);
        
        bigPanel.add(midPanel);
        midPanel.add(centerPanel);
        add(bigPanel);
    }
    
    private JButton addButtonMain(JPanel panel, String labelText, JButton button) {
    	JButton btn = new JButton(labelText);
    	pixelBorderText(btn);
    	btn.setFont(AppFont.titleSecondary());
    	btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btn.setMaximumSize(new Dimension(400, 100));
    	btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	panel.add(btn);
    	//setMenu();
    	return btn;
    }

    /*public void setMenu() {

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu archivo = new JMenu("Archive");
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);

		JMenuItem abrir = new JMenuItem("Open");
		abrir.setMnemonic(KeyEvent.VK_B);
		archivo.add(abrir);

		JMenuItem guardar = new JMenuItem("Save");
		guardar.setMnemonic(KeyEvent.VK_G);
		archivo.add(guardar);

		archivo.addSeparator();

		JMenuItem salir = new JMenuItem("Close");
		salir.setMnemonic(KeyEvent.VK_S);
		archivo.add(salir);

		JMenu otraOpcion = new JMenu("Other option");
		otraOpcion.setMnemonic(KeyEvent.VK_O);
		mb.add(otraOpcion);

		JMenu opcion1 = new JMenu("Option 1");
		otraOpcion.add(opcion1);

		JMenuItem opcion3 = new JMenuItem("Option 3");
		opcion1.add(opcion3);

		JMenuItem opcion2 = new JMenuItem("Option 2");
		otraOpcion.add(opcion2);

	}*/
    
    public void setMenu() {

	    JMenuBar mb = new JMenuBar();
	    setJMenuBar(mb);

	    JMenu mF = new JMenu("File");
	    mF.setMnemonic(KeyEvent.VK_F);
	    mb.add(mF);

	    JMenuItem mItemOpen = new JMenuItem("Open");
	    mItemOpen.setMnemonic(KeyEvent.VK_O);
	    mF.add(mItemOpen);

	    JMenuItem mItemSave = new JMenuItem("Save");
	    mItemSave.setMnemonic(KeyEvent.VK_S);
	    mF.add(mItemSave);

	    mF.addSeparator();

	    mItemExit = new JMenuItem("Exit");
	    mItemExit.setMnemonic(KeyEvent.VK_E);
	    mF.add(mItemExit);

	}
	

    
    public void createNavbar() {
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		homeBtn = new JButton("Inicio");usersBtn = new JButton("Usuarios");
		
		navbar.add(homeBtn);navbar.add(usersBtn);
		
		add(navbar, BorderLayout.NORTH);
	}
	
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		JPanel homePanel = new JPanel();
		homePanel.add(new JLabel("Bienvenido al Sistema"));
		usersPanel = new UsersView();
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		add(container, BorderLayout.CENTER);
	}
    
    public void showView(String view) {
		cardLayout.show(container, view); //usaremos el panel container, nice, segun el view (Home o Users)
	}
    
    public int confirmExit() { //pq esto es un int?, naturalmente, JOptionPane es un int
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}

    private void changeBackground(JComponent component) {
    	component.setBackground(new Color(17, 53, 189));
    	component.setForeground(Color.white);
    }
    
    private void resetBackground(JComponent component) {
    	component.setBackground(Color.white);
    	component.setForeground(Color.black);
    }

    private void pixelBorderText(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        component.setFont(AppFont.normalSecondary());
        component.setBackground(Color.WHITE);
        component.putClientProperty("JComponent.focusWidth", 0);
    }
}