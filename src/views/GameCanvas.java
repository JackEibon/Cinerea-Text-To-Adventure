package views;

import javax.swing.*;

import characters.*;
import gameWorld.WorldGraph;
import gamelogic.Star;
import repository.ItemRepository;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameCanvas extends JPanel {

	private CharSheet player;
	private WorldGraph world;
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
    
    public GameCanvas(CharSheet player, WorldGraph world) {
    	this.player=player;
    	this.world=world;
        setPreferredSize(new Dimension(360, 640));
        setBackground(Color.BLACK);

        loadSprites();
        

        Timer timer = new Timer(50, e -> {
            frameCount++;
            repaint();
    
        });
        

        timer.start();
    }
    

    private void loadSprites() {
    	loadImgItems();
    	loadImgSky();
    }
    
    private void loadImgItems() {
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
                  //  System.out.println("attempting to load: /assets/itemSprites/"+ spriteName);
                    if (img != null) {
                        sprites.put(spriteName,img);
                        loaded++;
                    }
                 //   else System.out.println("failed to load: "+ itemName + "(" + frame + ").png");
                }
            }
           
    }
    
    private void loadImgSky() {
    	int loaded=0;
    	 String spriteName = "";
        for(int i = 0; i <= 6; i++) {

            spriteName ="sky(" + i + ").png";
           // System.out.println("attempting to load: /assets/skySprites/"+ spriteName);
            BufferedImage img =
                loadImg("/assets/skySprites/" + spriteName);

            if(img != null) {
                sprites.put(spriteName, img);
                loaded++;
            }
            //else System.out.println("failed to load: "+ spriteName);
        }
        //System.out.println("Loaded sky sprites: "+ loaded);

        

            for(int type = 1; type <= 3; type++) {

                for(int frame = 0; frame < 20; frame++) {

                    spriteName =
                        "star" + type + "(" + frame + ").png";

                    BufferedImage img =
                        loadImg("/assets/skySprites/" + spriteName);
              //      System.out.println("attempting to load: /assets/skySprites/"+ spriteName);

                    if(img != null) {
                        sprites.put(spriteName, img);
                        loaded++;    
                    }
            //        else System.out.println("failed to load: "+ spriteName);
                }
            }
          //  System.out.println("Loaded star sprites: "+ loaded);
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
      
     
        
        switch(player.getLookWhere()) {
        case "up":{ 
        	drawUp(g2);
  
        break;}
        case "score":{drawScore(g);break;}
        
        case "inventory": default:{ drawInventory(g2);
    break;}
        }
        
        
        if (frameCount>100) frameCount=0; 
    }
    
    private void drawScore(Graphics g) 
    {
    	String score=Integer.toString(player.getCountingScore());
    	g.setColor(Color.WHITE);
    	for (int i=0; i<100; i++)
    	g.drawString(
    			score,  3, 0+(i*6));
 
    	
    }
    
    
    private void drawUp(Graphics2D g) {
    	String spriteName="";
    	if (player.isSky()) 
    	{
    		spriteName= "sky" + "("+ player.getTock() + ").png";
    	}
    	int index= 0;
    	
        	 
        	 //System.out.println(spriteName);
        	 BufferedImage img =sprites.get(spriteName); 
        	 if (img != null) 
            
            g.drawImage(img,0,0,360,640, null);
            if (player.getTock()> 3 &&  player.isSky()) {
            	drawNigthSky(g);
            }
  
    }
    
    private void drawNigthSky(Graphics2D g) {
    	//System.out.println("drawNigthSky");
    	

        for(Star star : world.getNightSky()) {
        	if (star.shouldAppear()) {
        		drawStar(
                        g,
                        star.getType(),
                        star.getFrame()+frameCount,
                        star.getX(),
                        star.getY()
                    );	
        	}
        }    
    }
    
    private void drawStar(
            Graphics g,
            int type,
            int frame,
            int x,
            int y) {

        int f=frame;

        switch(type) {

            case 1:
                f %= 8;
                break;

            case 2:
                f %= 9;
                break;

            case 3:
                f %= 15;
                break;

            default:
                f %= 8;
        }

        String id="";

            id = "star" + type + "("+f + ").png";

        BufferedImage img = sprites.get(id);

        if(img != null) {
            g.drawImage(img, x, y, null);
            //System.out.println("Star: drawn at x=" + x + " y="+y);   
        }
    }
    
    
    

    private void drawInventory(Graphics2D g) {

    	int index= 0;
        for (String ite : player.getInventoryNames()) {
        	int frame = (frameCount / 5) % 10;
        	 String spriteName = ite + "("+ frame + ").png";
        	 //System.out.println(spriteName);
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