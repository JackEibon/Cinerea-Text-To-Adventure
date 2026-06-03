package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
public class GameCanvas extends JPanel {

    private int frameCount = 0;

    private List<BufferedImage> items;

    public GameCanvas() {
        setPreferredSize(new Dimension(350,500));
        setBackground(Color.BLACK);
        loadAssets();
        Timer timer = new Timer(50, e -> {
            frameCount++;
            repaint();
        });
        timer.start();
    }

    private void loadAssets() {
        try {
        	for (int i=0;i<10;i++) {
        		for(int j=0;j<99;j++) {
        			items.add(javax.imageio.ImageIO.read(getClass().getResource(
        					"/assets/itemSprites/item"+i+"("+j+").png"
        					)));
        		}
        		
			}
           
        }
        catch(Exception e) {e.printStackTrace();}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

    }

 
}