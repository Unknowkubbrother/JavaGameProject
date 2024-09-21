package core.UI;

import core.GamePanel;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;

public class BoxStatus {
    private GamePanel gp;
    private JProgressBar boxHp;

    public BoxStatus(GamePanel gp) {
        this.gp = gp;
        initializeComponents();
    }

    private void initializeComponents() {
        // Initialize the health bar
        boxHp = new JProgressBar(0, 100);
        boxHp.setPreferredSize(new Dimension(350, 80));
        boxHp.setValue(100); // Set initial health value
        boxHp.setStringPainted(true); // Show percentage text on the bar

        // Initialize the panel
        JPanel boxStatus = new JPanel();
        boxStatus.setBounds(10, 10, 350, 80);
        boxStatus.setBackground(Color.RED);
        boxStatus.add(boxHp);

        // Add the panel to the GamePanel
        gp.setLayout(null); // Use absolute positioning
        gp.add(boxStatus);
    }

    public void updateHealth(int health) {
        boxHp.setValue(health);
    }

    public void draw(Graphics g2) {
        // The drawing logic is handled by Swing components, so no need to manually paint here
    }
}
