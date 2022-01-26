import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private RendererHandler rendererHandler;

    private boolean[] keyDown = new boolean[4];
    private DIRECTION facingDirection = DIRECTION.RIGHT;

    public KeyInput(RendererHandler rendererHandler){
        this.rendererHandler = rendererHandler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        for(int i = 0; i < rendererHandler.objects.size(); i++) {
            GameObject temporaryObject = rendererHandler.objects.get(i);

            if (temporaryObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    temporaryObject.setVelocityY(-5);
                    keyDown[0] = true;
                    facingDirection = DIRECTION.UP;
                }
                if (key == KeyEvent.VK_S) {
                    temporaryObject.setVelocityY(5);
                    keyDown[1] = true;
                    facingDirection = DIRECTION.DOWN;
                }
                if (key == KeyEvent.VK_A) {
                    temporaryObject.setVelocityX(-5);
                    keyDown[2] = true;
                    facingDirection = DIRECTION.LEFT;
                }
                if (key == KeyEvent.VK_D) {
                    temporaryObject.setVelocityX(5);
                    keyDown[3] = true;
                    facingDirection = DIRECTION.RIGHT;
                }
            }
        }
    }

    public void keyReleased(KeyEvent event)
    {
        int key = event.getKeyCode();

        for(int i = 0; i < rendererHandler.objects.size(); i++)
        {
            GameObject temporaryObject = rendererHandler.objects.get(i);

            if(temporaryObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_W)
                {
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_S)
                {
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_A)
                {
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_D)
                {
                    keyDown[3] = false;
                }

                if(!keyDown[0] && !keyDown[1])
                {
                    temporaryObject.setVelocityY(0);
                }

                if(!keyDown[2] && !keyDown[3])
                {
                    temporaryObject.setVelocityX(0);
                }
            }
        }
    }
}
