package dk.sdu.cbse.common.data;

public class GameData {
    private int displayWidth = 800;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();

    private long lastFrameTime;
    private double deltaTime;

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public void setDeltaTime(double deltaTime) {
        long currentTime = System.currentTimeMillis();
        if (lastFrameTime > 0){
            deltaTime = currentTime - lastFrameTime;
            this.deltaTime += deltaTime / 1_000_000_000.0;
        }
        lastFrameTime = currentTime;
    }

    public double getDeltaTime() {
        return deltaTime;
    }
}
