package core;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum Texture {
    FLOOR(0),
    WALL_LEFT(1),
    WALL_TOP(2),
    WALL_BOTTOM(3),
    WALL_RIGHT(4),
    FlOOR1(5);

    public final int textureId;
    private static Map<Integer, TextureLoader> dict = new HashMap<>();


    private Texture(int textureId) {
        this.textureId = textureId;
    }

    public static void loadTexture(){
        long startTime = System.currentTimeMillis();
        System.err.println("Loading textures...");
        try{
            dict.put(0, new TextureLoader("/resources/texture/map/floor.png", false));
            dict.put(1, new TextureLoader("/resources/texture/map/wall_left.png", true));
            dict.put(2, new TextureLoader("/resources/texture/map/wall_top.png", true));
            dict.put(3, new TextureLoader("/resources/texture/map/wall_bottom.png" , true));
            dict.put(4, new TextureLoader("/resources/texture/map/wall_right.png" , true));
            dict.put(5, new TextureLoader("/resources/texture/map/floor1.png", false));

        }catch(IOException e){
            System.out.println("[Texture]: Failed to load textures!");
            e.printStackTrace();
        }
        System.out.println("[Texture]: Textures loaded! (" + (System.currentTimeMillis() - startTime) + "ms)");
    }

    public int getTextureId() {
        return textureId;
    }

    public BufferedImage getTexture() {
        return dict.get(textureId).getImage();
    }

    public int getTextureWidth() {
        return dict.get(textureId).getTextureWidth();
    }

    public int getTextureHeight() {
        return dict.get(textureId).getTextureHeight();
    }

    public boolean isCollision() {
        return dict.get(textureId).isCollision();
    }

    public void setCollision(boolean collision) {
        dict.get(textureId).setCollision(collision);
    }

}

class TextureLoader {
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;
    private boolean collision;

    public TextureLoader(String path,boolean collision) throws IOException {
        this.image = ImageIO.read(getClass().getResourceAsStream(path));
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.collision = collision;
    }

    public int getTextureWidth() {
        return imageWidth;
    }

    public int getTextureHeight() {
        return imageHeight;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public BufferedImage getImage() {
        return image;
    }

}
