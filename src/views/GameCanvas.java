package views;

import javax.swing.*;

import gamelogic.CharSheet;
import repository.ItemRepository;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameCanvas extends JPanel {

	private CharSheet player;
	private int frameCount = 0;

    /*
     * Inventory contains item IDs.
     *
     * Example:
     * 40 = Rusted Sword
     * 41 = Rusted Spear
     * 42 = Revolver
     */

    /*
     * Sprite cache.
     *
     * Key examples:
     * Item400.png
     * Item401.png
     * Item402.png
     * Item403.png
     */
    private HashMap<String, BufferedImage> sprites = new HashMap<>();
    private static final int ICON_SIZE = 60;
    private static final int ICON_SPACING = 10;
    private static final int COLUMNS = 2;

    public GameCanvas(CharSheet player) {
    	this.player=player;

        setPreferredSize(new Dimension(350, 500));
        setBackground(Color.BLACK);

        loadSprites();

        Timer timer = new Timer(50, e -> {
            frameCount++;
            repaint();
        });

        timer.start();
    }
    

    private void loadSprites() {
        ItemRepository repo =
            new ItemRepository();
        List<String> itemNames =
            repo.getItemNames();

        int loaded = 0;

        for (String itemName : itemNames) {

            for (int frame = 0;frame < 10;frame++) {

                String spriteName =
                    itemName + "(" + frame + ").png";

                BufferedImage img =
                    loadImg("/assets/itemSprites/"+ spriteName);
                System.out.println("attempting to load: /assets/itemSprites/"+ spriteName);
                if (img != null) {
                    sprites.put(spriteName,img);
                    loaded++;
                }
                else System.out.println("failed to load: "+ itemName + "(" + frame + ").png");
            }
        }
        System.out.println("Loaded sprites: "+ loaded);
    }

     

    private BufferedImage loadImg(String path) {

        try {

            return javax.imageio.ImageIO.read(
                    getClass().getResource(path)
            );

        } catch (Exception ex) {

            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        drawInventory(g2);
        
        if (frameCount>100) frameCount=0; 
    }
    
    private void drawInventory(Graphics2D g) {

    	int index= 0;
        for (String ite : player.getInventoryNames()) {
        	int frame = (frameCount / 5) % 10;
        	 String spriteName = ite + "("+ frame + ").png";
        	 BufferedImage img =sprites.get(spriteName);
        	 if (img == null) img = sprites.get(ite + "(0).png"); //Fallback:try frame 0 
        	 if (img == null) continue;
            int row = index / COLUMNS;
            int col = index % COLUMNS;
            int x =10 + col * (ICON_SIZE + ICON_SPACING);
            int y= 10 + row * (ICON_SIZE + ICON_SPACING);
            g.drawImage(img,x,y,ICON_SIZE,ICON_SIZE, null);
            index++;
        }
    }

}