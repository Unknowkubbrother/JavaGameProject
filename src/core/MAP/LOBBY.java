package core.MAP;

import core.GamePanel;
import core.Texture;

// import core.KeyHandler;
// import java.util.ArrayList;
import java.awt.Graphics;

public class LOBBY{

    private GamePanel gp;
    private Texture mapContent[][];
    private int preMapContenet[][] = {
        {1,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,5,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,5,0},
        {1,3,3,3,3,3,3,3,3,3,3,3,1,0,0,0},
    };

    public LOBBY (GamePanel gp) {
        this.gp = gp;
        loadMap();
    }

    private void loadMap(){
        int row = preMapContenet.length;
        int col = preMapContenet[0].length;
        mapContent = new Texture[row][col];
        //floor
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                mapContent[i][j] = Texture.values()[preMapContenet[i][j]];
            }
        }
        
    }
    
    public void draw(Graphics g2) {
        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            g2.drawImage(mapContent[row][col].getTexture(), x, y, gp.titleSize, gp.titleSize, null);
            x += gp.titleSize;
            col++;
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
                x = 0;
                y += gp.titleSize;
            }
            
        }


    }

    
}
