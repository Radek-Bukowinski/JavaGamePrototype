import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private final int width = 800;
    private final int height = 800;

    private Thread thread;
    private boolean running = false;

    private RendererHandler rendererHandler;
    private KeyInput keyInput;

    public Game(){
        new Window(width, height, "Game", this);
        rendererHandler = new RendererHandler();
        keyInput = new KeyInput(rendererHandler);
        this.addKeyListener(keyInput);
        rendererHandler.addObject(new Player(100, 100, ID.Player));
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop(){
        try {
            thread.join();
            running = false;
        }catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();

        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double accumulatedFrameTime = 0;

        long timer = System.currentTimeMillis();


        int ticks = 0;
        int renders = 0;

        while (running) {
            long timeNow = System.nanoTime();
            accumulatedFrameTime += (timeNow - lastTime) / ns;

            lastTime = timeNow;
            while (accumulatedFrameTime >= 1) {
                tick();
                render();
                ticks++;
                accumulatedFrameTime = 0;
            }

        }
    }

    private void tick(){
        rendererHandler.tick();
    }
    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);

        rendererHandler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String args[]){
        new Game();
    }
}
