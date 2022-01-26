import java.awt.*;

public class Player extends GameObject{
    private RendererHandler rendererHandler;

    public Player(int x, int y, ID id){
        super(x, y, id);
        this.rendererHandler = rendererHandler;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
