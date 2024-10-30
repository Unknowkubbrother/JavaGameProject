package core;
import java.awt.event.KeyListener;

import core.MAP.LOBBY;

import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener{
    GamePanel gp;
    public boolean up, down, left, right;


    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 8 Backspace , 9 Tab, 10 Enter, 12 Clear , 16 Shift, 17 Ctrl, 18 Alt , 65 A, 66 B, 67 C, 68 D, 69 E, 70 F, 71 G, 72 H, 73 I, 74 J, 75 K, 76 L, 77 M, 78 N, 79 O, 80 P, 81 Q, 82 R, 83 S, 84 T, 85 U, 86 V, 87 W, 88 X, 89 Y, 90 Z

        int code = e.getKeyCode();

        if (gp.gameState == gp.menuState){
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0){
                    gp.map = new LOBBY(gp);
                    gp.gameState = gp.playerState;
                }
                else if (gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
                
        }




        if (code == KeyEvent.VK_W) {
            up = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if (code == KeyEvent.VK_S) {
            down = true;
        }
        if (code == KeyEvent.VK_D) {
            right = true;
        }
        
        //Pause
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playerState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playerState;
            }
        }

        if (gp.gameState == gp.playerState){
            if (code == KeyEvent.VK_1){
                gp.player.setCurrentElement(0);
            }else if (code == KeyEvent.VK_2){
                gp.player.setCurrentElement(1);
            }
            else if (code == KeyEvent.VK_3){
                gp.player.setCurrentElement(2);
            }
            else if (code == KeyEvent.VK_4){
                gp.player.setCurrentElement(3);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
    }

    
}
