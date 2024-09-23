package core;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum Texture {
    EMPTY(0),
    FLOOR(1),
    FlOOR_1(2),
    WALL_TOP(3),
    WALL_BOTTOM(4),
    WALL_LEFT(5),
    WALL_RIGHT(6),
    GRASS_1(7),
    GRASS_2(8),
    GRASS_3(9),
    GRASS_4(10),
    GRASS_STONE1(11),
    GRASS_STONE2(12);

    public final int textureId;
    private static Map<Integer, TextureLoader> dict = new HashMap<>();

    private Texture(int textureId) {
        this.textureId = textureId;
    }

    public static void loadTexture(){
        long startTime = System.currentTimeMillis();
        System.err.println("Loading textures...");
        try{
            dict.put(0, new TextureLoader("/resources/texture/map/empty.png", true));
            dict.put(1, new TextureLoader("/resources/texture/map/floor.png", false));
            dict.put(2, new TextureLoader("/resources/texture/map/floor_1.png", false));
            dict.put(3, new TextureLoader("/resources/texture/map/wall_top.png", true));
            dict.put(4, new TextureLoader("/resources/texture/map/wall_bottom.png", true));
            dict.put(5, new TextureLoader("/resources/texture/map/wall_left.png", true));
            dict.put(6, new TextureLoader("/resources/texture/map/wall_right.png", true));
            dict.put(7, new TextureLoader("/resources/texture/map/grass_1.png", false));
            dict.put(8, new TextureLoader("/resources/texture/map/grass_2.png", false));
            dict.put(9, new TextureLoader("/resources/texture/map/grass_3.png", false));
            dict.put(10, new TextureLoader("/resources/texture/map/grass_4.png", false));
            dict.put(11, new TextureLoader("/resources/texture/map/grass_stone1.png", false));
            dict.put(12, new TextureLoader("/resources/texture/map/grass_stone2.png", false));

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
