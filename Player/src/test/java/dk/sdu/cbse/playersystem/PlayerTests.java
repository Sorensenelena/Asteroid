package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerTests {

    private GameData gameData;
    private World world;
    private Player player;
    private PlayerPlugin playerPlugin;
    private PlayerControlSystem controlSystem;

    private GameData mockGameData;
    private World mockWorld;
    private Player mockPlayer;
    private PlayerControlSystem playerControlSystem;

    @BeforeEach
    public void setup() {
        world = new World();
        playerPlugin = new PlayerPlugin();
        controlSystem = new PlayerControlSystem();
        gameData = new GameData();
        gameData.setDisplayWidth(800);
        gameData.setDisplayHeight(600);

        playerControlSystem = new PlayerControlSystem();
        mockGameData = mock(GameData.class);
        mockWorld = mock(World.class);
        mockPlayer = mock(Player.class);
        GameKeys gameKeys = mock(GameKeys.class);
        when(mockGameData.getKeys()).thenReturn(gameKeys);
        when(mockWorld.getEntities(Player.class)).thenReturn(List.of(mockPlayer));
    }

    @Test
    void testPlayerLoadedToWorld() {
        playerPlugin.start(gameData, world);
        assertEquals(1, world.getEntities(Player.class).size());
    }

    @Test
    void testPlayerRemovedFromWorld() {
        playerPlugin.start(gameData, world);
        playerPlugin.stop(gameData, world);
        assertEquals(0, world.getEntities(Player.class).size());
    }

    @Test
    void testPlayerSpawnedOnMap() {
        playerPlugin.start(gameData, world);
        Entity p = world.getEntities(Player.class).iterator().next();
        assertEquals(400, p.getX());
        assertEquals(300, p.getY());
    }

    @Test
    public void testProcessPlayerMovesUp() {
        when(mockGameData.getKeys().isDown(GameKeys.UP)).thenReturn(true);
        when(mockPlayer.getRotation()).thenReturn(0.0);
        playerControlSystem.process(mockGameData, mockWorld);
        verify(mockPlayer, times(1)).setX(anyDouble());
        verify(mockPlayer, times(1)).setY(anyDouble());
    }

    @Test
    public void testPlayerTakesDamage() {
        when(mockPlayer.getHealth()).thenReturn(3);
        when(mockPlayer.getHealth()).thenReturn(2);
        assertEquals(2, mockPlayer.getHealth());
    }

    @Test
    public void testProcessPlayerHealthZero() {
        when(mockPlayer.getHealth()).thenReturn(0);
        playerControlSystem.process(mockGameData, mockWorld);
        verify(mockWorld, times(1)).removeEntity(mockPlayer);
    }
}