package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameCanvas extends JPanel {

	private int frameCount = 0;

    /*
     * Inventory contains item IDs.
     *
     * Example:
     * 40 = Rusted Sword
     * 41 = Rusted Spear
     * 42 = Revolver
     */
    private List<Integer> inventoryById = new ArrayList<>();

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

    private static final int ICON_SIZE = 64;
    private static final int ICON_SPACING = 10;
    private static final int COLUMNS = 2;

    public GameCanvas() {

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

        String id;

        /*
         * Loads:
         *
         * Item400.png
         * Item401.png
         * ...
         * Item609.png
         */

        for (int itemId = 0; itemId <= 60; itemId++) {
            for (int frame = 0; frame <= 9; frame++) {

                id = "item" + itemId + "("+ frame + ").png";

                BufferedImage img =
                        loadImg("/assets/itemSprites/item" + itemId + "("+ frame + ").png");

                if (img != null) {
      //          	 System.out.println(
    //                         "Loaded sprite: " + id
        //             );
                    sprites.put(id, img);
                }
          //      else System.out.println(
            //            "NO Loaded sprite: " + id
              //  );
            }
        }

        System.out.println(
                //"Loaded sprites: " + sprites.size()
        );
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
    }

    private void drawInventory(Graphics2D g) {

        for (int index = 0;
             index < inventoryById.size();
             index++) {

            int itemId = inventoryById.get(index);

            /*
             * Animation frame
             *
             * 0 1 2 3 4 5 6 7 8 9
             */

            int frame = (frameCount / 5) % 10;

            String spriteId =
            		"item" + itemId + "("+ frame + ").png";

            BufferedImage img =
                    sprites.get(spriteId);

            if (img == null) {

                /*
                 * Fallback:
                 * try frame 0
                 */

                img = sprites.get(
                		"item" + itemId + "(0).png"
                );
            }

            if (img == null) {
                continue;
            }

            int row = index / COLUMNS;
            int col = index % COLUMNS;

            int x =
                    10 + col * (ICON_SIZE + ICON_SPACING);

            int y =
                    10 + row * (ICON_SIZE + ICON_SPACING);

            g.drawImage(
                    img,
                    x,
                    y,
                    ICON_SIZE,
                    ICON_SIZE,
                    null
            );
        }
    }

    public void addInventoryById(int id) {

        inventoryById.add(id);

        repaint();
    }

    public void removeInventoryById(int id) {

        inventoryById.remove(
                Integer.valueOf(id)
        );

        repaint();
    }

    public void clearInventory() {

        inventoryById.clear();

        repaint();
    }

    public List<Integer> getInventoryById() {

        return inventoryById;
    }

    public int getFrameCount() {

        return frameCount;
    }

}