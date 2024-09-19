import javax.swing.*;
import core.GamePanel;

public class Game {

    public Game(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dark Knight");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Game());;
    }
}
