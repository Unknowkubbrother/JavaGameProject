package core.MAP;

import core.GamePanel;
// import core.KeyHandler;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class LOBBY{

    private GamePanel gp;
    private ArrayList<Map> maps = new ArrayList<>();

    public LOBBY (GamePanel gp) {
        this.gp = gp;
        loadMap();
    }

    private BufferedImage loadSprite(String path) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getResource("/resources/texture/map/lobby/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sprite;
    }

    private void loadMap(){
        //wall
        for(int i = 1;i<=1;i++){
            maps.add(new Map());
            maps.get(i-1).map = loadSprite(String.format("floor.png", i));
        }
        
    }
    
    public void draw(Graphics g2) {


        g2.drawImage(maps.get(0).map, 0, 0, (gp.titleSize/4)*3, (gp.titleSize/4)*3, null);
    }

    
}
