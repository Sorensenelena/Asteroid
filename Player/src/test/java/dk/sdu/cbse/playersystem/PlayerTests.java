package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

public class PlayerTests {
    private GameData gameData;

    private GameKeys gameKeys;

    private World world;
    private Player player;
    private PlayerPlugin playerPlugin;
    private PlayerControlSystem controlSystem;

    @BeforeEach
    public void setup() {
        world = new World();
        playerPlugin = new PlayerPlugin();
        controlSystem = new PlayerControlSystem();

        gameData = new GameData();
        gameData.setDisplayWidth(800);
        gameData.setDisplayHeight(600);
    }

    @Test
    void testPlayerLoadedToWorld() {
        playerPlugin.start(gameData,world);
        assertEquals(1, world.getEntities(Player.class).size());
    }

    @Test
    void testPlayerRemovedFromWorld() {
        playerPlugin.start(gameData,world);
        playerPlugin.stop(gameData,world);
        assertEquals(0, world.getEntities(Player.class).size());
    }

    @Test
    void testPlayerSpawnedOnMap() {
        playerPlugin.start(gameData,world);
        Entity p = world.getEntities(Player.class).iterator().next();
        assertEquals(400, p.getX()); //the center of a 800x600 screen from setup
        assertEquals(300, p.getY());
    }
}
